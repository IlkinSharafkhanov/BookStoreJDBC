package com.ilkin;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    private static final String URL = "jdbc:postgresql://localhost:5432/Bookstore";
    private static final String USER = "postgres";
    private static final String PASSWORD = "mysecretpassword";

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            AuthorDataManager authorManager = new AuthorDataManager();
            BookDataManager bookManager = new BookDataManager();
            CustomerDataManager customerManager = new CustomerDataManager();
            OrderDataManager orderManager = new OrderDataManager();

            // Create instances of Author, Book, Customer, and Order classes
//            Author author1 = new Author(1, "John Doe");
//            Author author2 = new Author(2, "Dan Brown");
//            Author author3 = new Author(3, "Jules Verne");
//            Author author4 = new Author(4, "Arthur Konan Doyl");
//            Book book1 = new Book(2, "Angels And Demons", 1, 10);
//            Book book2 = new Book(3, "80 Days Around the World", 3, 17);
//            Book book3 = new Book(4, "Scherlock Holmes", 4, 20);
//            Customer newCustomer = new Customer(2, "Sebastian Vettel", "vettel5@example.com");
//            Customer anotherNewCustomer = new Customer(3, "Daniel Ricciardo", "danni3@example.com");
            Order newOrder = new Order(3, 3, Date.valueOf("2023-11-29"), new BigDecimal("90.00"));

            // Insert authors and a book
//            AuthorDataManager.insertAuthor(connection, author1);
//            AuthorDataManager.insertAuthor(connection, author2);
//            AuthorDataManager.insertAuthor(connection, author3);
//            AuthorDataManager.insertAuthor(connection, author4);
//            bookManager.insertBook(connection, book1);
//            bookManager.insertBook(connection, book2);
//            bookManager.insertBook(connection, book3);

            // Insert a customer
//            customerManager.insertCustomer(connection, newCustomer);
//            customerManager.insertCustomer(connection, anotherNewCustomer);

            // Insert an order and update books in the same transaction
//            orderManager.insertOrderWithBooksUpdate(connection, newOrder, 4, 5);

            // ii) Retrieving all book information, including authors and associated orders
            bookManager.retrieveAllBooks(connection);

            // iii) Updating details of a book
//            bookManager.updateBook(connection, 2, "Angels And Demons", 2, 10);

            // iv) Removing an existing book
//            bookManager.deleteBook(connection, 1);

            connection.close();
            System.out.println("Connection closed.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
