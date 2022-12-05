package cl.ionix.prueba.application.port.interactor;

import cl.ionix.prueba.application.dto.busqueda.BusquedaDto;
import cl.ionix.prueba.application.dto.busqueda.ParamDto;

public interface IBusquedaService {
    BusquedaDto buscar(ParamDto param);
}
