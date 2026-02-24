import java.sql.*;

public class DBConnection {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/userdb",
                    "root",
                    "jiyanna@2006"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
