/**
 * The ProductService class is implemment to
 *
 * @version :1.0
 * @Author :Warren
 * @since :28/05/2018
 */
package innovar.io.grifo.service;

import innovar.io.grifo.config.AppResponse;
import innovar.io.grifo.config.ExceptionHandling.AuthorizationException;
import innovar.io.grifo.entity.Product;
import innovar.io.grifo.repository.ProductDao;
import innovar.io.grifo.repository.UserDao;
import innovar.io.grifo.security.UserMetadate;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static innovar.io.grifo.security.Jwt.OBJECT_USER;

@Service
public class ProductService {
    @Autowired
    ProductDao dao;

    @Autowired
    UserDao userDao;

    public Mono<ServerResponse> saveProduct(ServerRequest request) {
        ObjectId idUser = ((UserMetadate) request.attributes().get(OBJECT_USER)).getId();
        return userDao.findById(idUser.toString()).flatMap(
                user -> user.getRole().compareTo("user") == 0 ?
                        request.bodyToMono(Product.class).flatMap(
                                product -> dao.save(product.setIsActive(true).setStock(0L)).flatMap(
                                        productSved -> AppResponse.AppResponseOk()
                                )
                        ) :
                        Mono.error(new AuthorizationException())
        ).onErrorResume(AppResponse::AppResponseError);
    }

    public Mono<ServerResponse> eliminarProduct(ServerRequest request) {
        ObjectId idUser = ((UserMetadate) request.attributes().get(OBJECT_USER)).getId();
        return userDao.findById(idUser.toString()).flatMap(
                user -> user.getRole().compareTo("user") == 0 ?
                        dao.removeBy_id(request.pathVariable("id")).flatMap(
                                aVoid -> AppResponse.AppResponseOk()
                        )
                        :
                        Mono.error(new AuthorizationException())
        ).onErrorResume(AppResponse::AppResponseError);
    }
    public Mono<ServerResponse> inabiliteProduct(ServerRequest request) {
        ObjectId idUser = ((UserMetadate) request.attributes().get(OBJECT_USER)).getId();
        return userDao.findById(idUser.toString()).flatMap(
                user -> user.getRole().compareTo("user") == 0 ?
                        dao.findById(request.pathVariable("id")).flatMap(
                                product -> dao.save(product.setIsActive(false)).flatMap(
                                        productSaved->AppResponse.AppResponseOk()
                                )

                        )
                        :
                        Mono.error(new AuthorizationException())
        ).onErrorResume(AppResponse::AppResponseError);
    }
    public Mono<ServerResponse> habliteProduct(ServerRequest request) {
        ObjectId idUser = ((UserMetadate) request.attributes().get(OBJECT_USER)).getId();
        return userDao.findById(idUser.toString()).flatMap(
                user -> user.getRole().compareTo("user") == 0 ?
                        dao.findById(request.pathVariable("id")).flatMap(
                                product -> dao.save(product.setIsActive(true)).flatMap(
                                        productSaved->AppResponse.AppResponseOk()
                                )

                        )
                        :
                        Mono.error(new AuthorizationException())
        ).onErrorResume(AppResponse::AppResponseError);
    }

    public Mono<ServerResponse> eliminarProduct2(ServerRequest request) {
        ObjectId idUser = ((UserMetadate) request.attributes().get(OBJECT_USER)).getId();
        return userDao.findById(idUser.toString()).flatMap(
                user -> user.getRole().compareTo("user") == 0 ?
                        dao.removeBy_id(request.pathVariable("id")).flatMap(
                                aVoid -> AppResponse.AppResponseOk()
                        )
                        :
                        Mono.error(new AuthorizationException())
        ).onErrorResume(AppResponse::AppResponseError);
    }

    public Mono<ServerResponse> findProduct(ServerRequest request) {
        ObjectId idUser = ((UserMetadate) request.attributes().get(OBJECT_USER)).getId();
        String p = request.pathVariable("text");
        return dao.findAllByCodeContainingOrDescriptionContaining(p, p, p).collectList().flatMap(AppResponse::AppResponseOk
        ).onErrorResume(AppResponse::AppResponseError);
    }

    public Mono<ServerResponse> findAllProduct(ServerRequest request) {
        ObjectId idUser = ((UserMetadate) request.attributes().get(OBJECT_USER)).getId();
        return dao.findAll().collectList().flatMap(AppResponse::AppResponseOk
        ).onErrorResume(AppResponse::AppResponseError);
    }


}
