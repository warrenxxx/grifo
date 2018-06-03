package innovar.io.grifo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import innovar.io.grifo.config.AppResponse;
import innovar.io.grifo.config.ExceptionHandling.AuthorizationException;
import innovar.io.grifo.config.ExceptionHandling.HeaderException;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.HashMap;
import java.util.stream.Stream;


/**
 * The Jwt class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :20/03/2018
 */
@Service
public class Jwt {


    private static final String ID_USER = "_id";


    public static final String ROLE_USER = "ROLE";
    public static final String OBJECT_USER = "OBJECT_USER";


    public static String TO_JWT(String _id, String role) {

        try {
            return JWT.create()
                    .withIssuer("auth0")
                    .withHeader(
                            new HashMap(){{
                                put(ID_USER, _id);
                                put(ROLE_USER, role);
                            }}
                        )
                    .sign(Algorithm.HMAC256("SECRETOo"));
        } catch (Exception exception) {
            return "";
        }
    }

    public Mono<ServerResponse> verifyFunctions(ServerRequest req, HandlerFunction<ServerResponse> next) {
        System.out.println("warrena");
        Stream.of(req.headers().header("Authorization").toArray(new String[0])).forEach(System.out::println);
        System.out.println("warrenb");

        return Flux.just(req.headers().header("Authorization").toArray(new String[0]))
                .limitRequest(1)
                .map(JWT::decode)
                .flatMap(
                        jwt -> {
                            req.attributes().put(
                                    OBJECT_USER,
                                    new UserMetadate(new ObjectId(jwt.getHeaderClaim(ID_USER).asString()),jwt.getHeaderClaim(ROLE_USER).asString())
                            );
                            return
                                    next.handle(req) ;
                        }
                )
                .switchIfEmpty(Mono.error(new HeaderException("Authorization")))
                .publishNext()
                .onErrorResume(AppResponse::AppResponseError)
                ;
    }


    public static Mono<String> getIdOfJwtToken(ServerRequest req) {
        return Flux.just(req.headers().header("Authorization").toArray(new String[0]))
                .limitRequest(1)
                .map(e -> JWT.decode(e))
                .map(e -> e.getHeaderClaim(ID_USER).asString())
                .switchIfEmpty(Mono.error(new HeaderException("Authorization")))
                .publishNext()
                ;

    }
}
