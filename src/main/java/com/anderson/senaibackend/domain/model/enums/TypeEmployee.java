package com.anderson.senaibackend.domain.model.enums;


public enum TypeEmployee {
    TECNICO("tecnico"),
    GERENTE("gerente"),
    ATENDENTE("atendente");

    private String description;

    TypeEmployee(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static boolean existsEnum(String status) {
        for (TypeEmployee typeEmployee : TypeEmployee.values()) {
            if (typeEmployee.getDescription().equalsIgnoreCase(status)) {
                return true;
            }
        }
        return false;
    }

}
