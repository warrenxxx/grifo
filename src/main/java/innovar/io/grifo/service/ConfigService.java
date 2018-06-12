/**
 * The PojoService class is implemment to
 *
 * @version :1.0
 * @Author :Warren
 * @since :28/05/2018
 */
package innovar.io.grifo.service;

import innovar.io.grifo.config.AppResponse;
import innovar.io.grifo.entity.Config;
import innovar.io.grifo.entity.Pojo;
import innovar.io.grifo.repository.ConfigDao;
import innovar.io.grifo.repository.PojoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Service
public class ConfigService {

    @Autowired
    ConfigDao dao;

    public Mono<ServerResponse> add (ServerRequest request){
        return request.bodyToMono(Config.class).flatMap(e->dao.save(e).flatMap(AppResponse::AppResponseOk));
    }

    public Mono<ServerResponse> get (ServerRequest request){
        return dao.findAll().next().flatMap(AppResponse::AppResponseOk);
    }

}
