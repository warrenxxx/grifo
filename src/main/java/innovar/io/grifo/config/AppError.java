package innovar.io.grifo.config;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * The AppError class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :18/03/2018
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter

public class AppError {

    public String errorCode;                //este es el codigo del error respuesta
    public String errorNameException;       //este es el nombre de error
    public String errorMessageControlado;   //error controlado
    public String errorMessageSystem;       //error no controlado
    public LocalDate errorDate;             //tiempo en el que se creo el error

    public Object data;                     //objeto si es que exisitiera
    public String paramKey;                 //parametro mediante el cualse reconoce al causante
    public String paramValue;               //parametro del valor del parametro del causante


    public String operation;                //operacion en el que se iso el error
//    public String resource;


    /**
     * Sets the errorCode.
     *
     * @param errorCode the errorCode to set
     * @return AppError
     */
    public AppError setErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    /**
     * Sets the errorNameException.
     *
     * @param errorNameException the errorNameException to set
     * @return AppError
     */
    public AppError setErrorNameException(String errorNameException) {
        this.errorNameException = errorNameException;
        return this;
    }


    /**
     * Sets the errorDate.
     *
     * @param errorDate the errorDate to set
     * @return AppError
     */
    public AppError setErrorDate(LocalDate errorDate) {
        this.errorDate = errorDate;
        return this;
    }

    /**
     * Sets the data.
     *
     * @param data the data to set
     * @return AppError
     */
    public AppError setData(Object data) {
        this.data = data;
        return this;
    }

    /**
     * Sets the paramKey.
     *
     * @param paramKey the paramKey to set
     * @return AppError
     */
    public AppError setParamKey(String paramKey) {
        this.paramKey = paramKey;
        return this;
    }

    /**
     * Sets the paramValue.
     *
     * @param paramValue the paramValue to set
     * @return AppError
     */
    public AppError setParamValue(String paramValue) {
        this.paramValue = paramValue;
        return this;
    }

    /**
     * Sets the operation.
     *
     * @param operation the operation to set
     * @return AppError
     */
    public AppError setOperation(String operation) {
        this.operation = operation;
        return this;
    }

    /**
     * Sets the errorMessageControlado.
     *
     * @param errorMessageControlado the errorMessageControlado to set
     * @return AppError
     */
    public AppError setErrorMessageControlado(String errorMessageControlado) {
        this.errorMessageControlado = errorMessageControlado;
        return this;
    }

    /**
     * Sets the errorMessageSystem.
     *
     * @param errorMessageSystem the errorMessageSystem to set
     * @return AppError
     */
    public AppError setErrorMessageSystem(String errorMessageSystem) {
        this.errorMessageSystem = errorMessageSystem;
        return this;
    }
}
