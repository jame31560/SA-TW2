import java.io.Console;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JOptionPane;

public class Controller {
    DBMgr dbMgr = new DBMgr();
    private String userID = null;
    Console console = System.console();

    public void setUserID(String username) {
        this.userID = username;
    }
    public boolean login(String username, String password) {
        if (dbMgr.verifyUsername(username)) {
            if (eccrypt(password).equals(dbMgr.getUserPassword(username))) {
                this.userID = username;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean logout() {
        try {
            userID = null;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public String[] regist(String username, String password, String confirmPassword, String name, String phone,
            int balance) {
        if (username.equals("") || dbMgr.verifyUsername(username)) {
            return new String[] { "Fail", "There has the same username in the system" };
        } else if (!password.equals(confirmPassword)) {
            return new String[] { "Fail", "Password confirm incorrect." };
        }
        dbMgr.addUser(username, eccrypt(password), name, phone);
        return new String[] { "Success" };
    }

    public String[] changePassword(String password, String newPassword, String confirmPassword) {
        if (eccrypt(password).equals(dbMgr.getUserPassword(userID))) {
            if (newPassword.equals(confirmPassword)) {
                dbMgr.setUserPassword(userID, eccrypt(newPassword));
                return new String[] { "Success" };
            } else {
                return new String[] { "Fail", "Password confirm incorrect." };
            }
        }
        return new String[] { "Fail", "Password incorrect." };
    }

    public String getPayer(String QRCodeID) {
        return dbMgr.getUsernameByQRCodeID(QRCodeID);
    }

    public String[] makeTransaction(String payerID, int amount) {
        if (amount > dbMgr.getUserBalance(payerID)) {
            return new String[] { "Fail", "Payer's balance is not enough." };
        } else {
            int result = JOptionPane.showConfirmDialog(null,
                "Is the amount right?",
                "Confirm",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                dbMgr.deductUserMoney(payerID, amount);
                dbMgr.addUserMoney(userID, amount);
                return new String[] { "Success" };
            } else {
                return new String[] { "Fail", "Payer reject the amount." };
            }
        }
    }

    public String eccrypt(String info){
        MessageDigest sha;
        try {
            sha = MessageDigest.getInstance("SHA-256");
            byte[] srcBytes = info.getBytes();
            sha.update(srcBytes);
            byte[] resultBytes = sha.digest();   
            char[] hexChar = {  
                '0', '1', '2', '3', '4', '5', '6', '7',  
                '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'  
            };
            StringBuilder sb = new StringBuilder(resultBytes.length * 2);  
            for (int i = 0; i < resultBytes.length; i++) {  
                sb.append(hexChar[(resultBytes[i] & 0xf0) >>> 4]);  
                sb.append(hexChar[resultBytes[i] & 0x0f]);  
            }  
            return sb.toString();  
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    

    public String getUserID() {
        return userID;
    }

    public String getUserPassword() {
        return dbMgr.getUserPassword(userID);
    }

    public String getUserName() {
        return dbMgr.getUserName(userID);
    }

    public int getUserBalance() {
        return dbMgr.getUserBalance(userID);
    }

    public String getUserQRCodeID() {
        return dbMgr.getUserQRCodeID(userID);
    }

    public String getUserPhone() {
        return dbMgr.getUserPhone(userID);
    }
}