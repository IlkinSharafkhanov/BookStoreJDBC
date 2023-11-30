package com.ilkin;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

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

            Scanner scanner = new Scanner(System.in);

            // Display menu options
            System.out.println("Select an option:");
            System.out.println("1. Insert");
            System.out.println("2. Update");
            System.out.println("3. Retrieve");
            System.out.println("4. Delete");

            // Read user choice
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Insert
                    System.out.println("Select what to insert:");
                    System.out.println("1. Author");
                    System.out.println("2. Book");
                    System.out.println("3. Customer");
                    System.out.println("4. Order");

                    int insertChoice = scanner.nextInt();

                    switch (insertChoice) {
                        case 1:
                            // Insert Author
                            System.out.println("Enter Author ID:");
                            int authorID = scanner.nextInt();
                            scanner.nextLine();  // Consume the newline character

                            System.out.println("Enter Author Name:");
                            String authorName = scanner.nextLine();
                            Author newAuthor = new Author(authorID, authorName);
                            authorManager.insertAuthor(connection, newAuthor);
                            break;

                        case 2:
                            // Insert Book
                            System.out.println("Enter Book ID:");
                            int bookID = scanner.nextInt();
                            scanner.nextLine();  // Consume the newline character
                            System.out.println("Enter Book Title:");
                            String title = scanner.nextLine();
                            System.out.println("Enter Author ID:");
                            int authorIDForBook = scanner.nextInt();
                            System.out.println("Enter Stock Quantity:");
                            int stockQuantity = scanner.nextInt();
                            Book newBook = new Book(bookID, title, authorIDForBook, stockQuantity);
                            bookManager.insertBook(connection, newBook);
                            break;

                        case 3:
                            // Insert Customer
                            System.out.println("Enter Customer ID:");
                            int customerID = scanner.nextInt();
                            scanner.nextLine();  // Consume the newline character
                            System.out.println("Enter Customer Name:");
                            String customerName = scanner.nextLine();
                            System.out.println("Enter Customer Email:");
                            String customerEmail = scanner.nextLine();
                            Customer newCustomer = new Customer(customerID, customerName, customerEmail);
                            customerManager.insertCustomer(connection, newCustomer);
                            break;

                        case 4:
                            // Insert Order
                            System.out.println("Enter Order ID:");
                            int orderID = scanner.nextInt();
                            System.out.println("Enter Customer ID:");
                            int customerIDForOrder = scanner.nextInt();
                            System.out.println("Enter Order Date (YYYY-MM-DD):");
                            String orderDateString = scanner.next();
                            Date orderDate = Date.valueOf(orderDateString);
                            System.out.println("Enter Total Amount:");
                            BigDecimal totalAmount = scanner.nextBigDecimal();
                            Order newOrder = new Order(orderID, customerIDForOrder, orderDate, totalAmount);
                            System.out.println("Enter Book ID for the order:");
                            int bookIDForOrder = scanner.nextInt();
                            System.out.println("Enter Quantity for the order:");
                            int quantityForOrder = scanner.nextInt();
                            orderManager.insertOrderWithBooksUpdate(connection, newOrder, bookIDForOrder, quantityForOrder);
                            break;

                        default:
                            System.out.println("Invalid choice for insert");
                    }
                    break;

                case 2:
                    // Update
                    System.out.println("Select what to update:");
                    System.out.println("1. Book");

                    int updateChoice = scanner.nextInt();

                    switch (updateChoice) {
                        case 1:
                            // Update Book
                            System.out.println("Enter Book ID to update:");
                            int bookIDToUpdate = scanner.nextInt();
                            scanner.nextLine();  // Consume the newline character
                            System.out.println("Enter New Title:");
                            String newTitle = scanner.nextLine();
                            System.out.println("Enter New Author ID:");
                            int newAuthorID = scanner.nextInt();
                            System.out.println("Enter New Stock Quantity:");
                            int newStockQuantity = scanner.nextInt();
                            bookManager.updateBook(connection, bookIDToUpdate, newTitle, newAuthorID, newStockQuantity);
                            break;

                        default:
                            System.out.println("Invalid choice for update");
                    }
                    break;

                case 3:
                    // Retrieve
                    bookManager.retrieveAllBooks(connection);
                    break;

                case 4:
                    // Delete
                    System.out.println("Select what to delete:");
                    System.out.println("1. Book");

                    int deleteChoice = scanner.nextInt();

                    switch (deleteChoice) {
                        case 1:
                            // Delete Book
                            System.out.println("Enter Book ID to delete:");
                            int bookIDToDelete = scanner.nextInt();
                            bookManager.deleteBook(connection, bookIDToDelete);
                            break;

                        default:
                            System.out.println("Invalid choice for delete");
                    }
                    break;

                default:
                    System.out.println("Invalid choice");
            }

            connection.close();
            System.out.println("Connection closed.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
