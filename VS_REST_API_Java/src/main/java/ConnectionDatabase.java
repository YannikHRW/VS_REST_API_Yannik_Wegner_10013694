import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDatabase {

    private static Statement connectionToDatabase;

    public static void createConnection(String path) throws SQLException {
        Connection connection = DriverManager.getConnection(path);
        connectionToDatabase = connection.createStatement();
    }

    public static Statement getConnectionToDatabase(){
        return connectionToDatabase;
    }

}
