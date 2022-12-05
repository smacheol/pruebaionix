package cl.ionix.prueba.infrastructurecross.application;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class Setting {

    @Value("${des.llave}")
    private String desLlave;
}
