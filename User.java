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

    public int confirmAmount(String payeeID, int amount) {
        // use socket to confirm.
        return 1; // 1 success, 2 reject, 3 timeout
    }
}