package cl.ionix.prueba.infrastructurecross.application;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class Setting {

    @Value("${des.llave}")
    private String desLlave;

    @Value("${servicio-busqueda.connect-time-out}")
    private int connectTimeOut;

    @Value("${servicio-busqueda.read-time-out}")
    private int readTimeOut;

    @Value("${servicio-busqueda.url}")
    private String urlServicioBusqueda;

    @Value("${servicio-busqueda.api-key}")
    private String valorApiKey;
}
