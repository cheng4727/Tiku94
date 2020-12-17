package com.example.tiku9_4.bean;

public class ZHGL {
    /*"id": 1,
            "number": 1,
            "owner": "张三",
            "balance": 55309,
            "plate": "鲁A10001",
            "brand": "奔驰",
            "user": "user1"*/
    private String id,owner,balance,plate,brand;
    private boolean isRecharge;

    public ZHGL(String id, String owner, String balance, String plate, String brand) {
        this.id = id;
        this.owner = owner;
        this.balance = balance;
        this.plate = plate;
        this.brand = brand;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isRecharge() {
        return isRecharge;
    }

    public void setRecharge(boolean recharge) {
        isRecharge = recharge;
    }

    @Override
    public String toString() {
        return "ZHGL{" +
                "id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                ", balance='" + balance + '\'' +
                ", plate='" + plate + '\'' +
                ", brand='" + brand + '\'' +
                ", isRecharge=" + isRecharge +
                '}';
    }
}
