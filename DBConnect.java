// import java.sql.Connection; 
// import java.sql.DriverManager; 
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
import java.sql.*;

public class DBConnect{
     Connection con;
     PreparedStatement stmt;
     ResultSet rs;

    public DBConnect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/user?serverTimezone=UTC","root","");
            stmt=con.prepareStatement("select * from user where username=? and password=?");
        }catch(Exception ex){
            System.out.println("Error: " + ex);
        }
    }

    public void getData(){
        try{
            String query = "SELECT * from useracc";
            rs = stmt.executeQuery(query);
            System.out.println("Record from Database");
            while(rs.next()){
                String name = rs.getString("username");
                String pwd = rs.getString("password");
                System.out.println(name + " " +pwd);
            }

        }catch(Exception ex){
            System.out.println(ex);
        }
    }
}