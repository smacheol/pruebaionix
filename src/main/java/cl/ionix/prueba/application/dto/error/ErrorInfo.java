package cl.ionix.prueba.application.dto.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorInfo {
    @JsonProperty("message")
    private String message;
    @JsonProperty("code")
    private String errorCode;
    @JsonProperty("statusCode")
    private int statusCode;
    @JsonProperty("uri")
    private String uriRequested;
}
