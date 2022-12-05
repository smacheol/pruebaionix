package cl.ionix.prueba.application.port.input;

import cl.ionix.prueba.application.dto.busqueda.BusquedaDto;
import cl.ionix.prueba.application.dto.busqueda.ParamDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface IBusquedaController {
    ResponseEntity<BusquedaDto> guardar(@RequestBody @Valid ParamDto paramDto);
}
