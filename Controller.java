import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JOptionPane;

public class Controller {
    private DBMgr dbMgr = new DBMgr();
    private String username = null;

    public void setUsingUsername(String username) {
        this.username = username;
    }

    public boolean login(String username, String password) {
        if (dbMgr.verifyUsername(username)) {
            if (eccrypt(password).equals(dbMgr.getUserPassword(username))) {
                this.setUsingUsername(username);
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
            this.username = null;
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
        if (username.equals("") || dbMgr.verifyUsername(username)) {
            return new String[] { "Fail",
                "There has the same username in the system" };
        } else if (username.length() > 20) {
            return new String[] { "Fail",
                "The username length need under than 20."};
        } else if (!password.equals(confirmPassword)) {
            return new String[] { "Fail", "Password confirm incorrect." };
        }
        dbMgr.addUser(username, eccrypt(password), name, phone);
        return new String[] { "Success" };
    }

    public String[] changePassword(String password,
            String newPassword,
            String confirmPassword) {
        if (eccrypt(password).equals(dbMgr.getUserPassword(this.username))) {
            if (newPassword.equals(confirmPassword)) {
                dbMgr.setUserPassword(this.username, eccrypt(newPassword));
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
            dbMgr.addTransaction(this.username, payerID, amount, 1,
                "Payer balance is not enough.");
            return new String[] { "Fail", "Payer's balance is not enough." };
        } else {
            int result = JOptionPane.showConfirmDialog(null,
                "Is the amount right?",
                "Confirm",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                dbMgr.deductUserMoney(payerID, amount);
                dbMgr.addUserMoney(this.username, amount);
                dbMgr.addTransaction(this.username, payerID, amount, 0,
                    null);
                return new String[] { "Success" };
            } else {
                dbMgr.addTransaction(this.username, payerID, amount, 1,
                    "Payer reject the amount.");
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

    public String[][] getUserHistory() {
        int[] HistoryID = dbMgr.getUserTransactionHistory(this.username);
        String[][] result = new String[HistoryID.length][7];
        for (int i = 0; i < HistoryID.length; i++) {
            result[i] = dbMgr.getTransactionDetail(HistoryID[i]);
        }
        return result;
    }
    

    public String getUserID() {
        return this.username;
    }

    public String getUserPassword() {
        return dbMgr.getUserPassword(this.username);
    }

    public String getUserName() {
        return dbMgr.getUserName(this.username);
    }

    public int getUserBalance() {
        return dbMgr.getUserBalance(this.username);
    }

    public String getUserQRCodeID() {
        return dbMgr.getUserQRCodeID(this.username);
    }

    public String getUserPhone() {
        return dbMgr.getUserPhone(this.username);
    }
}
