import javax.swing.JOptionPane;

public class User{
    private DBMgr dbMgr = new DBMgr();
    private String username = null;

    User(String username) {
        setUsername(username);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return dbMgr.getUserPassword(this.username);
    }

    public String getName() {
        return dbMgr.getUserName(this.username);
    }

    public String getPhone() {
        return dbMgr.getUserPhone(this.username);
    }

    public int getBalance() {
        return dbMgr.getUserBalance(this.username);
    }

    public String getQRCodeID() {
        return dbMgr.getUserQRCodeID(this.username);
    }
    
    public void setPassword(String password) {
        dbMgr.setUserPassword(this.username, password);
    }

    public void deductMoney(int amount) {
        dbMgr.deductUserMoney(this.username, amount);
    }

    public void addMoney(int amount) {
        dbMgr.addUserMoney(this.username, amount);
    }

    public Transaction makeTransaction(User payer, int amount) {
        Transaction transaction = new Transaction(this, payer, amount);
        dbMgr.addTransaction(transaction);
        return transaction;
    }

    public int[] historyTransactionsID() {
        return dbMgr.getUserTransactionHistory(username);
    }

    public boolean confirmAmount(String payeeID, int amount) {
        int result = JOptionPane.showConfirmDialog(null,
            "Is the amount right?",
            "Confirm",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            return true;
        } else {
            return false;
        }
    }
}