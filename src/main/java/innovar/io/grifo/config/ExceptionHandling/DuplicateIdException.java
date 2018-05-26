package innovar.io.grifo.config.ExceptionHandling;


import innovar.io.grifo.config.AppError;

/**
 * The DuplicateIdException class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :25/03/2018
 */
public class DuplicateIdException extends BaseException {
    public DuplicateIdException(String id) {
        super(
                new AppError()
                        .setParamKey(id)
                        .setParamValue(" ")
                        .setErrorNameException(DuplicateIdException.class.getSimpleName())
        );
    }
}
