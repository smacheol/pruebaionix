package cl.ionix.prueba.application.dto.busqueda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel(description = "Parametro entrada servicio busqueda")
public class ParamDto {

    @NotNull
    @ApiModelProperty("Valor entrada")
    private String parametro;
}
