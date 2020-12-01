package com.luiz.me.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luiz.me.model.Pedido;


@Repository
public interface PedidoRepositry extends JpaRepository<Pedido, Long> {
    
    Optional<Pedido> findByPedido(Integer pedido);

}
