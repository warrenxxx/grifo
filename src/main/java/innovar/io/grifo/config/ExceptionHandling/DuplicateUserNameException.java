package innovar.io.grifo.config.ExceptionHandling;


import innovar.io.grifo.config.AppError;

public class DuplicateUserNameException extends BaseException {

    public DuplicateUserNameException(String userName) {
        super(
                new AppError().setErrorNameException(DuplicateUserNameException.class.getSimpleName())
                        .setParamKey("userName")
                        .setParamValue(userName)

        );
    }
}
