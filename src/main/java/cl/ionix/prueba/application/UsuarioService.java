package cl.ionix.prueba.application;

import cl.ionix.prueba.application.dto.usuario.UsuarioDto;
import cl.ionix.prueba.application.port.interactor.IUsuarioService;
import cl.ionix.prueba.application.port.output.IUsuarioRepository;
import cl.ionix.prueba.domain.entity.Usuario;
import cl.ionix.prueba.domain.enums.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UsuarioDto> listar() {
        List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();

        return usuarios.stream()
                .map(u -> modelMapper.map(u, UsuarioDto.class)).collect(Collectors.toList());
    }

    @Override
    public UsuarioDto guardar(UsuarioDto usuarioDto) {
        try {
            usuarioRepository.save(modelMapper.map(usuarioDto, Usuario.class));
        } catch (DataIntegrityViolationException se) {
            throw new GenericException(ErrorEnum.USUARIO_NO_GUARDADO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return usuarioDto;
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);

        if (usuario != null) {
            usuarioRepository.delete(usuario);
        } else {
            throw new GenericException(ErrorEnum.USUARIO_NO_ENCONTRADO, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public UsuarioDto obtenerPorEmail(String email) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

        if (usuario.isPresent()) {
            return modelMapper.map(usuario.get(), UsuarioDto.class);
        } else {
            throw new GenericException(ErrorEnum.USUARIO_NO_ENCONTRADO, HttpStatus.BAD_REQUEST);
        }
    }
}
