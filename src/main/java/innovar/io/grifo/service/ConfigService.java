/**
 * The PojoService class is implemment to
 *
 * @version :1.0
 * @Author :Warren
 * @since :28/05/2018
 */
package innovar.io.grifo.service;

import com.sun.org.apache.regexp.internal.RE;
import innovar.io.grifo.config.AppResponse;
import innovar.io.grifo.entity.Configt;
import innovar.io.grifo.repository.ConfigDao;
import innovar.io.grifo.repository.EmployesDao;
import innovar.io.grifo.repository.UserDao;
import innovar.io.grifo.security.UserMetadate;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static innovar.io.grifo.security.Jwt.OBJECT_USER;
import static innovar.io.grifo.security.Jwt.ToUserMetadate;

@Service
public class ConfigService {

    @Autowired
    ConfigDao dao;

    @Autowired
    UserDao userDao;


    public Mono<ServerResponse> add (ServerRequest request){
        return request.bodyToMono(Configt.class).flatMap(e->dao.save(e).flatMap(AppResponse::AppResponseOk));
    }

    public Mono<ServerResponse> get (ServerRequest request){
        return dao.findAll().next().flatMap(AppResponse::AppResponseOk);
    }

    public Mono<ServerResponse> verifiToken(ServerRequest request) {
        String idUser = ToUserMetadate(request.pathVariable("token")).getId().toString();
        System.out.println(idUser);
        return userDao.findById(idUser).map(e->"ADMIN").switchIfEmpty(Mono.just("EMPLOYE")).flatMap(AppResponse::AppResponseOk);
    }
}
