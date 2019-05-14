import java.io.Console;
import javax.swing.JOptionPane;

public class Controller {
    DBMgr dbMgr = new DBMgr();
    User user;
    Console console = System.console();
    
    public boolean login(String username, String password) {
        user = dbMgr.getUserByUsername(username);
        if (user == null) {
            return false;
        } else {
            if (password.equals(user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean logout() {
        try {
            user = null;
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public User getPayer(String QRCodeID) {
        return dbMgr.getUserByQRCodeID(QRCodeID);
    }
    
    public String[] makeTransaction(User payer, int amount) {
        if (amount > payer.getBlance()) {
            return new String[] { "Fail", "Payer's balance is not enough." };
        } else {
            int result = JOptionPane.showConfirmDialog(null,
               "Is the amount right?",
               "Confirm",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE);
            
            if (result == JOptionPane.YES_OPTION) {
                payer.deductMoney(amount);
                user.addMoney(amount);
                return new String[] { "Success" };
            } else {
                return new String[] { "Fail", "Payer reject the amount." };
            }
        }
    }
    

    public String getUserID() {
        return user.getID();
    }

    public String getUserPassword() {
        return user.getPassword();
    }

    public String getUserName() {
        return user.getName();
    }

    public int getUserBlance() {
        return user.getBlance();
    }

    public String getUserQRCodeID() {
        return user.getQRCodeID();
    }

    public String getUserPhone() {
        return user.getPhone();
    }
}