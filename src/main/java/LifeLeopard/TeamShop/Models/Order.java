package LifeLeopard.TeamShop.Models;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "table_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Order_id")
    private int orderId;
    @ManyToOne
    @JoinColumn(name = "Customer_id")
    private Customers customers;
    @Column(name = "First_name")
    private String firstName;
    @Column(name = "Last_name")
    private String lastName;
    @Column(name = "Phone_numer")
    private String phoneNumber;
    @Column(name = "Address")
    private String address;
    @Column(name = "Total")
    private double total;
    @Column(name = "Payment")
    private String payment;
    @Column(name = "Payment_status")
    private int paymentStatus;
    @Column(name = "status")
    private int status;
    @Column(name = "Created")
    private Timestamp created;
    @Column(name = "Updated")
    private Timestamp updated;
    public Order(){}

    public Order(int orderId, Customers customers, String firstName, String lastName, String phoneNumber, String address, double total, String payment, int paymentStatus, int status, Timestamp created, Timestamp updated) {
        this.orderId = orderId;
        this.customers = customers;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.total = total;
        this.payment = payment;
        this.paymentStatus = paymentStatus;
        this.status = status;
        this.created = created;
        this.updated = updated;
    }

    public Order(Customers customers, String firstName, String lastName, String phoneNumber, String address, double total, int paymentStatus, int status) {
        this.customers = customers;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.total = total;
        this.paymentStatus = paymentStatus;
        this.status = status;
    }

    public Order(int orderId, Customers customers, String firstName, String lastName, String phoneNumber, String address, double total, int paymentStatus, int status) {
        this.orderId = orderId;
        this.customers = customers;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.total = total;
        this.paymentStatus = paymentStatus;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public int getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(int paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getCreated() {
        return created;
    }
    public Timestamp getUpdated() {
        return updated;
    }

    public String getFullName(){
        return firstName+" "+lastName;
    }
}
