package cl.ionix.prueba.application.port.input;

import cl.ionix.prueba.application.dto.usuario.UsuarioDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface IUsuarioController {
    ResponseEntity<List<UsuarioDto>> listar();
    ResponseEntity<UsuarioDto> guardar(@RequestBody @Valid UsuarioDto usuarioDto);
    ResponseEntity<UsuarioDto> obtenerPorEmail(@PathVariable String email);
    ResponseEntity<Void> eliminar(@PathVariable Long id);
}
