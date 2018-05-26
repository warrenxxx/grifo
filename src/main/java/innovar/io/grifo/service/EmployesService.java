/**
 * The EmployesService class is implemment to
 *
 * @version :1.0
 * @Author :Warren
 * @since :26/05/2018
 */
package innovar.io.grifo.service;

import innovar.io.grifo.config.AppResponse;
import innovar.io.grifo.config.ExceptionHandling.UserNotFoundException;
import innovar.io.grifo.dto.LoginDto;
import innovar.io.grifo.dto.RequestUserDto;
import innovar.io.grifo.dto.ResponseUserDto;
import innovar.io.grifo.entity.User;
import innovar.io.grifo.repository.EmployesDao;
import innovar.io.grifo.security.UserMetadate;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static innovar.io.grifo.security.Jwt.OBJECT_USER;
import static innovar.io.grifo.security.Jwt.TO_JWT;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class EmployesService {

    @Autowired
    EmployesDao dao;

    @Autowired
    ReactiveMongoOperations reactiveMongoOperations;

    public Mono<ServerResponse> createEmployes(ServerRequest request){
        ObjectId idUser=((UserMetadate) request.attributes().get(OBJECT_USER)).getId();
        ObjectId idEmploye=new ObjectId();
        return  request.bodyToMono(RequestUserDto.class).flatMap(
                    requestUserDto->dao.insert(requestUserDto.getEmployes().set_id(idEmploye.toString())).flatMap(
                            user ->reactiveMongoOperations.update(User.class).matching(new Query(where("_id").is(idUser))).apply(new Update().push("employes",idEmploye)).first()
                            .flatMap(
                                    e->AppResponse.AppResponseOk()
                            )
                    )
            ).onErrorResume(e->AppResponse.AppResponseError(e));
    }
    public Mono<ServerResponse> loginEmploye(ServerRequest request){
        return  request.bodyToMono(LoginDto.class).flatMap(
                loginDto -> dao.getUserByPasswordAndUserName(loginDto.getPassword(),loginDto.getUser()).flatMap(
                        employe ->AppResponse.AppResponseOk(new ResponseUserDto(
                                employe.get_id(),
                                employe.getAllName(),
                                employe.getBirthDate(),
                                employe.getEmail(),
                                employe.getUserName(),
                                TO_JWT(employe.get_id(),"employe"),
                                null
                        ))
                ).switchIfEmpty(Mono.error(new UserNotFoundException()))
        ).onErrorResume(e->AppResponse.AppResponseError(e));
    }
}
