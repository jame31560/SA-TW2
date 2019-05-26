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
            System.out.println("connect success to MySQLToJava");
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
                + "`u_name`, `u_username`, `u_password`, "
                + "`u_phone`, `u_QRcodeID`) VALUES ('"
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
            rs = stmt.executeQuery("SELECT u_username "
                + "FROM userinfo WHERE u_QRcodeID = '"
                + QRCodeID
                + "';");
            if(rs.next()){
                return rs.getString("u_username");
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean verifyUsername(String username) {
        try {
            rs = stmt.executeQuery("SELECT u_username "
                + "FROM userinfo WHERE u_username = '"
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
            stmt.executeUpdate("UPDATE `userinfo` SET `u_password` = '"
                + password + "' "
                + "WHERE `userinfo`.`u_username` = '"
                + username + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getUserPassword(String username) {
        try {
            rs = stmt.executeQuery("SELECT u_password "
                + "FROM userinfo WHERE u_username = '"
                + username
                + "';");
            if(rs.next()){
                return rs.getString("u_password");
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
            rs = stmt.executeQuery("SELECT u_name "
                + "FROM userinfo WHERE u_username = '"
                + username
                + "';");
            if(rs.next()){
                return rs.getString("u_name");
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
            rs = stmt.executeQuery("SELECT u_phone "
                + "FROM userinfo WHERE u_username = '"
                + username
                + "';");
            if(rs.next()){
                return rs.getString("u_phone");
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
            rs = stmt.executeQuery("SELECT u_QRcodeID "
                + "FROM userinfo WHERE u_username = '"
                + username
                + "';");
            if(rs.next()){
                return rs.getString("u_QRcodeID");
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
            rs = stmt.executeQuery("SELECT u_balance "
                + "FROM userinfo WHERE u_username = '"
                + username
                + "';");
            if(rs.next()){
                return rs.getInt("u_balance");
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
            stmt.executeUpdate("UPDATE `userinfo` SET `u_balance` = '"
                + balance + "' "
                + "WHERE `userinfo`.`u_username` = '"
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
}