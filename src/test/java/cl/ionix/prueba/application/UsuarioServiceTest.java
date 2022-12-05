package cl.ionix.prueba.application;

import cl.ionix.prueba.application.dto.usuario.UsuarioDto;
import cl.ionix.prueba.application.port.output.IUsuarioRepository;
import cl.ionix.prueba.domain.entity.Usuario;
import cl.ionix.prueba.domain.enums.ErrorEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class UsuarioServiceTest {

    @Mock
    private IUsuarioRepository usuarioRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void listar() {
        when(usuarioRepository.findAll()).thenReturn(Collections.singletonList(getUsuario()));
        Assertions.assertEquals(1, usuarioService.listar().size());
    }

    @Test
    void guardar() {
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(getUsuario());
        Assertions.assertDoesNotThrow(() -> usuarioService.guardar(new UsuarioDto()));
    }

    @Test
    void guardarErrorUniqueKey() {
        when(usuarioRepository.save(any(Usuario.class))).thenThrow(new DataIntegrityViolationException("Error unique key"));
        try {
            usuarioService.guardar(new UsuarioDto());
        } catch (GenericException ge) {
            Assertions.assertEquals(ErrorEnum.USUARIO_NO_GUARDADO.getCodigo(), ge.getErrorCode());
        }
    }

    @Test
    void eliminar() {
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(getUsuario()));
        Assertions.assertDoesNotThrow(() -> usuarioService.eliminar(1L));
    }

    @Test
    void eliminarNoEntrado() {
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());
        try {
            usuarioService.eliminar(1L);
        } catch (GenericException ge) {
            Assertions.assertEquals(ErrorEnum.USUARIO_NO_ENCONTRADO.getCodigo(), ge.getErrorCode());
        }
    }

    @Test
    void obtenerPorEmail() {
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setEmail("email");
        when((usuarioRepository.findByEmail(anyString()))).thenReturn(Optional.of(getUsuario()));
        when(modelMapper.map(any(Usuario.class), any())).thenReturn(usuarioDto);
        UsuarioDto res = usuarioService.obtenerPorEmail("email");
        Assertions.assertEquals("email", res.getEmail());
    }

    @Test
    void obtenerPorEmailNoEncontrado() {
        when(usuarioRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        try {
            usuarioService.obtenerPorEmail("email");
        } catch (GenericException ge) {
            Assertions.assertEquals(ErrorEnum.USUARIO_NO_ENCONTRADO.getCodigo(), ge.getErrorCode());
        }
    }

    private Usuario getUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setEmail("email");
        usuario.setName("nombre");
        usuario.setPhone("988888888");
        usuario.setUsername("username");
        return usuario;
    }
}