/**
 * The PojoService class is implemment to
 *
 * @version :1.0
 * @Author :Warren
 * @since :28/05/2018
 */
package innovar.io.grifo.service;

import innovar.io.grifo.entity.Pojo;
import innovar.io.grifo.repository.PojoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Service
public class PojoService {

    @Autowired
    PojoDao pojoDao;

    public Mono<ServerResponse> add (ServerRequest request){
        return request.bodyToMono(Pojo.class).flatMap(
                e->ok().body(pojoDao.save(e),Pojo.class)
        );
    }

}
