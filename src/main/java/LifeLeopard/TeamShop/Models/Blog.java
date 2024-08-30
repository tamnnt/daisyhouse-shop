package LifeLeopard.TeamShop.Models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "table_blog")
@Data
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private int blogId;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "title")
    private String blogTitle;

    @Column(name = "descript")
    private String blogDesc;
    @Column(name = "img")
    private String img;
    @Column(name = "content")
    private  String blogContent;

    @Column(name = "author")
    private String blogAuthor;

    @OneToMany(mappedBy = "blog",cascade = CascadeType.ALL)
    private List<BlogComment> commentId;

    public  Blog(){}

    public Blog(int blogId, Timestamp createdAt, String blogTitle, String blogDesc, String img, String blogContent, String blogAuthor, List<BlogComment> commentId) {
        this.blogId = blogId;
        this.createdAt = createdAt;
        this.blogTitle = blogTitle;
        this.blogDesc = blogDesc;
        this.img = img;
        this.blogContent = blogContent;
        this.blogAuthor = blogAuthor;
        this.commentId = commentId;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogDesc() {
        return blogDesc;
    }

    public void setBlogDesc(String blogDesc) {
        this.blogDesc = blogDesc;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public String getBlogAuthor() {
        return blogAuthor;
    }

    public void setBlogAuthor(String blogAuthor) {
        this.blogAuthor = blogAuthor;
    }

    public List<BlogComment> getCommentId() {
        return commentId;
    }

    public void setCommentId(List<BlogComment> commentId) {
        this.commentId = commentId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "blogId=" + blogId +
                ", createdAt=" + createdAt +
                ", blogTitle='" + blogTitle + '\'' +
                ", blogDesc='" + blogDesc + '\'' +
                ", img='" + img + '\'' +
                ", blogContent='" + blogContent + '\'' +
                ", blogAuthor='" + blogAuthor + '\'' +
                ", commentId=" + commentId +
                '}';
    }
}