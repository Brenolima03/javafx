package db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                // Using the H2 properties from the db.properties file
                conn = DriverManager.getConnection(getProperties("dburl"), getProperties("user"),
                        getProperties("password"));

                createDatabaseIfNotExists();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    public static void createDatabaseIfNotExists() {
        try (Connection conn = DriverManager.getConnection(getProperties("dburl"), getProperties("user"),
                getProperties("password"));
                Statement st = conn.createStatement()) {

            // Check if the "Department" table exists
            ResultSet rs = conn.getMetaData().getTables(null, null, "Department", null);
            if (!rs.next()) {
                // The "Department" table does not exist, so we'll create it

                // Read the content from the schema.sql file
                String sqlScript = readSqlScriptFromFile("schema.sql");

                // Execute the SQL script to create the "Department" table
                st.executeUpdate(sqlScript);
            }

        } catch (SQLException | IOException e) {
            throw new DbException(e.getMessage());
        }
    }

    // ... (remaining code)
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

    private static String readSqlScriptFromFile(String fileName) throws IOException {
        StringBuilder content = new StringBuilder();
        try (InputStream input = DB.class.getClassLoader().getResourceAsStream(fileName);
                BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {

            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeStatement(PreparedStatement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
}
