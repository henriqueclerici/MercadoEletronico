package com.luiz.me.dto;

import java.math.BigDecimal;

import com.luiz.me.enums.StatusRequest;

import lombok.Data;

@Data
public class PedidoRequestDTO {

    private StatusRequest status;
    
    private Long itensAprovados;
    
    private BigDecimal valorAprovado;
    
    private Integer pedido;
    
    
}
