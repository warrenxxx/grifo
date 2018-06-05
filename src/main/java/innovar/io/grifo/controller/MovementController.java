/**
 * The MovementController class is implemment to
 *
 * @version :1.0
 * @Author :Warren
 * @since :26/05/2018
 */
package innovar.io.grifo.controller;

import innovar.io.grifo.security.Jwt;
import innovar.io.grifo.service.EmployesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class MovementController {
    @Autowired
    Jwt jwt;

    @Bean
    RouterFunction Employefunction(EmployesService service){
        return route(POST("/movement/ticker"),service::newTicket)
                .andRoute(POST("/movement/bill"),service::newBill)
                .andRoute(POST("/movement/print"),service::newPrint)
                .filter(jwt::verifyFunctions)
                ;
    }
}
