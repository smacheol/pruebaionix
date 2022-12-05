package cl.ionix.prueba.infrastructure.controller;

import cl.ionix.prueba.application.UsuarioService;
import cl.ionix.prueba.application.dto.usuario.UsuarioDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    private MockMvc mockMvc;
    private static final String LISTAR = "/usuario/listar";
    private static final String GUARDAR = "/usuario/guardar";
    private static final String OBTENER = "/usuario/obtener/";
    private static final String ELIMINAR = "/usuario/eliminar/";

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
    }

    @Test
    void listar() throws Exception {
        when(usuarioService.listar()).thenReturn(new ArrayList<>());
        MvcResult result = this.mockMvc.perform(get(LISTAR)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();

        int statusCode = result.getResponse().getStatus();
        Assertions.assertEquals(200, statusCode);
        List<UsuarioDto> usuarioDtos = new ObjectMapper().readValue(result.getResponse().getContentAsString(), new TypeReference<List<UsuarioDto>>() {
        });
        Assertions.assertEquals(0, usuarioDtos.size());
    }

    @Test
    void guardar() throws Exception {
        when(usuarioService.guardar(any(UsuarioDto.class))).thenReturn(getUsuarioDto());
        MvcResult result = this.mockMvc.perform(post(GUARDAR)
                        .content(new ObjectMapper().writeValueAsString(getUsuarioDto()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();

        int statusCode = result.getResponse().getStatus();
        Assertions.assertEquals(200, statusCode);
        UsuarioDto usuarioDto = new ObjectMapper().readValue(result.getResponse().getContentAsString(), UsuarioDto.class);
        Assertions.assertNotNull(usuarioDto);
    }

    @Test
    void obtenerPorEmail() throws Exception {
        when(usuarioService.obtenerPorEmail(anyString())).thenReturn(getUsuarioDto());
        MvcResult result = this.mockMvc.perform(get(OBTENER + "EMAIL")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();
        int statusCode = result.getResponse().getStatus();
        Assertions.assertEquals(200, statusCode);
        UsuarioDto usuarioDto = new ObjectMapper().readValue(result.getResponse().getContentAsString(), UsuarioDto.class);
        Assertions.assertEquals("email@email.cl", usuarioDto.getEmail());

    }

    @Test
    void eliminar() throws Exception {
        MvcResult result = this.mockMvc.perform(delete(ELIMINAR + "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();

        int statusCode = result.getResponse().getStatus();
        Assertions.assertEquals(200, statusCode);
    }

    private UsuarioDto getUsuarioDto() {
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setEmail("email@email.cl");
        usuarioDto.setName("nombre");
        usuarioDto.setUsername("username");
        usuarioDto.setPhone("99");
        return usuarioDto;
    }
}