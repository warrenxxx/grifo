/**
 * The ProductController class is implemment to
 *
 * @version :1.0
 * @Author :Warren
 * @since :28/05/2018
 */
package innovar.io.grifo.controller;

import innovar.io.grifo.security.Jwt;
import innovar.io.grifo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ProductController {
    @Autowired
    Jwt jwt;
    @Bean
    RouterFunction<ServerResponse> ProductFunction(ProductService service){
        return route(POST("/product"),service::saveProduct)
                .andRoute(GET("/product/{text}"),service::findProduct)
                .andRoute(GET("/product"),service::findAllProduct)
                .andRoute(DELETE("/product/{id}"),service::eliminarProduct)
                .andRoute(GET("/product/delete/{id}"),service::eliminarProduct)
                .andRoute(GET("/product/inability/{id}"),service::inabiliteProduct)
                .andRoute(GET("/product/habiliment/{id}"),service::habliteProduct)
                .filter(jwt::verifyFunctions);
    }
}
