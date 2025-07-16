package com.bank;

public class User {
    public int accNo;
    public String name;
    public String email;
    public String pin;
    public  double balance;

    public User(int accNo, String name, String email, String pin, double balance){
        this.accNo = accNo;
        this.name = name;
        this.email = email;
        this.pin = pin;
        this.balance = balance;
    }
}
