package com.luiz.me.service;

import com.luiz.me.dto.PedidoRequestDTO;
import com.luiz.me.dto.PedidoResponseDTO;
import com.luiz.me.model.Pedido;

public interface PedidoService {
    
    Pedido salvar(Pedido pedido);

    PedidoResponseDTO mudarStatus(PedidoRequestDTO pedidoDto);

}
