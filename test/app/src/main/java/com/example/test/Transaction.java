package com.example.test;

public class Transaction {
    private String payeeID;
    private String payerID;
    private boolean status;
    private int amount;
    private String reason = null;

    Transaction(User payee, User payer, int amount) {
        this.payeeID = payee.getUsername();
        payerID = payer.getUsername();
        this.amount = amount;
        if (payer.getBalance() < amount) {
            this.status = false;
            this.reason = "Payer balance is not enough.";
        } else {
            if (payer.confirmAmount(payeeID, amount)) {
                this.status = true;
                payee.addMoney(amount);
                payer.deductMoney(amount);
            } else {
                this.status = false;
                this.reason = "Payer reject the amount.";
            }
        }
    }

    public int getAmount() {
        return amount;
    }

    public String getPayeeID() {
        return payeeID;
    }

    public String getPayerID() {
        return payerID;
    }

    public String getReason() {
        return reason;
    }

    public boolean getStatus() {
        return status;
    }
}
