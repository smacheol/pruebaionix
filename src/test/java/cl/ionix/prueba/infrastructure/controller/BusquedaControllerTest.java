package cl.ionix.prueba.infrastructure.controller;

import cl.ionix.prueba.application.BusquedaService;
import cl.ionix.prueba.application.dto.busqueda.BusquedaDto;
import cl.ionix.prueba.application.dto.busqueda.ParamDto;
import cl.ionix.prueba.application.dto.usuario.UsuarioDto;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
class BusquedaControllerTest {

    @Mock
    private BusquedaService busquedaService;

    @InjectMocks
    private BusquedaController busquedaController;

    private MockMvc mockMvc;
    private static final String BUSCAR = "/busqueda/buscar";

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(busquedaController).build();
    }

    @Test
    void guardar() throws Exception {
        ParamDto paramDto = new ParamDto();
        paramDto.setParametro("1-9");
        BusquedaDto busquedaDto = new BusquedaDto();
        busquedaDto.setElapsedTime(1L);
        when(busquedaService.buscar(any(ParamDto.class))).thenReturn(busquedaDto);
        MvcResult result = this.mockMvc.perform(post(BUSCAR)
                        .content(new ObjectMapper().writeValueAsString(paramDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();

        int statusCode = result.getResponse().getStatus();
        Assertions.assertEquals(200, statusCode);
        BusquedaDto busquedaResponse = new ObjectMapper().readValue(result.getResponse().getContentAsString(), BusquedaDto.class);
        Assertions.assertEquals(1, busquedaResponse.getElapsedTime());
    }
}