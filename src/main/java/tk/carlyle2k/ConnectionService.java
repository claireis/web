package tk.carlyle2k;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.System.getenv;

public class ConnectionService {
    static {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * get jdbc connections
     *
     * @return Connection
     */
    public static Connection getConn() throws SQLException {
        return DriverManager.getConnection("jdbc:mariadb://mariadb:3306/web",
                getenv("MYSQL_USER"),
                getenv("MYSQL_PASSWORD"));
    }
}
