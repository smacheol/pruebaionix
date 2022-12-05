package cl.ionix.prueba.infrastructure.controller;

import cl.ionix.prueba.application.dto.busqueda.BusquedaDto;
import cl.ionix.prueba.application.dto.busqueda.ParamDto;
import cl.ionix.prueba.application.port.input.IBusquedaController;
import cl.ionix.prueba.application.port.interactor.IBusquedaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/busqueda")
@Api(tags = "BusquedaController")
public class BusquedaController implements IBusquedaController {

    @Autowired
    private IBusquedaService busquedaService;

    @ApiOperation(value = "Api para consultar servicio de busqueda")
    @PostMapping(value = "/buscar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<BusquedaDto> guardar(@RequestBody @Valid ParamDto paramDto) {
        return ResponseEntity.ok(busquedaService.buscar(paramDto));
    }
}
