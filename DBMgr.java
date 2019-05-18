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

    public void addUser(User ob) {
        try {
            stmt.executeUpdate("INSERT INTO `userinfo` ("
                + "`u_name`, `u_username`, `u_password`, "
                + "`u_phone`, `u_QRcodeID`) VALUES ('"
                + ob.getName() + "', '"
                + ob.getID() + "', '"
                + ob.getPassword() + "', '"
                + ob.getPhone() + "', '"
                + ob.getQRCodeID() + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getUserByQRCodeID(String QRCodeID) {
        try {
            rs = stmt.executeQuery("SELECT * "
                + "FROM userinfo WHERE u_QRcodeID = '"
                + QRCodeID
                + "';");
            if(rs.next()){
                return new User(rs.getString("u_username"),
                    rs.getString("u_password"),
                    rs.getString("u_name"),
                    rs.getInt("u_balance"),
                    rs.getString("u_phone"));
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public User getUserByUsername(String username) {
        try {
            rs = stmt.executeQuery("SELECT * "
                + "FROM userinfo WHERE u_username = '"
                + username
                + "';");
            if(rs.next()){
                return new User(rs.getString("u_username"),
                    rs.getString("u_password"),
                    rs.getString("u_name"),
                    rs.getInt("u_balance"),
                    rs.getString("u_phone"));
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public void changePassword(String userID, String password) {
        try {
            stmt.executeUpdate("UPDATE `userinfo` SET `u_password` = '"
                + password + "' "
                + "WHERE `userinfo`.`u_username` = '"
                + userID + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}