package cl.ionix.prueba.infrastructure.controller;

import cl.ionix.prueba.application.GenericException;
import cl.ionix.prueba.application.dto.error.ErrorInfo;
import cl.ionix.prueba.application.dto.error.ErrorResponse;
import cl.ionix.prueba.application.port.input.IErrorHandlerController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorHandlerController implements IErrorHandlerController {

    private ErrorResponse obtenerError(HttpStatus httpStatus, String errorMessage, String errorCode, String requestUri) {
        ErrorResponse error = new ErrorResponse();
        ErrorInfo errorInfo = ErrorInfo.builder()
                .statusCode(httpStatus.value())
                .message(errorMessage)
                .errorCode(errorCode)
                .uriRequested(requestUri)
                .build();
        error.setError(errorInfo);
        return error;
    }

    @Override
    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ErrorResponse> genericExceptionHandler(HttpServletRequest request, GenericException e) {
        ErrorResponse error = obtenerError(e.getHttpStatus(), e.getMessage(),
                e.getErrorCode(), request.getRequestURI());
        return new ResponseEntity<>(error, e.getHttpStatus());
    }

}
