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
import innovar.io.grifo.repository.PersonDao;
import innovar.io.grifo.security.UserMetadate;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.File;

import static innovar.io.grifo.security.Jwt.OBJECT_USER;

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

    public Mono<ServerResponse> getByDni(ServerRequest request) {
//        ObjectId idUser = ((UserMetadate) request.attributes().get(OBJECT_USER)).getId();
        return dao.getPersonByDni(request.pathVariable("dni")).flatMap(
                e->AppResponse.AppResponseOk(e)
        ).switchIfEmpty(
            dao.insert(
                captchaResolv.getDni(request.pathVariable("dni"),tessdata,lang)
            ).flatMap(
                e->AppResponse.AppResponseOk(e)
            )
        ).onErrorResume(e -> AppResponse.AppResponseError(e));
    }

    public Mono<ServerResponse> getByRuc(ServerRequest request) {
  //      ObjectId idUser = ((UserMetadate) request.attributes().get(OBJECT_USER)).getId();
        dao.getPersonByRucUser(request.pathVariable("ruc")).subscribe(System.out::println);
        return dao.getPersonByRucUser(request.pathVariable("ruc")).flatMap(
                e->AppResponse.AppResponseOk(e)
        ).switchIfEmpty(
                dao.insert(
                        captchaResolv.getRuc(request.pathVariable("ruc"),tessdata,lang)
                ).flatMap(
                        e->AppResponse.AppResponseOk(e)
                )
        ).onErrorResume(e -> AppResponse.AppResponseError(e));
    }
}
