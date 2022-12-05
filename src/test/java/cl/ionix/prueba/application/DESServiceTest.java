package cl.ionix.prueba.application;

import cl.ionix.prueba.domain.enums.ErrorEnum;
import cl.ionix.prueba.infrastructurecross.application.Setting;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@SpringBootTest
class DESServiceTest {

    @Mock
    private Setting setting;

    @InjectMocks
    private DESService desService;

    @Test
    void cifrar() {
        when(setting.getDesLlave()).thenReturn("key123456");
        Assertions.assertDoesNotThrow(() -> desService.cifrar("1-9"));
    }

    @Test
    void cifrarError() {
        when(setting.getDesLlave()).thenReturn("key");
        try {
            desService.cifrar("1-9");
        } catch (GenericException ge) {
            Assertions.assertEquals(ErrorEnum.CIFRAR.getCodigo(), ge.getErrorCode());
        }
    }
}