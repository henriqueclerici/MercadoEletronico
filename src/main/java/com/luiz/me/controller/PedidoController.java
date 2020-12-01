package com.luiz.me.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.created;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luiz.me.dto.PedidoRequestDTO;
import com.luiz.me.dto.PedidoResponseDTO;
import com.luiz.me.model.Pedido;
import com.luiz.me.service.PedidoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Pedido Controller")
@RequestMapping(value = "/api")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(final PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }
    
    @ApiOperation(value = "Salvar Pedido")
    @PostMapping(value="/pedido")
    @ResponseStatus(CREATED)
    public ResponseEntity<Pedido> salvar(@RequestBody Pedido pedido) {
        pedidoService.salvar(pedido);
        return new ResponseEntity<>(HttpStatus.CREATED);
                
    }
    
    
    @ApiOperation(value = "Mudar o status do Pedido")
    @PostMapping(value="/pedido/status")
    @ResponseStatus(CREATED)
    public ResponseEntity<PedidoResponseDTO> mudarStatus(@RequestBody PedidoRequestDTO pedidoDto) {
        PedidoResponseDTO pedidoResponse = pedidoService.mudarStatus(pedidoDto);
        return created(URI.create("/pedido/status/"+pedidoResponse.getStatus()))
                .body(pedidoResponse); 
    }
    
    
    
   

}
