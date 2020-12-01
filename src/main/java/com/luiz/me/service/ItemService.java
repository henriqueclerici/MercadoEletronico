package com.luiz.me.service;

import com.luiz.me.model.Item;
import com.luiz.me.model.Pedido;

public interface ItemService {
    
    void validarItens (Item item);
    
    Pedido obterValorDoPedidoeQuantidade(Long id);

}
