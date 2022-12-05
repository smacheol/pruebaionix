package cl.ionix.prueba.infrastructure.controller;

import cl.ionix.prueba.application.dto.usuario.UsuarioDto;
import cl.ionix.prueba.application.port.input.IUsuarioController;
import cl.ionix.prueba.application.port.interactor.IUsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuario")
@Api(tags = "UsuarioController")
public class UsuarioController implements IUsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @ApiOperation(value = "Api para obtener el listado de usuarios")
    @GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<List<UsuarioDto>> listar() {
        return ResponseEntity.ok(usuarioService.listar());
    }

    @ApiOperation(value = "Api para guardar un usuario nuevo")
    @PostMapping(value = "/guardar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<UsuarioDto> guardar(@RequestBody @Valid UsuarioDto usuarioDto) {
        return ResponseEntity.ok(usuarioService.guardar(usuarioDto));
    }

    @ApiOperation(value = "Api para obtener un usuario por email")
    @GetMapping(value = "/obtener/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<UsuarioDto> obtenerPorEmail(@PathVariable String email) {
        return ResponseEntity.ok(usuarioService.obtenerPorEmail(email));
    }

    @ApiOperation(value = "Api para eliminar un usuario")
    @DeleteMapping(value = "/eliminar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
