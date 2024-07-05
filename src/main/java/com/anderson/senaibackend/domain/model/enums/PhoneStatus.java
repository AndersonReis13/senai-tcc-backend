package com.anderson.senaibackend.domain.model.enums;


public enum PhoneStatus {
    PENDENTE("pendente"),
    CONCLUIDO("concluido");

    private String description;

    PhoneStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static boolean existsEnum(String status){
        for(PhoneStatus phoneStatus : PhoneStatus.values()){
            if(phoneStatus.getDescription().equalsIgnoreCase(status)){
                return true;
            }
        }
        return false;
    }
}
