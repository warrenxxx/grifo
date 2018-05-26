/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package innovar.io.grifo.config.ExceptionHandling;


import innovar.io.grifo.config.AppError;

/**
 * The SystemException class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :24/03/2018
 */
public class WebException extends BaseException {
    public WebException(String key, String value, String message) {
        super(
                new AppError()
                        .setParamKey(key)
                        .setParamValue(value)
                        .setErrorNameException(WebException.class.getSimpleName())
                        .setErrorMessageSystem(message)
        );
    }

    public WebException(Throwable t) {
        super(
                new AppError()
                        .setParamKey(t.getMessage())
                        .setParamValue(t.getLocalizedMessage())
                        .setErrorNameException(WebException.class.getSimpleName())
                        .setErrorMessageSystem(t.getMessage())
        );
    }
}
