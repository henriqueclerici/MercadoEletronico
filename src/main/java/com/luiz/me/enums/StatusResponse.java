package com.luiz.me.enums;

public enum StatusResponse {

    CODIGO_PEDIDO_INVALIDO("CODIGO_PEDIDO_INVALIDO"), REPROVADO("REPROVADO"), APROVADO("APROVADO"),
    APROVADO_VALOR_A_MENOR("APROVADO_VALOR_A_MENOR"), APROVADO_QTD_A_MENOR("APROVADO_QTD_A_MENOR"), APROVADO_VALOR_A_MAIOR("APROVADO_VALOR_A_MAIOR"),
    APROVADO_QTD_A_MAIOR("APROVADO_QTD_A_MAIOR");

    private String description;

    StatusResponse(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
