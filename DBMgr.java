import java.sql.ResultSet;
import java.sql.SQLException;

public class DBMgr {
    private DBConnection db;
    private ResultSet rs;

    public void addUser(String username,
            String password,
            String name,
            String phone) {
        db = new DBConnection("INSERT INTO `userinfo` ("
            + "`name`, `username`, `password`, "
            + "`phone`, `QRcodeID`) VALUES ('"
            + name + "', '"
            + username + "', '"
            + password + "', '"
            + phone + "', '"
            + username + phone + "');", "update");
        while(!db.getStatus()) {
            System.out.printf("");
            return;
        }
    }

    public String getUsernameByQRCodeID(String QRCodeID) {
        db = new DBConnection("SELECT username "
            + "FROM userinfo WHERE QRcodeID = '"
            + QRCodeID
            + "';", "query");
        while(!db.getStatus()) {
            System.out.printf("");
            rs = db.getResultset();
        }
        try {
            if (rs.next()) {
                return rs.getString("username");
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getUser(String username) {
        db = new DBConnection("SELECT username "
            + "FROM userinfo WHERE username = '"
            + username
            + "';", "query");
        int i = 0;
        while(!db.getStatus()) {
            System.out.printf("");
            rs = db.getResultset();
        }
        try {
            
            if (rs.next()) {
                
                return new User(rs.getString("username"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean verifyUsername(String username) {
        db = new DBConnection("SELECT username "
            + "FROM userinfo WHERE username = '"
            + username
            + "';", "query");
        while(!db.getStatus()) {
            System.out.printf("");
            rs = db.getResultset();
        }
        try {
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setUserPassword(String username, String password) {
        db = new DBConnection("UPDATE `userinfo` SET `password` = '"
            + password + "' "
            + "WHERE `userinfo`.`username` = '"
            + username + "';", "update");
        while(!db.getStatus()) {
            System.out.printf("");
            return;
        }
    }
    public String getUserPassword(String username) {
        db = new DBConnection("SELECT password " 
            + "FROM userinfo WHERE username = '" 
            + username 
            + "';", "query");
        while(!db.getStatus()) {
            System.out.printf("");
            rs = db.getResultset();
        }
        try {
            if (rs.next()) {
                return rs.getString("password");
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getUserName(String username) {
        db = new DBConnection("SELECT name "
            + "FROM userinfo WHERE username = '"
            + username
            + "';", "query");
        while(!db.getStatus()) {
            System.out.printf("");
            rs = db.getResultset();
        }
        try {
            if (rs.next()) {
                return rs.getString("name");
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getUserPhone(String username) {
        db = new DBConnection("SELECT phone "
            + "FROM userinfo WHERE username = '"
            + username
            + "';", "query");
        while(!db.getStatus()) {
            System.out.printf("");
            rs = db.getResultset();
        }
        try {
            if (rs.next()) {
                return rs.getString("phone");
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getUserQRCodeID(String username) {
        db = new DBConnection("SELECT QRcodeID "
            + "FROM userinfo WHERE username = '"
            + username
            + "';", "query");
        while(!db.getStatus()) {
            System.out.printf("");
            rs = db.getResultset();
        }
        try {
            if (rs.next()) {
                return rs.getString("QRcodeID");
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getUserBalance(String username) {
        db = new DBConnection("SELECT balance "
            + "FROM userinfo WHERE username = '"
            + username
            + "';", "query");
        while(!db.getStatus()) {
            System.out.printf("");
            rs = db.getResultset();
        }
        try {
            if (rs.next()) {
                return rs.getInt("balance");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void setUserBalance(String username, int balance) {
        db = new DBConnection("UPDATE `userinfo` SET `balance` = '"
            + balance + "' "
            + "WHERE `userinfo`.`username` = '"
            + username + "';", "update");
        while(!db.getStatus()) {
            System.out.printf("");
            return;
        }
    }
    
    public void deductUserMoney(String username, int amount) {
        int balance = this.getUserBalance(username);
        balance -= amount;
        this.setUserBalance(username, balance);
    }

    public void addUserMoney(String username, int amount) {
        int balance = this.getUserBalance(username);
        balance += amount;
        this.setUserBalance(username, balance);
    }

    public void addTransaction(Transaction transaction) {
        try {
            int transaction_id;
            db = new DBConnection("SELECT auto_increment "
                + "FROM information_schema.tables "
                + "WHERE table_schema='java' "
                + "AND table_name='transaction_list';", "query");
            while(!db.getStatus()) {
                System.out.printf("");
                rs = db.getResultset();
            }
            rs.next();
            transaction_id = rs.getInt("auto_increment");
            String sql = ("INSERT INTO `transaction_list` "
                + "(`id`, `amount`, `status`) VALUES ("
                + transaction_id + ", "
                + transaction.getAmount() + ", "
                + transaction.getStatus() + ");");
            db = new DBConnection(sql, "update");
            while(!db.getStatus()) {
                System.out.printf("");
                return;
            }
            sql = ("INSERT INTO `transaction_detail` "
                + "(`transaction_id`, `username`, `role`) VALUES ("
                + transaction_id + ", '"
                + transaction.getPayeeID() + "', "
                + "0), ("
                + transaction_id + ", '"
                + transaction.getPayerID() + "', "
                + "1);");
            db = new DBConnection(sql, "update");
            while(!db.getStatus()) {
                System.out.printf("");
                return;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public int[] getUserTransactionHistory(String username) {
        db = new DBConnection("SELECT count(*) FROM transaction_detail "
            + "WHERE username = '" + username + "';", "query");
        while(!db.getStatus()) {
            System.out.printf("");
            rs = db.getResultset();
        }
        try {
            rs.next();
            int[] result = new int[rs.getInt("count(*)")];
            db = new DBConnection("SELECT transaction_id "
                + "FROM transaction_detail "
                + "WHERE username = '" + username + "' "
                + "ORDER BY transaction_id ASC", "query");
            while(!db.getStatus()) {
                System.out.printf("");
                rs = db.getResultset();
            }
            int i = 0;
            while(rs.next()){
                result[i] = rs.getInt("transaction_id");
                i ++;
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
            
    }

    public String[] getTransactionDetail(int transactionID) {
        db = new DBConnection("SELECT a.*, b.*, c.description "
            + "FROM transaction_detail as a "
            + "RIGHT JOIN transaction_list AS b "
            + "ON b.id = a.transaction_id "
            + "RIGHT JOIN transaction_status AS c "
            + "ON b.status = c.id "
            + "WHERE a.transaction_id = " + transactionID + " "
            + "ORDER BY `a`.`role` ASC", "query");
        while(!db.getStatus()) {
            System.out.printf("");
            rs = db.getResultset();
        }
        try {
            if (rs.next()) {
                String[] result = new String[7];
                result[0] = String.valueOf(rs.getInt("transaction_id"));
                result[1] = String.valueOf(rs.getInt("amount"));
                result[2] = rs.getString("datetime");
                result[3] = String.valueOf(rs.getInt("status"));
                result[4] = rs.getString("description");
                result[5] = rs.getString("username");
                rs.next();
                result[6] = rs.getString("username");
                return result;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }    
    }
}
