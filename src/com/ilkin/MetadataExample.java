package com.ilkin;

import java.sql.*;

public class MetadataExample {
    // JDBC URL, username, and password of PostgreSQL server
    private static final String URL = "jdbc:postgresql://localhost:5432/Bookstore";
    private static final String USER = "postgres";
    private static final String PASSWORD = "mysecretpassword";

    public static void main(String[] args) {
        try {
            // Step 1: Register JDBC driver
            Class.forName("org.postgresql.Driver");

            // Step 2: Open a connection
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Display info about tables
            displayTableInfo(connection);

            // Display details on columns of tables
            displayColumnInfo(connection);

            // Display information on primary and foreign keys
            displayKeyInfo(connection);

            // ... (the rest of your code)
        } catch (ClassNotFoundException | SQLException e) {
            // Handle errors
            e.printStackTrace();
        }
    }

    // Display info about tables
    private static void displayTableInfo(Connection connection) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tables = metaData.getTables(null, null, "%", new String[]{"TABLE"});

        System.out.println("Tables in the database:");
        while (tables.next()) {
            String tableName = tables.getString("TABLE_NAME");
            System.out.println("Table Name: " + tableName);
        }
    }

    // Display details on columns of tables
    private static void displayColumnInfo(Connection connection) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet columns = metaData.getColumns(null, null, "%", "%");

        System.out.println("\nColumns in the database:");
        while (columns.next()) {
            String tableName = columns.getString("TABLE_NAME");
            String columnName = columns.getString("COLUMN_NAME");
            String dataType = columns.getString("TYPE_NAME");

            System.out.println("Table: " + tableName + ", Column: " + columnName + ", Type: " + dataType);
        }
    }

    // Display information on primary and foreign keys
    private static void displayKeyInfo(Connection connection) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();

        // Display primary keys
        ResultSet primaryKeys = metaData.getPrimaryKeys(null, null, "%");
        System.out.println("\nPrimary Keys in the database:");
        while (primaryKeys.next()) {
            String tableName = primaryKeys.getString("TABLE_NAME");
            String columnName = primaryKeys.getString("COLUMN_NAME");
            System.out.println("Table: " + tableName + ", Primary Key Column: " + columnName);
        }

        // Display foreign keys
        ResultSet foreignKeys = metaData.getImportedKeys(null, null, "%");
        System.out.println("\nForeign Keys in the database:");
        while (foreignKeys.next()) {
            String tableName = foreignKeys.getString("FKTABLE_NAME");
            String columnName = foreignKeys.getString("FKCOLUMN_NAME");
            String referredTableName = foreignKeys.getString("PKTABLE_NAME");
            String referredColumnName = foreignKeys.getString("PKCOLUMN_NAME");

            System.out.println("Table: " + tableName + ", Foreign Key Column: " + columnName +
                    ", Referred Table: " + referredTableName + ", Referred Column: " + referredColumnName);
        }
    }
}
