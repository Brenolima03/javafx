package db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                // Utilizando as propriedades do H2 do arquivo db.properties
                conn = DriverManager.getConnection(getProperties("dburl"), getProperties("user"), getProperties("password"));
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    private static String getProperties(String key) {
        try (InputStream input = DB.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty(key);
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }
    

    public static void closeResultSet(ResultSet rs) {
    }

    public static void closeStatement(PreparedStatement st) {
    }
}
