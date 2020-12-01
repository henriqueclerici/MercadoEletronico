package com.luiz.me.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luiz.me.exception.ItemException;
import com.luiz.me.model.Item;
import com.luiz.me.model.Pedido;
import com.luiz.me.repository.ItemRepository;
import com.luiz.me.service.ItemService;
import com.luiz.me.util.Error;

import lombok.Getter;


@Service
@Getter
public class ItemServiceImpl implements ItemService{
    
    private final ItemRepository repository;
    
    @Autowired
    public ItemServiceImpl(final ItemRepository repository) {        
        this.repository = repository ;
    }
    
    

    @Override
    public void validarItens(Item item) {
        
        if(null == item.getDescricao() && item.getDescricao().equals("")) {
            throw new ItemException(Error.DESCRICAO_ITEM_INVALIDA);
        }else if (item.getPrecoUnitario() == BigDecimal.ZERO || item.getPrecoUnitario().compareTo(BigDecimal.ZERO) < 0) {
            throw new ItemException(Error.PRECO_ITEM_INVALIDA);
        }else if (null == item.getQtd() && item.getQtd().compareTo(0L) < 0) {
            throw new ItemException(Error.QTD_ITEM_INVALIDA);
        }
        
        
    }



    @Override
    public Pedido obterValorDoPedidoeQuantidade(Long id) {
        return getRepository().obterValorDoPedidoeQuantidade(id);
    }




    

}
