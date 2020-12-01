package com.luiz.me.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.luiz.me.model.Item;
import com.luiz.me.model.Pedido;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {   
    
    @Query(value = "SELECT new com.luiz.me.model.Pedido(sum(i.precoUnitario * i.qtd) , sum(i.qtd)) from Item i "
            + "where pedido_id = ?1 ")
     Pedido obterValorDoPedidoeQuantidade(Long id);
}
