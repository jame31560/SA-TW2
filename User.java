import javax.swing.JOptionPane;

public class User{
    private DBMgr dbMgr = new DBMgr();
    private String username = null;
    private String password = dbMgr.getUserPassword(this.username);
    private String name = dbMgr.getUserName(this.username);
    private String phone = dbMgr.getUserPhone(this.username);
    private String QRCodeID = dbMgr.getUserQRCodeID(this.username);
    private int balance = dbMgr.getUserBalance(this.username);

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
        return password;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public int getBalance() {
        return balance;
    }

    public String geyQRCodeID() {
        return QRCodeID;
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