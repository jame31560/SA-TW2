import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// import javax.swing.JOptionPane;

public class Controller {
    private static DBMgr dbMgr = new DBMgr();
    private static Main view = new Main();
    private User user = null;

    public void setUser(User user) {
        this.user = user;
    }

    public boolean login(String username, String password) {
        setUser(dbMgr.getUser(username));
        if (this.user != null) {
            if (eccrypt(password).equals(user.getPassword())) {
                return true;
            } else {
                System.out.println(user.getPassword());
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean logout() {
        try {
            setUser(null);
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
        if (eccrypt(password).equals(user.getPassword())) {
            if (newPassword.equals(confirmPassword)) {
                user.setPassword(eccrypt(newPassword));
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

    public void makeTransaction(String payerID, int amount) {
        User payer = dbMgr.getUser(payerID);
        Transaction transaction = user.makeTransaction(payer, amount);
        String msg = "\nTransaction ";
        if (transaction.getStatus()) {
            msg += "success.\n";
            msg += String.format("Transaction amount: %,d\n",
                transaction.getAmount());
            msg += ("Payer: " +  transaction.getPayerID() + "\n");
		} else {
            msg += "fail.\n";
            msg += ("Reason: " + transaction.getReason() + "\n");
            msg += String.format("Transaction amount: %,d\n",
                transaction.getAmount());
            msg += ("Payer: " +  transaction.getPayerID() + "\n");
        }
        view.showTransaction(msg);
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
        int[] HistoryID = user.historyTransactionsID();
        String[][] result = new String[HistoryID.length][7];
        for (int i = 0; i < HistoryID.length; i++) {
            result[i] = dbMgr.getTransactionDetail(HistoryID[i]);
        }
        return result;
    }

    public String getUserInfo() {
        return "Name: " + user.getName()
            + "\nUsername: " + user.getUsername()
            + "\nPhone: " + user.getPhone()
            + "\nBalance: NT$ "+ String.format("%,d\n", user.getBalance());
    }

    public String getQRCodeID() {
        return user.getQRCodeID();
    }

    public String getName() {
        return user.getName();
    }
}
