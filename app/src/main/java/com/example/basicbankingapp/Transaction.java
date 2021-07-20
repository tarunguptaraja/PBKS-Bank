package com.example.basicbankingapp;

public class Transaction {

    private String toUser;
    private String fromUser;
    private int amountTransferred;
    private int status;

    public Transaction(String toUser, String fromUser, int amountTransferred, int status) {
        this.toUser = toUser;
        this.fromUser = fromUser;
        this.amountTransferred = amountTransferred;
        this.status = status;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public int getAmountTransferred() {
        return amountTransferred;
    }

    public void setAmountTransferred(int amountTransferred) {
        this.amountTransferred = amountTransferred;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
