package com.luiz.me.model;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Pedido {

    @Id
    @GeneratedValue
    @JsonIgnore
    @Column(name = "pedido_id")
    private Long id;

    private Integer pedido;
    
    @Transient
    @JsonIgnore
    private BigDecimal totalPreco;
    
    @Transient
    @JsonIgnore
    private Long totalQtd;   
    

       
    public Pedido() {
       
    }

    public Pedido(BigDecimal totalPreco, Long totalQtd) {        
        this.totalPreco = totalPreco;
        this.totalQtd = totalQtd;
    }

    @OneToMany(mappedBy = "pedidoItem", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Item> itens;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the pedido
     */
    public Integer getPedido() {
        return pedido;
    }

    /**
     * @param pedido the pedido to set
     */
    public void setPedido(Integer pedido) {
        this.pedido = pedido;
    }

    public Set<Item> getItens() {
        return itens;
    }

    public void setItens(Set<Item> itens) {
        this.itens = itens;
    }

    public BigDecimal getTotalPreco() {
        return totalPreco;
    }

    public void setTotalPreco(BigDecimal totalPreco) {
        this.totalPreco = totalPreco;
    }

    public Long getTotalQtd() {
        return totalQtd;
    }

    public void setTotalQtd(Long totalQtd) {
        this.totalQtd = totalQtd;
    }
    
    

}
