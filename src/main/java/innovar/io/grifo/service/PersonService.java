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
    @Value("${tessdata.dir:classpath:/tessdata}")
    File tessdata;
    @Value("${tessdata.lang:eng}")
    String lang;

    @Autowired
    CaptchaResolv captchaResolv;

    @Autowired
    PersonDao dao;

    @Autowired
    ReactiveMongoOperations operations;

    public Mono<ServerResponse> getByDni(ServerRequest request) {
        return dao.countByDni(request.pathVariable("dni"))
                .flatMap(
                        aLong -> aLong>0?
                                dao.getPersonByDni(request.pathVariable("dni")).flatMap(AppResponse::AppResponseOk):
                                Mono.just(captchaResolv.getDni(request.pathVariable("dni"), tessdata, lang)).flatMap(
                                       person -> dao.countByRucUser(person.getRucUser()).flatMap(
                                           e->e>0?
                                            dao.getPersonByRucUser(person.getRucUser()).flatMap(personFinded->dao.save(personFinded.setDni(person.getDni())).flatMap(AppResponse::AppResponseOk))
                                            :dao.save(person).flatMap(AppResponse::AppResponseOk)
                                    )
                                )
                ).onErrorResume(e -> AppResponse.AppResponseError(e));
    }

    public Mono<ServerResponse> getByRuc(ServerRequest request) {
        return dao.countByRucUser(request.pathVariable("ruc"))
                .flatMap(
                        aLong -> aLong > 0 ?
                                dao.getPersonByRucUser(request.pathVariable("ruc")).flatMap(AppResponse::AppResponseOk) :
                                dao.insert(captchaResolv.getRuc(request.pathVariable("ruc"), tessdata, lang)).flatMap(AppResponse::AppResponseOk)
                ).onErrorResume(e -> AppResponse.AppResponseError(e));
    }

    public Mono<ServerResponse> inertPerson(ServerRequest request) {

        Flux<Person> people = request.bodyToFlux(Person.class).flatMap(dao::insert);
        return ok().body(people, Person.class);
    }
    public Mono<ServerResponse> findPersonByText(ServerRequest request) {
        Flux<Person> people = request.bodyToFlux(Person.class);
        return dao.findAllByAllNameContainingOrRucUserContaining(request.pathVariable("text"),request.pathVariable("text"),PageRequest.of(0,100)).limitRequest(10).collectList().flatMap(
                e->AppResponse.AppResponseOk(e)
        ).onErrorResume(e -> AppResponse.AppResponseError(e));
    }
}
