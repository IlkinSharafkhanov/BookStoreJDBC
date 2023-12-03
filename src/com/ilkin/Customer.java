package com.ilkin;

public class Customer {
    private int customerID;
    private String customerName;
    private String contactInfo;

    public Customer(int customerID, String customerName, String contactInfo) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.contactInfo = contactInfo;
    }

    // Getter and Setter methods for CustomerID
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    // Getter and Setter methods for CustomerName
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    // Getter and Setter methods for ContactInfo
    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

}
