package LifeLeopard.TeamShop.Models;

import javax.persistence.*;

@Entity
@Table(name = "table_contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private int contactId;
    @Column(name = "address")
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email_sup")
    private  String emailsuport;
    @Column(name = "status")
    private int status;
    public Contact(){}

    public Contact(int contactId, String address, String phoneNumber, String emailsuport, int status) {
        this.contactId = contactId;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailsuport = emailsuport;
        this.status = status;
    }

    public Contact(String address, String phoneNumber, String emailsuport, int status) {
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailsuport = emailsuport;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailsuport() {
        return emailsuport;
    }

    public void setEmailsuport(String emailsuport) {
        this.emailsuport = emailsuport;
    }
}
