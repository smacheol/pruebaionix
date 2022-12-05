package cl.ionix.prueba.application.port.input;

import cl.ionix.prueba.application.GenericException;
import cl.ionix.prueba.application.dto.error.ErrorResponse;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface IErrorHandlerController {
    ResponseEntity<ErrorResponse> genericExceptionHandler(HttpServletRequest request, GenericException e);
}
