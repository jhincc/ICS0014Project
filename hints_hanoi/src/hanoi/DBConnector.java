package hanoi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    public static Connection getConnection() throws SQLException {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/highscores?useSSL=false", "root", "");
            return connection;
    }
}
