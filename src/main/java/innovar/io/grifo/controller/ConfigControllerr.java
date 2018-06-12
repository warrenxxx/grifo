/**
 * The PojoControllerr class is implemment to
 *
 * @version :1.0
 * @Author :Warren
 * @since :28/05/2018
 */
package innovar.io.grifo.controller;

import innovar.io.grifo.service.ConfigService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ConfigControllerr {
    @Bean
    RouterFunction<ServerResponse> ConfigSinFilter(ConfigService service){
        return route(POST("/config"),service::add)
                .andRoute(PUT("/config"),service::add)
                .andRoute(GET("/config"),service::get);
    }
}
