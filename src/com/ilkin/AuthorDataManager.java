package com.ilkin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorDataManager {

    public static void insertAuthor(Connection connection, Author author) throws SQLException {
        int authorID = author.getAuthorID();
        String authorName = author.getAuthorName();

        String insertAuthorQuery = "INSERT INTO Authors (AuthorID, AuthorName) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertAuthorQuery)) {
            preparedStatement.setInt(1, authorID);
            preparedStatement.setString(2, authorName);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted for author.");
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public static boolean isAuthorExists(Connection connection, Author author) throws SQLException {
        int authorID = author.getAuthorID();  // Assuming you have a getAuthorID method in your Author class
        String checkAuthorQuery = "SELECT 1 FROM Authors WHERE AuthorID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(checkAuthorQuery)) {
            preparedStatement.setInt(1, authorID);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
    }


    // Other methods remain unchanged...

    public static void handleSQLException(SQLException e) {
        System.err.println("SQL Exception: " + e.getMessage());
        System.err.println("SQL State: " + e.getSQLState());
        System.err.println("Error Code: " + e.getErrorCode());
    }
}
