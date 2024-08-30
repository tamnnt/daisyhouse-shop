package LifeLeopard.TeamShop.Models;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "table_blog_cmt")
@Data
public class BlogComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cmt_id")
    private int commentId;
    @ManyToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;

    @Column(name = "cmt")
    private String cmt;
    @ManyToOne
    @JoinColumn(name = "author_id ")
    private Customers customers;

    @Column(name = "created_at")
    private Timestamp createdAt;

    public BlogComment(){}

    public BlogComment(int commentId, Blog blog, String cmt, Customers customers, Timestamp createdAt) {
        this.commentId = commentId;
        this.blog = blog;
        this.cmt = cmt;
        this.customers = customers;
        this.createdAt = createdAt;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}