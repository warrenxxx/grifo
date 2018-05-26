package innovar.io.grifo.config;

import innovar.io.grifo.config.ExceptionHandling.BaseException;
import innovar.io.grifo.config.ExceptionHandling.SystemException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;

import static org.springframework.web.reactive.function.server.ServerResponse.badRequest;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * The response class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :18/03/2018
 */

public class AppResponse {
    public Object data;
    public AppError error;

    public AppResponse(Object data, Throwable error) {
        if (data == null) {
            if (error instanceof BaseException)
                this.error = ((BaseException) error).appError;
            else
                this.error = new SystemException("System", "error", error.getMessage() + " " + error.getLocalizedMessage()).appError;
        } else {
            this.data = data;
        }
    }


    /*
        public AppResponse(Object data)
        {
            this.data = data;
        }

        public AppResponse(Throwable e){
            if(e instanceof BaseException)
                this.error=((BaseException)e).appError;
            else
                this.error=new SystemException("System","error",e.getMessage()).appError;
        }
    */
    public static Mono<ServerResponse> AppResponseError(Throwable t) {
        return badRequest()
                .body(
                        Mono.just(
                                new AppResponse(null, t)
                        )
                        , AppResponse.class
                );
    }

    public static Mono<ServerResponse> AppResponseOk(Object responseObject) {
        return ok()
                .body(
                        Mono.just(new AppResponse(responseObject, null))
                        , AppResponse.class
                );
    }
    public static Mono<ServerResponse> AppResponseCountOk(String name, Long count) {
        return ok()
                .body(
                        Mono.just(new AppResponse(new CountObject(name,count), null))
                        , AppResponse.class
                );
    }

    public static Mono<ServerResponse> AppResponseOkMono(Mono object) {
        return ok()
                .body(
                        object.map(e -> new AppResponse(e, null))
                        , AppResponse.class
                );
    }


    public static Mono<ServerResponse> AppResponseOk() {
        return ok()
                .body(
//                        Mono.just(new AppResponse(Map.of("message", "successful operation"), null))
                          Mono.just(new AppResponse(new HashMap(){{put("message","successful operation");}}, null))
                       , AppResponse.class
                );
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class CountObject{
    private String name;
    private Long count;
}