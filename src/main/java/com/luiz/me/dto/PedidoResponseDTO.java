package com.luiz.me.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luiz.me.enums.StatusResponse;

import lombok.Data;

@Data
public class PedidoResponseDTO {
    
    
    
    public PedidoResponseDTO(Integer pedido, StatusResponse status) {
        this.pedido = pedido;
        this.status = status;
    }
    
    public PedidoResponseDTO() {
        
    }
    
    @JsonIgnore
    private Integer pedido;
    
    private StatusResponse status;

}
