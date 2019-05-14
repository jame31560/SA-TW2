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

    public String[] regist(String username,
            String password,
            String confirmPassword,
            String name,
            String phone,
            int balance) {
        
        if (username.equals("") || 
                dbMgr.getUserByUsername(username) != null) {
            return new String[] {"Fail",
                "There has the same username in the system"};
        } else if (!password.equals(confirmPassword)) {
            return new String[] {"Fail",
                "Password confirm incorrect."};
        }
        User registUser = new User(username,
            password,
            name,
            balance,
            phone);
        dbMgr.addUser(registUser);
        return new String[] {"Success"};
    }

    public String[] changePassword(String password,
            String newPassword,
            String confirmPassword) {
        if (password.equals(user.getPassword())) {
            if (newPassword.equals(confirmPassword)) {
                dbMgr.setUserPassword(user.getID(), newPassword);
                user = dbMgr.getUserByUsername(user.getID());
                return new String[] {"Success"};
            } else {
                return new String[] {"Fail", "Password confirm incorrect."};
            }
        }
        return new String[] {"Fail", "Password incorrect."};
    }
    
    public User getPayer(String QRCodeID) {
        return dbMgr.getUserByQRCodeID(QRCodeID);
    }
    
    public String[] makeTransaction(User payer, int amount) {
        if (amount > payer.getBalance()) {
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

    public int getUserBalance() {
        return user.getBalance();
    }

    public String getUserQRCodeID() {
        return user.getQRCodeID();
    }

    public String getUserPhone() {
        return user.getPhone();
    }
}