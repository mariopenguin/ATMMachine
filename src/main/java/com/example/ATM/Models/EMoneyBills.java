package com.example.ATM.Models;

public enum EMoneyBills {
    BILL5(5),
    BILL10(10),
    BILL20(20),
    BILL50(50);

    private Integer value;
    EMoneyBills(int i) {
        this.value = i;
    }

    public Integer getValue() {
        return value;
    }
}
