/**
 * The UserController class is implemment to
 *
 * @version :1.0
 * @Author :Warren
 * @since :26/05/2018
 */
package innovar.io.grifo.controller;

import innovar.io.grifo.security.Jwt;
import innovar.io.grifo.service.EmployesService;
import innovar.io.grifo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UserController {
        @Autowired
        Jwt jwt;

    @Bean
    RouterFunction UserfunctionSinFilter(UserService service){
        return route(POST("/authenticate/register"),service::userCreate)
                .andRoute(POST("/authenticate/login"),service::login);
    }
    @Bean
    RouterFunction Userfunction(EmployesService service){
        return route(POST("/authenticate/register/employes"),service::createEmployes).filter(jwt::verifyFunctions);
    }
    @Bean
    RouterFunction EmplyefunctionSinFilter(EmployesService service){
        return route(POST("/authenticate/login/employes"),service::loginEmploye);
    }


}
