/**
 * The UbigeoService class is implemment to
 *
 * @version :1.0
 * @Author :Warren
 * @since :28/05/2018
 */
package innovar.io.grifo.service;

import innovar.io.grifo.config.AppResponse;
import innovar.io.grifo.entity.Ubigeo;
import innovar.io.grifo.repository.UbigeoDao;
import innovar.io.grifo.repository.UbigeoDaoImpl;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Service
public class UbigeoService {

    @Autowired
    UbigeoDaoImpl dao;

    @Autowired
    UbigeoDao ubigeoDao;
    public Mono<ServerResponse> addAll(ServerRequest request){
        return ok().body( request.bodyToFlux(Ubigeo.class).flatMap(
                ubigeoDao::insert
        ),Ubigeo.class);
    }
    public Mono<ServerResponse> getDepartamanets(ServerRequest request){
        return dao.getDepartament()
                .collectList()
                .flatMap(
                        AppResponse::AppResponseOk
                );
    }
    public Mono<ServerResponse> getProvinces(ServerRequest request){
        return dao.getProvince(request.pathVariable("pro")).collectList()
                .flatMap(
                        AppResponse::AppResponseOk
                );
    }
    public Mono<ServerResponse> getDistritos(ServerRequest request){
        return dao.getDistrict(request.pathVariable("dis")).collectList()
                .flatMap(
                        AppResponse::AppResponseOk
                );
    }
}
