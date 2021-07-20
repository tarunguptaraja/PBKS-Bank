package com.example.basicbankingapp;

public class User {
    private String name;
    private int accountNo;
    private String email;
    private String phoneNo;
    private int balance;
    private String ifscCode;

    public User(String name, int accountNo, String email, String phoneNo, int balance, String ifscCode) {
        this.name = name;
        this.accountNo = accountNo;
        this.email = email;
        this.phoneNo = phoneNo;
        this.balance = balance;
        this.ifscCode = ifscCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }
}
