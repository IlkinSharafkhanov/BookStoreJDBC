package com.ilkin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ilkin.BookDataManager.handleSQLException;

public class CustomerDataManager {

    public static void insertCustomer(Connection connection, Customer customer) throws SQLException {
        int customerID = customer.getCustomerID();
        String customerName = customer.getCustomerName();
        String contactInfo = customer.getContactInfo();

        String insertCustomerQuery = "INSERT INTO Customers (CustomerID, CustomerName, ContactInfo) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertCustomerQuery)) {
            preparedStatement.setInt(1, customerID);
            preparedStatement.setString(2, customerName);
            preparedStatement.setString(3, contactInfo);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted into Customers table.");
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }


    public static boolean isCustomerExists(Connection connection, int customerID) throws SQLException {
        String checkCustomerQuery = "SELECT 1 FROM Customers WHERE CustomerID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(checkCustomerQuery)) {
            preparedStatement.setInt(1, customerID);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
    }

}

