package com.luiz.me.util;

public final class Error {
    
    private Error() {
        throw new IllegalAccessError("Utility class");
}
    //  pedido
    public static final String NUMERO_PEDIDO_INVALIDO = "Numero do pedido invalido";
    public static final String NUMERO_PEDIDO_NAO_ENCONTRADO = "Numero do pedido nao encontrado";
    
    // item
    public static final String DESCRICAO_ITEM_INVALIDA = "Favor informar a descricao";
    public static final String PRECO_ITEM_INVALIDA = "Preco unitario nao pode ser negativo";
    public static final String QTD_ITEM_INVALIDA = "Quantidade deve ser maior de 0";
    
}
