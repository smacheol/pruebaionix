package cl.ionix.prueba.application.dto.usuario;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel(description = "Contiene informacion de un usuario")
public class UsuarioDto {

    @NotNull
    @ApiModelProperty("Nombre")
    private String name;

    @NotNull
    @ApiModelProperty("Nombre de usuario")
    private String username;

    @Email
    @ApiModelProperty("Correo electronico")
    private String email;

    @NotNull
    @ApiModelProperty("Telefono")
    private String phone;
}
