import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBMgr {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    DBMgr () {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://"
                + "127.0.0.1:5000/java?"
                + "user=root&"
                + "password=root&"
                + "useUnicode=true");
            System.out.println("connect success to MySQL");
            stmt = conn.createStatement();
        } catch(SQLException excpt) {
            excpt.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addUser(String username,
            String password,
            String name,
            String phone) {
        try {
            stmt.executeUpdate("INSERT INTO `userinfo` ("
                + "`name`, `username`, `password`, "
                + "`phone`, `QRcodeID`) VALUES ('"
                + name + "', '"
                + username + "', '"
                + password + "', '"
                + phone + "', '"
                + username + phone + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUsernameByQRCodeID(String QRCodeID) {
        try {
            rs = stmt.executeQuery("SELECT username "
                + "FROM userinfo WHERE QRcodeID = '"
                + QRCodeID
                + "';");
            if(rs.next()){
                return rs.getString("username");
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean verifyUsername(String username) {
        try {
            rs = stmt.executeQuery("SELECT username "
                + "FROM userinfo WHERE username = '"
                + username
                + "';");
            if(rs.next()){
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void setUserPassword(String username, String password) {
        try {
            stmt.executeUpdate("UPDATE `userinfo` SET `password` = '"
                + password + "' "
                + "WHERE `userinfo`.`username` = '"
                + username + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getUserPassword(String username) {
        try {
            rs = stmt.executeQuery("SELECT password "
                + "FROM userinfo WHERE username = '"
                + username
                + "';");
            if(rs.next()){
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
        try {
            rs = stmt.executeQuery("SELECT name "
                + "FROM userinfo WHERE username = '"
                + username
                + "';");
            if(rs.next()){
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
        try {
            rs = stmt.executeQuery("SELECT phone "
                + "FROM userinfo WHERE username = '"
                + username
                + "';");
            if(rs.next()){
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
        try {
            rs = stmt.executeQuery("SELECT QRcodeID "
                + "FROM userinfo WHERE username = '"
                + username
                + "';");
            if(rs.next()){
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
        try {
            rs = stmt.executeQuery("SELECT balance "
                + "FROM userinfo WHERE username = '"
                + username
                + "';");
            if(rs.next()){
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
        try {
            stmt.executeUpdate("UPDATE `userinfo` SET `balance` = '"
                + balance + "' "
                + "WHERE `userinfo`.`username` = '"
                + username + "';");
        } catch (SQLException e) {
            e.printStackTrace();
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

    public void addTransaction(String payee,
            String payer,
            int amount,
            int status,
            String reason) {
        try {
            int transaction_id;
            rs = stmt.executeQuery("SELECT auto_increment "
                + "FROM information_schema.tables "
                + "WHERE table_schema='java' "
                + "AND table_name='transaction_list';");
            rs.next();
            transaction_id = rs.getInt("auto_increment");
            String sql = ("INSERT INTO `transaction_list` "
                + "(`id`, `amount`, `status`, `reason`) VALUES ("
                + transaction_id + ", "
                + amount + ", "
                + status + ", ");
            if (status == 0) {
            // 0 means success, 1 means fail.
                sql += "NULL);";
            } else {
                sql += "'" + reason + "');";
            }
            stmt.executeUpdate(sql);
            sql = ("INSERT INTO `transaction_detail` "
                + "(`transaction_id`, `username`, `role`) VALUES ("
                + transaction_id + ", '"
                + payee + "', "
                + "0), ("
                + transaction_id + ", '"
                + payer + "', "
                + "1);");
            stmt.executeUpdate(sql);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public int[] getUserTransactionHistory(String username) {
        try {
            rs = stmt.executeQuery("SELECT count(*) FROM transaction_detail "
                + "WHERE username = '" + username + "';");
            rs.next();
            int[] result = new int[rs.getInt("count(*)")];
            rs = stmt.executeQuery("SELECT transaction_id FROM transaction_detail "
                + "WHERE username = '" + username + "' "
                + "ORDER BY transaction_id ASC");
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
        try {
            rs = stmt.executeQuery("SELECT a.*, b.* "
                + "FROM transaction_detail as a "
                + "RIGHT JOIN transaction_list AS b "
                + "ON b.id = a.transaction_id "
                + "WHERE a.transaction_id = " + transactionID + " "
                + "ORDER BY `a`.`role` ASC");
            rs.next();
            String[] result = new String[7];
            result[0] = String.valueOf(rs.getInt("transaction_id"));
            result[1] = String.valueOf(rs.getInt("amount"));
            result[2] = rs.getString("datetime");
            result[3] = String.valueOf(rs.getInt("status"));
            result[4] = rs.getString("reason");
            result[5] = rs.getString("username");
            rs.next();
            result[6] = rs.getString("username");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }       
    }
}
