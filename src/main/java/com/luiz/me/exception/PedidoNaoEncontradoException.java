package com.luiz.me.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PedidoNaoEncontradoException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public PedidoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    
    
}
