package innovar.io.grifo.config.ExceptionHandling;

import innovar.io.grifo.config.AppError;

/**
 * The UserNotFountExecptions class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :20/03/2018
 */
public class ObjetcNotFoundException extends BaseException {

    public ObjetcNotFoundException(String key, String value) {
        super(
                new AppError()
                        .setErrorNameException(ObjetcNotFoundException.class.getSimpleName())
                        .setParamKey(key)
                        .setParamValue(value)
        );
    }

    public ObjetcNotFoundException() {
        super(
                new AppError()
                        .setErrorNameException(ObjetcNotFoundException.class.getSimpleName())
                        .setParamKey(" ")
                        .setParamValue(" ")
        );
    }

}