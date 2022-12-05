package cl.ionix.prueba.infrastructure.agent.busqueda;

import cl.ionix.prueba.application.GenericException;
import cl.ionix.prueba.application.dto.busqueda.BusquedaResponse;
import cl.ionix.prueba.application.port.output.IBusquedaAgent;
import cl.ionix.prueba.domain.enums.ErrorEnum;
import cl.ionix.prueba.infrastructurecross.application.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class BusquedaAgent implements IBusquedaAgent {

    @Autowired
    private Setting setting;

    @Autowired
    private RestTemplate restTemplate;

    private static final String XAPIKEY = "X-API-Key";

    @Override
    public ResponseEntity<BusquedaResponse> buscar(String parametro) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(XAPIKEY, setting.getValorApiKey());
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        String url = String.format(setting.getUrlServicioBusqueda(), parametro);

        try {
            return restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    requestEntity,
                    BusquedaResponse.class
            );
        } catch (HttpClientErrorException ex) {
            throw new GenericException(ErrorEnum.SERVICIO_BUSQUEDA, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
