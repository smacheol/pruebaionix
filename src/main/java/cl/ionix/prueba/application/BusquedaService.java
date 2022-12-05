package cl.ionix.prueba.application;

import cl.ionix.prueba.application.dto.busqueda.BusquedaDto;
import cl.ionix.prueba.application.dto.busqueda.BusquedaResponse;
import cl.ionix.prueba.application.dto.busqueda.ParamDto;
import cl.ionix.prueba.application.port.interactor.IBusquedaService;
import cl.ionix.prueba.application.port.interactor.IDESService;
import cl.ionix.prueba.application.port.output.IBusquedaAgent;
import cl.ionix.prueba.domain.enums.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BusquedaService implements IBusquedaService {

    @Autowired
    private IDESService desService;

    @Autowired
    private IBusquedaAgent busquedaAgent;

    @Override
    public BusquedaDto buscar(ParamDto param) {
        BusquedaDto busquedaDto = new BusquedaDto();
        String cifrado = desService.cifrar(param.getParametro());

        long inicio = System.currentTimeMillis();
        ResponseEntity<BusquedaResponse> response = busquedaAgent.buscar(cifrado);
        long tiempoResponse = System.currentTimeMillis() - inicio;
        BusquedaResponse busquedaResponse = response.getBody();

        if (response.getStatusCode().value() != 200 || busquedaResponse == null) {
            throw new GenericException(ErrorEnum.SERVICIO_BUSQUEDA, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        BusquedaDto.Result result = new BusquedaDto.Result();
        result.setRegisterCount(busquedaResponse.getResult().getItems().size());
        busquedaDto.setDescription(busquedaResponse.getDescription());
        busquedaDto.setResponseCode(busquedaResponse.getResponseCode());
        busquedaDto.setElapsedTime(tiempoResponse);
        busquedaDto.setResult(result);

        return busquedaDto;
    }


}
