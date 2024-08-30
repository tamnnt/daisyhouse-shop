package LifeLeopard.TeamShop.Models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "table_comment")
public class ProductComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private int commentId;
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customers customers;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "comment_value")
    private String comment;
    @Column(name = "rating")
    private int rating;
    @Column(name = "status")
    private int status;
    @Column(name = "Created")
    private Timestamp created;
    @Column(name = "Updated")
    private Timestamp updated;
    public ProductComment(){}

    public ProductComment(int commentId, Customers customers, Product product, String comment, int rating, int status, Timestamp created, Timestamp updated) {
        this.commentId = commentId;
        this.customers = customers;
        this.product = product;
        this.comment = comment;
        this.rating = rating;
        this.status = status;
        this.created = created;
        this.updated = updated;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
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

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "ProductComment{" +
                "commentId=" + commentId +
                ", customers=" + customers +
                ", product=" + product +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                ", status=" + status +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }


}
