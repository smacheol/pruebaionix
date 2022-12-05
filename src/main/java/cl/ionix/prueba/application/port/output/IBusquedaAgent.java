package cl.ionix.prueba.application.port.output;

import cl.ionix.prueba.application.dto.busqueda.BusquedaResponse;
import org.springframework.http.ResponseEntity;

public interface IBusquedaAgent {
    ResponseEntity<BusquedaResponse> buscar(String parametro);
}
