package com.anderson.senaibackend.domain.model.enums;

public enum ProductStatus{
    ESTOQUE("estoque"),
    FINALIZADO("finalizado");

    private String description;

    ProductStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static boolean existsEnum(String status){
        for(ProductStatus productStatus : ProductStatus.values()){
            if(productStatus.getDescription().equalsIgnoreCase(status)){
                return true;
            }
        }
        return false;
    }

}
