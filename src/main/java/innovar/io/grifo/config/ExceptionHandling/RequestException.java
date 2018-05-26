package innovar.io.grifo.config.ExceptionHandling;


import innovar.io.grifo.config.AppError;

/**
 * The AuthorizationException class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :23/03/2018
 */
public class RequestException extends BaseException {
    public RequestException() {
        super(
                new AppError()
                        .setParamKey("Request")
                        .setParamValue("Bad")
                        .setErrorNameException(RequestException.class.getSimpleName())
        );
    }
}
