package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

    private static Connection conn = null;
    //private static String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() {
        try {
            if (conn == null) {
                //Class.forName(DRIVER);
                //Properties props = loadProperties();
                //String url = props.getProperty("dburl");
                DriverManager.getConnection("jdbc:mysql//localhost:3306/coursejdbc", "root", "023302");
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            }
            catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    private static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }
}