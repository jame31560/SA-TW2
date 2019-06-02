import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection extends Thread {
    static private Connection conn = null;
    private String sql = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private boolean status = false;
    private String method = null ;

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(100);  // 每隔0.1秒嘗試連接
            } catch (InterruptedException e) {
                System.out.print("fail sleep");
            }
            String ip = "127.0.0.1";
            int port = 5000;
            String dbName = "java";
            String url = "jdbc:mysql://" + ip + ":" + port
                    + "/" + dbName;
            String user = "root";
            String password = "root";

            try {
                conn = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                System.out.print("fail");
            }
            try {
                stmt = conn.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (method.equals("query")) {
                    this.rs = stmt.executeQuery(sql);
                } else if (method.equals("update")) {
                    stmt.executeUpdate(sql);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            this.status = true;
            return;
        }
    }

    DBConnection(String sql, String method) {
        this.sql = sql;
        this.method = method;
        start();
    }

    public ResultSet getResultset() {
        return rs;
    }

    public boolean getStatus() {
        return status;
    }
}