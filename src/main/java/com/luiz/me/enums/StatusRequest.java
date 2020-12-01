package com.luiz.me.enums;

public enum StatusRequest {
    
    APROVADO("APROVADO"),REPROVADO("REPROVADO");

    private String description;

    StatusRequest(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }    
    

}
