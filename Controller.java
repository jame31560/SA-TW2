import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Controller {
    private View v = new View();
    private DBMgr db = new DBMgr();
    private User user = null;

    public void process() {
        v.shoWelcome();
        String command = null;
        while (command == null || !command.equals("3")) {
            if (command != null) {
                switch(command) {
                    case "1":
                        login(v.showLoginForm());
                        break;
                    case "2": 
                        break;
                    default: 
                        v.showError("The input seems not the options.");
                }
            }
            command = v.showMenu();
        }
    }

    public boolean login(String[] loginInfo) {
        user= db.getUser(loginInfo[0]);
        if (this.user != null) {
            if (eccrypt(loginInfo[1]).equals(user.getPassword())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
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
}
