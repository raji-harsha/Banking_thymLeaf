package com.example.demo.model;

public enum TransactionType {
    DEPOSIT("Self Deposit"),WITHDRAWAL("Withdraw"),TRANSFER("Transfer");
    private String type;

    public String getType() {
        return type;
    }

    TransactionType(String type){
        this.type = type;
    }
}
