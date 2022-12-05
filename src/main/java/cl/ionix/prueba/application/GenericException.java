package cl.ionix.prueba.application;

import cl.ionix.prueba.domain.enums.ErrorEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
public class GenericException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -7405432357544406448L;

    private final String errorCode;
    private final HttpStatus httpStatus;

    public GenericException(ErrorEnum errorEnum, HttpStatus httpStatus) {
        super(errorEnum.getMensaje());
        this.errorCode = errorEnum.getCodigo();
        this.httpStatus = httpStatus;
    }
}