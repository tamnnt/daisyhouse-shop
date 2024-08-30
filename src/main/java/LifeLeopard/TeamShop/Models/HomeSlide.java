package LifeLeopard.TeamShop.Models;

import javax.persistence.*;

@Entity
@Table(name = "table_slide_home")
public class HomeSlide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "slide_id")
    private int slideID;
    @Column(name = "slide_img")
    private String slideImg;
    @Column(name = "slide_sub")
    private String slideSub;
    @Column(name = "slide_title")
    private String slideTitle;
    @Column(name = "status")
    private int status;
    public HomeSlide(){}

    public HomeSlide(int slideID, String slideImg, String slideSub, String slideTitle, int status) {
        this.slideID = slideID;
        this.slideImg = slideImg;
        this.slideSub = slideSub;
        this.slideTitle = slideTitle;
        this.status = status;
    }
    public HomeSlide( String slideImg, String slideSub, String slideTitle, int status) {
        this.slideImg = slideImg;
        this.slideSub = slideSub;
        this.slideTitle = slideTitle;
        this.status = status;
    }

    public int getSlideID() {
        return slideID;
    }

    public void setSlideID(int slideID) {
        this.slideID = slideID;
    }

    public String getSlideImg() {
        return slideImg;
    }

    public void setSlideImg(String slideImg) {
        this.slideImg = slideImg;
    }

    public String getSlideSub() {
        return slideSub;
    }

    public void setSlideSub(String slideSub) {
        this.slideSub = slideSub;
    }

    public String getSlideTitle() {
        return slideTitle;
    }

    public void setSlideTitle(String slideTitle) {
        this.slideTitle = slideTitle;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "HomeSlide{" +
                "slideID=" + slideID +
                ", slideImg='" + slideImg + '\'' +
                ", slideSub='" + slideSub + '\'' +
                ", slideTitle='" + slideTitle + '\'' +
                ", status=" + status +
                '}';
    }
}
