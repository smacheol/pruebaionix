package cl.ionix.prueba.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorEnum {

    USUARIO_NO_ENCONTRADO("Error. Usuario no encontrado", "PRU-01"),
    USUARIO_NO_GUARDADO("Error. Usuario no guardado", "PRU-02"),
    CIFRAR("Error. Error al cifrar", "PRU-03");

    private String mensaje;
    private String codigo;
}
