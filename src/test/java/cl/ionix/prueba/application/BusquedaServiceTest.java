package cl.ionix.prueba.application;

import cl.ionix.prueba.application.dto.busqueda.BusquedaDto;
import cl.ionix.prueba.application.dto.busqueda.BusquedaResponse;
import cl.ionix.prueba.application.dto.busqueda.ParamDto;
import cl.ionix.prueba.domain.enums.ErrorEnum;
import cl.ionix.prueba.infrastructure.agent.busqueda.BusquedaAgent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class BusquedaServiceTest {

    @Mock
    DESService desService;

    @Mock
    BusquedaAgent busquedaAgent;

    @InjectMocks
    private BusquedaService busquedaService;

    @Test
    void buscar() {
        BusquedaResponse response = new BusquedaResponse();
        BusquedaResponse.Result result = new BusquedaResponse.Result();
        BusquedaResponse.Item item = new BusquedaResponse.Item();
        result.setItems(Collections.singletonList(item));
        response.setResult(result);
        ParamDto paramDto = new ParamDto();
        paramDto.setParametro("param");
        when(desService.cifrar(anyString())).thenReturn("cifrado");
        when(busquedaAgent.buscar(anyString())).thenReturn(new ResponseEntity<>(response, HttpStatus.OK));
        BusquedaDto busquedaDto = busquedaService.buscar(paramDto);
        Assertions.assertEquals(1, busquedaDto.getResult().getRegisterCount());
    }

    @Test
    void buscarError() {
        ParamDto paramDto = new ParamDto();
        paramDto.setParametro("param");
        when(desService.cifrar(anyString())).thenReturn("cifrado");
        when(busquedaAgent.buscar(anyString())).thenReturn(new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
        try {
            busquedaService.buscar(paramDto);
        } catch (GenericException ge) {
            Assertions.assertEquals(ErrorEnum.SERVICIO_BUSQUEDA.getCodigo(), ge.getErrorCode());
        }

    }
}