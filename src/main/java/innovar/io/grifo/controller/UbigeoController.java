/**
 * The UbigeoController class is implemment to
 *
 * @version :1.0
 * @Author :Warren
 * @since :28/05/2018
 */
package innovar.io.grifo.controller;

import innovar.io.grifo.service.UbigeoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UbigeoController {
    @Bean
    public RouterFunction<ServerResponse> ubigeoFunction(UbigeoService service){
        return route(POST("/ubigeo"),service::addAll)
                .andRoute(GET("/ubigeo/departaments"),service::getDepartamanets)
                .andRoute(GET("/ubigeo/provinces/{pro}"),service::getProvinces)
                .andRoute(GET("/ubigeo/district/{dis}"),service::getDistritos);
    }
}
