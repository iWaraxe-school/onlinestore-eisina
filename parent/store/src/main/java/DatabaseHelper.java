import java.sql.*;

public class DatabaseHelper {
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/testKate";
    private static final String USER = "sa";
    private static final String PASS = "";
    private static Connection conn;

    public static Connection getConnection() {
        try {
            Class.forName(DatabaseHelper.JDBC_DRIVER).newInstance();
            try {
                conn = DriverManager.getConnection(DatabaseHelper.DB_URL, DatabaseHelper.USER, DatabaseHelper.PASS);
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
