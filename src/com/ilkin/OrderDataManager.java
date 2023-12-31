package com.ilkin;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDataManager {

    public static void insertOrder(Connection connection, int customerID, Date orderDate, BigDecimal totalAmount) throws SQLException {
        String insertOrderQuery = "INSERT INTO Orders (CustomerID, OrderDate, TotalAmount) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertOrderQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, customerID);
            preparedStatement.setDate(2, orderDate);
            preparedStatement.setBigDecimal(3, totalAmount);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted into Orders table.");

            // Retrieve the auto-generated Order ID
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedOrderID = generatedKeys.getInt(1);
                    System.out.println("Generated Order ID: " + generatedOrderID);
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }


    public void insertOrderWithBooksUpdate(Connection connection, Order order, int bookID, int quantity) throws SQLException {
        try {
            // Disable auto-commit to start a transaction
            connection.setAutoCommit(false);

            int customerID = order.getCustomerID();
            // No need to specify Order ID here since it's assumed to be auto-incremented
            Date orderDate = order.getOrderDate();
            BigDecimal totalAmount = order.getTotalAmount();

            // Check if the customer exists
            if (!isCustomerExists(connection, customerID)) {
                System.err.println("Error: Customer with ID " + customerID + " does not exist.");
                connection.rollback();
                return;
            }

            // Check if enough books are available
            if (isStockAvailable(connection, bookID, quantity)) {
                // Insert the order without specifying Order ID
                insertOrder(connection, customerID, orderDate, totalAmount);

                // Update the Books table (decrease the stock)
                BookDataManager.updateBookStock(connection, bookID, quantity);

                // Commit the transaction
                connection.commit();
                System.out.println("Transaction committed.");
            } else {
                // Rollback the transaction if not enough stock
                connection.rollback();
                System.err.println("Transaction rolled back. Not enough stock available.");
            }
        } catch (SQLException e) {
            // Rollback the transaction in case of an exception
            connection.rollback();
            BookDataManager.handleSQLException(e);
        } finally {
            // Enable auto-commit to return to the default behavior
            connection.setAutoCommit(true);
        }
    }






    // Helper method to check if a customer with the specified CustomerID exists
    static boolean isCustomerExists(Connection connection, int customerID) throws SQLException {
        String checkCustomerQuery = "SELECT 1 FROM Customers WHERE CustomerID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(checkCustomerQuery)) {
            preparedStatement.setInt(1, customerID);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();  // If next() is true, customer exists; otherwise, it doesn't
        }
    }

    // Helper method to check if enough stock is available
    static boolean isStockAvailable(Connection connection, int bookID, int quantity) throws SQLException {
        String checkStockQuery = "SELECT StockQuantity FROM Books WHERE BookID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(checkStockQuery)) {
            preparedStatement.setInt(1, bookID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int stockQuantity = resultSet.getInt("StockQuantity");
                return stockQuantity >= quantity;
            }

            return false;
        }
    }



    // Helper method to handle SQLException
    static void handleSQLException(SQLException e) {
        System.err.println("SQL Exception: " + e.getMessage());
        System.err.println("SQL State: " + e.getSQLState());
        System.err.println("Error Code: " + e.getErrorCode());
    }

}
