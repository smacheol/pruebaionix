package cl.ionix.prueba.infrastructure.agent.busqueda;

import cl.ionix.prueba.application.GenericException;
import cl.ionix.prueba.application.dto.busqueda.BusquedaResponse;
import cl.ionix.prueba.infrastructurecross.application.Setting;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class BusquedaAgentTest {

    @Mock
    private Setting setting;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private BusquedaAgent busquedaAgent;

    @BeforeEach
    public void setUp() {
        when(setting.getValorApiKey()).thenReturn("valor");
        when(setting.getUrlServicioBusqueda()).thenReturn("url/%s");
    }

    @Test
    void buscar() {
        BusquedaResponse busquedaResponse = new BusquedaResponse();
        when(restTemplate.exchange(anyString(),
                any(HttpMethod.class),
                ArgumentMatchers.<HttpEntity<Void>>any(),
                ArgumentMatchers.<Class<BusquedaResponse>>any())).thenReturn(ResponseEntity.ok(busquedaResponse));
        Assertions.assertDoesNotThrow(() -> busquedaAgent.buscar("param"));
    }

    @Test
    void buscarError() {
        when(restTemplate.exchange(anyString(),
                any(HttpMethod.class),
                ArgumentMatchers.<HttpEntity<Void>>any(),
                ArgumentMatchers.<Class<BusquedaResponse>>any())).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "ERROR"));
        Assertions.assertThrows(GenericException.class, () -> busquedaAgent.buscar("param"));
    }
}