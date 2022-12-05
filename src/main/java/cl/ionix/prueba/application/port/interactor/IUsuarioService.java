package cl.ionix.prueba.application.port.interactor;

import cl.ionix.prueba.application.dto.usuario.UsuarioDto;

import java.util.List;

public interface IUsuarioService {
    List<UsuarioDto> listar();
    UsuarioDto guardar(UsuarioDto usuarioDto);
    void eliminar(Long id);
    UsuarioDto obtenerPorEmail(String email);
}
