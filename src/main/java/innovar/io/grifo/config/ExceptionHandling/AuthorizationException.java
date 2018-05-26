package innovar.io.grifo.config.ExceptionHandling;


import innovar.io.grifo.config.AppError;

/**
 * The AuthorizationException class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :23/03/2018
 */
public class AuthorizationException extends BaseException {
    public AuthorizationException(String userName) {
        super(
                new AppError()
                        .setParamValue("userName")
                        .setParamValue(userName)
                        .setErrorNameException(AuthorizationException.class.getSimpleName())
        );
    }

    public AuthorizationException() {
        super(
                new AppError()
                        .setParamValue("Authorization")
                        .setParamValue("0")
                        .setErrorNameException(AuthorizationException.class.getSimpleName())
        );
    }

    public AuthorizationException(String key, String value) {
        super(
                new AppError()
                        .setParamValue(key)
                        .setParamValue(value)
                        .setErrorNameException(AuthorizationException.class.getSimpleName())
        );
    }
}
