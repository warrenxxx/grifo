package innovar.io.grifo.config.ExceptionHandling;


import innovar.io.grifo.config.AppError;

/**
 * The HeaderException class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :24/03/2018
 */
public class HeaderException extends BaseException {

    public HeaderException(String header) {
        super(
                new AppError()
                        .setParamKey("Header")
                        .setParamValue(header)
                        .setErrorNameException(HeaderException.class.getSimpleName())
        );
    }
}
