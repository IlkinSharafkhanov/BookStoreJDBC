// Updated Order class
package com.ilkin;

import java.math.BigDecimal;
import java.sql.Date;

public class Order {
    private int orderID; // Keep the OrderID field for internal use
    private int customerID;
    private Date orderDate;
    private BigDecimal totalAmount;

    public Order(int customerID, Date orderDate, BigDecimal totalAmount) {
        // No need to provide OrderID during object creation
        this.customerID = customerID;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }

    // Getter and Setter methods for OrderID
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    // Getter and Setter methods for CustomerID
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    // Getter and Setter methods for OrderDate
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    // Getter and Setter methods for TotalAmount
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
