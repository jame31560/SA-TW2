import java.sql.ResultSet;
import java.sql.SQLException;

public class DBMgr {
    private DBConnection db;
    private ResultSet rs;

    public void addUser(String username,
            String password,
            String name,
            String phone) {
        db = new DBConnection("INSERT INTO `User` ("
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
            + "FROM User WHERE QRcodeID = '"
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
            + "FROM User WHERE username = '"
            + username
            + "';", "query");
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
            + "FROM User WHERE username = '"
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
        db = new DBConnection("UPDATE `User` SET `password` = '"
            + password + "' "
            + "WHERE `User`.`username` = '"
            + username + "';", "update");
        while(!db.getStatus()) {
            System.out.printf("");
            return;
        }
    }
    public String getUserPassword(String username) {
        db = new DBConnection("SELECT password " 
            + "FROM User WHERE username = '" 
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
            + "FROM User WHERE username = '"
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
            + "FROM User WHERE username = '"
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
            + "FROM User WHERE username = '"
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
            + "FROM User WHERE username = '"
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
        db = new DBConnection("UPDATE `User` SET `balance` = '"
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
                + "AND table_name='Transaction';", "query");
            while(!db.getStatus()) {
                System.out.printf("");
                rs = db.getResultset();
            }
            rs.next();
            transaction_id = rs.getInt("auto_increment");
            String sql = ("INSERT INTO `Transaction` "
                + "(`TransactionId`, `amount`, `status`) VALUES ("
                + transaction_id + ", "
                + transaction.getAmount() + ", "
                + transaction.getStatus() + ");");
            db = new DBConnection(sql, "update");
            while(!db.getStatus()) {
                System.out.printf("");
                return;
            }
            sql = ("INSERT INTO `TransactionDetail` "
                + "(`TransactionId`, `username`, `role`) VALUES ("
                + transaction_id + ", '"
                + transaction.getPayeeID() + "', "
                + "1), ("
                + transaction_id + ", '"
                + transaction.getPayerID() + "', "
                + "2);");
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
        db = new DBConnection("SELECT count(*) FROM TransactionDetail "
            + "WHERE username = '" + username + "';", "query");
        while(!db.getStatus()) {
            System.out.printf("");
            rs = db.getResultset();
        }
        try {
            rs.next();
            int[] result = new int[rs.getInt("count(*)")];
            db = new DBConnection("SELECT TransactionId "
                + "FROM TransactionDetail "
                + "WHERE username = '" + username + "' "
                + "ORDER BY TransactionId DESC", "query");
            while(!db.getStatus()) {
                System.out.printf("");
                rs = db.getResultset();
            }
            int i = 0;
            while(rs.next()){
                result[i] = rs.getInt("TransactionId");
                i ++;
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
            
    }

    public String[] getTransactionDetail(int transactionID) {
        db = new DBConnection("SELECT a.*, b.*, c.statusDescription "
            + "FROM TransactionDetail as a "
            + "RIGHT JOIN `Transaction` AS b "
            + "ON b.TransactionId = a.TransactionId "
            + "RIGHT JOIN TransactionStatus AS c "
            + "ON b.status = c.status "
            + "WHERE a.TransactionId = " + transactionID + " "
            + "ORDER BY `a`.`role` ASC", "query");
        while(!db.getStatus()) {
            System.out.printf("");
            rs = db.getResultset();
        }
        try {
            if (rs.next()) {
                String[] result = new String[7];
                result[0] = String.valueOf(rs.getInt("TransactionId"));
                result[1] = String.valueOf(rs.getInt("amount"));
                result[2] = rs.getString("datetime");
                result[3] = String.valueOf(rs.getInt("status"));
                result[4] = rs.getString("statusDescription");
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
