package com.ilkin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BookDataManager {

    public static void insertBook(Connection connection, Book book) throws SQLException {
        int bookID = book.getBookID();
        String title = book.getTitle();
        int stockQuantity = book.getStockQuantity();
        Author author = new Author(book.getAuthorID(), "");  // Assuming Author class has a constructor that takes ID and name

        if (!AuthorDataManager.isAuthorExists(connection, author)) {
            System.err.println("Error: Author with ID " + book.getAuthorID() + " does not exist.");
            return;
        }

        String insertQuery = "INSERT INTO Books (BookID, Title, AuthorID, StockQuantity) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, bookID);
            preparedStatement.setString(2, title);
            preparedStatement.setInt(3, book.getAuthorID());
            preparedStatement.setInt(4, stockQuantity);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted.");
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }


    public static void retrieveAllBooks(Connection connection) throws SQLException {
        String selectQuery = "SELECT Books.BookID, Books.Title, Books.StockQuantity, Authors.AuthorName " +
                "FROM Books INNER JOIN Authors ON Books.AuthorID = Authors.AuthorID";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                // Process each row of the result set
                int bookID = resultSet.getInt("BookID");
                String title = resultSet.getString("Title");
                int stockQuantity = resultSet.getInt("StockQuantity");
                String authorName = resultSet.getString("AuthorName");

                System.out.println("BookID: " + bookID + ", Title: " + title + ", Author: " + authorName + ", Stock Quantity: " + stockQuantity);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public static void updateBook(Connection connection, int bookID, String title, int authorID, int stockQuantity) throws SQLException {
        String updateQuery = "UPDATE Books SET Title = ?, AuthorID = ?, StockQuantity = ? WHERE BookID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, authorID);
            preparedStatement.setInt(3, stockQuantity);
            preparedStatement.setInt(4, bookID);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) updated.");
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public static void deleteBook(Connection connection, int bookID) throws SQLException {
        String deleteQuery = "DELETE FROM Books WHERE BookID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, bookID);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) deleted.");
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public static void updateBookStock(Connection connection, int bookID, int quantity) throws SQLException {
        String updateStockQuery = "UPDATE Books SET StockQuantity = StockQuantity - ? WHERE BookID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateStockQuery)) {
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, bookID);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) updated in Books table.");
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public static void handleSQLException(SQLException e) {
        System.err.println("SQL Exception: " + e.getMessage());
        System.err.println("SQL State: " + e.getSQLState());
        System.err.println("Error Code: " + e.getErrorCode());
    }
}

