package cl.ionix.prueba.application.dto.busqueda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "Contiene informacion busqueda")
public class BusquedaDto {

    @ApiModelProperty("Codigo respuesta")
    private int responseCode;

    @ApiModelProperty("Descripcion respuesta")
    private String description;

    @ApiModelProperty("Tiempo ejecucion")
    private long elapsedTime;

    @ApiModelProperty("Resultado")
    private Result result;

    @Getter
    @Setter
    @ApiModel(description = "Contiene informacion numero de registros")
    public static class Result {
        @ApiModelProperty("Cantidad de registros")
        private long registerCount;
    }
}
