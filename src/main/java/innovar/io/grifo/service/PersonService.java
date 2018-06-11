/**
 * The PersonService class is implemment to
 *
 * @version :1.0
 * @Author :Warren
 * @since :26/05/2018
 */
package innovar.io.grifo.service;

import innovar.io.grifo.config.AppResponse;
import innovar.io.grifo.config.captcha.CaptchaResolv;
import innovar.io.grifo.entity.Person;
import innovar.io.grifo.repository.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.File;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Service
public class PersonService {
    //    @Value("${tessdata.dir:classpath:/tessdata}")
    File tessdata;
    //    @Value("${tessdata.lang:eng}")
    String lang;

    @Autowired
    CaptchaResolv captchaResolv;

    @Autowired
    PersonDao dao;

    @Autowired
    ReactiveMongoOperations operations;

    public PersonService() {
        this.tessdata = new File(getClass().getResource("/tessdata").getPath());
        this.lang = "eng";
        System.out.println(tessdata.getPath());
    }

    public Mono<ServerResponse> getByDni(ServerRequest request) {
        return Mono.just(captchaResolv.getDni(request.pathVariable("dni"), tessdata, lang))
                .flatMap(AppResponse::AppResponseOk).onErrorResume(AppResponse::AppResponseError);
    }

    public Mono<ServerResponse> getByRuc(ServerRequest request) {
        return Mono.just(captchaResolv.getRuc(request.pathVariable("ruc"), tessdata, lang))
                .flatMap(AppResponse::AppResponseOk).onErrorResume(AppResponse::AppResponseError);
    }

    public Mono<ServerResponse> inertPerson(ServerRequest request) {
        Flux<Person> people = request.bodyToFlux(Person.class).flatMap(dao::insert);
        return ok().body(people, Person.class);
    }

    public Mono<ServerResponse> findPersonByText(ServerRequest request) {
        Flux<Person> people = request.bodyToFlux(Person.class);
        return dao.findAllByAllNameContainingOrRucUserContaining(request.pathVariable("text"), request.pathVariable("text"), PageRequest.of(0, 100)).limitRequest(10).collectList().flatMap(
                AppResponse::AppResponseOk
        ).onErrorResume(AppResponse::AppResponseError);
    }
    public Mono<ServerResponse> insertAllPerson(ServerRequest request) {
        return null;
    }
}
