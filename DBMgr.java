import java.util.*;

public class DBMgr {
    List<User> UserList = new ArrayList<User>();

    DBMgr () {
        this.addUser(new User("B10623020", "1234", "Amy1", 1000, "09123456781"));
        this.addUser(new User("B10623021", "1234", "Amy2", 1000, "09123456782"));
        this.addUser(new User("B10623022", "1234", "Amy3", 1000, "09123456783"));
        /*
        The DBMgr need to add the User from database when it init,
        But we don't have database now, so I write the test data in here.
        */
    }

    protected void addUser(User ob) {
        UserList.add(ob);
    }

    public boolean verifyQRCode(String QRCodeID) {
        for (User user : UserList) {
            if (QRCodeID.equals(user.getQRCodeID())) {
                return true;
            }
        }

        return false;
    }

    public User getUser(String QRCodeID) {
        for (User user : UserList) {
            if (QRCodeID.equals(user.getQRCodeID())) {
                return user;
            }
        }

        return null;
    }
    
}