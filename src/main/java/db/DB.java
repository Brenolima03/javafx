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
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return conn;
    }

    public static void createDatabaseIfNotExists() throws IOException {
        try (Connection conn = DriverManager.getConnection(getProperties("dburl"), getProperties("user"),
                getProperties("password"));
                Statement st = conn.createStatement()) {

            // Check if the "Department" table exists
            ResultSet departmentTableRs = conn.getMetaData().getTables(null, null, "Department", null);
            if (!departmentTableRs.next()) {
                // The "Department" table does not exist, so we'll create it
                String departmentSqlScript = readSqlScriptFromFile("schema_department.sql");
                st.executeUpdate(departmentSqlScript);
            }

            // Check if the "Seller" table exists
            ResultSet sellerTableRs = conn.getMetaData().getTables(null, null, "Seller", null);
            if (!sellerTableRs.next()) {
                // The "Seller" table does not exist, so we'll create it
                String sellerSqlScript = readSqlScriptFromFile("schema_seller.sql");
                st.executeUpdate(sellerSqlScript);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Print the error stack trace
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

    private static String readSqlScriptFromFile(String fileName) {
        StringBuilder content = new StringBuilder();
        try (InputStream input = DB.class.getResourceAsStream("/" + fileName);
                InputStreamReader isr = new InputStreamReader(input);
                BufferedReader reader = new BufferedReader(isr)) {

            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new DbException("Error reading SQL script file: " + fileName);
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
