package cl.ionix.prueba.application.dto.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    @JsonProperty("error")
    private ErrorInfo error;
}
