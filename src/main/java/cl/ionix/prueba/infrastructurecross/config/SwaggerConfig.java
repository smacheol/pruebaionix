package cl.ionix.prueba.infrastructurecross.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${spring.application.name}")
    private String nombreAplicacion;

    @Value("${spring.application.description}")
    private String descripcion;

    @Value("${spring.application.base-package}")
    private String packageBase;

    @Value("${spring.app.version}")
    private String version;

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage(packageBase)).build()
                .useDefaultResponseMessages(false)
                .apiInfo(new ApiInfoBuilder()
                        .title(nombreAplicacion)
                        .description(descripcion)
                        .version(version)
                        .build())
                .tags(new Tag("UsuarioController", "Encargado de operaciones relacionadas con usuarios"),
                        new Tag("BusquedaController", "Encargado de consumir servicio con parametro cifrado"));
    }
}
