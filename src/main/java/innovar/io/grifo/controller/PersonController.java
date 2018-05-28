/**
 * The PersonController class is implemment to
 *
 * @version :1.0
 * @Author :Warren
 * @since :26/05/2018
 */
package innovar.io.grifo.controller;

import innovar.io.grifo.service.PersonService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class PersonController {

    @Bean
    RouterFunction<ServerResponse> PersonfunctionSinFilter(PersonService service){
        return route(GET("/person/dni/{dni}"),service::getByDni)
                .andRoute(GET("/person/ruc/{ruc}"),service::getByRuc)
                .andRoute(POST("/person"),service::inertPerson)
                .andRoute(GET("/person/text/{text}"),service::findPersonByText);
    }
}
