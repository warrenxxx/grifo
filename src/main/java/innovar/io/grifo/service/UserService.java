/**
 * The UserService class is implemment to
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
import innovar.io.grifo.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static innovar.io.grifo.security.Jwt.TO_JWT;

@Service
public class UserService {
    @Autowired
    UserDao dao;


    public Mono<ServerResponse> userCreate(ServerRequest request){
        return  request.bodyToMono(RequestUserDto.class).flatMap(
                requestUserDto->dao.insert(requestUserDto.getUser()).flatMap(
                        user -> AppResponse.AppResponseOk()
                )
        ).onErrorResume(e->AppResponse.AppResponseError(e));
    }


    public Mono<ServerResponse> login(ServerRequest request){
        return  request.bodyToMono(LoginDto.class).flatMap(
                loginDto -> dao.getUserByPasswordAndUserName(loginDto.getPassword(),loginDto.getUser()).flatMap(
                        user ->AppResponse.AppResponseOk(new ResponseUserDto(
                                user.get_id(),
                                user.getAllName(),
                                user.getBirthDate(),
                                user.getEmail(),
                                user.getUserName(),
                                TO_JWT(user.get_id(),user.getRole()),
                                user.getCompany()
                        ))
                ).switchIfEmpty(Mono.error(new UserNotFoundException()))
        ).onErrorResume(e->AppResponse.AppResponseError(e));
    }



}
