public class Transaction {
    private String payeeID;
    private String payerID;
    private int status;
    private int amount;
    private String reason = null;

    Transaction(User payee, User payer, int amount) {
        this.payeeID = payee.getUsername();
        payerID = payer.getUsername();
        this.amount = amount;
        if (payer.getBalance() < amount) {
            this.status = 2;
            this.reason = "Payer balance is not enough.";
        } else {
            int confirmResult = payer.confirmAmount(payeeID, amount);
            if (confirmResult == 1) {
                this.status = 1;
                payee.addMoney(amount);
                payer.deductMoney(amount);
            } else if (confirmResult == 2) {
                this.status = 3;
                this.reason = "Payer reject the amount.";
            } else {
                this.status = 4;
                this.reason = "Transaction timeout.";
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

    public int getStatus() {
        return status;
    }
}