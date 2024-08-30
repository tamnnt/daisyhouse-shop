package LifeLeopard.TeamShop.Models;

import javax.persistence.*;

@Entity
@Table(name = "table_about")
public class About {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "about_id")
    private int aboutId;
    @Column(name = "about_title")
    private String aboutTitle;
    @Column(name = "about_sub")
    private String aboutSub;
    @Column(name = "about_topic")
    private String aboutTopic;
    @Column(name = "about_img")
    private String aboutImg;
    @Column(name = "name")
    private String name;
    @Column(name = "status")
    private int status;
    public About(){}

    public About(int aboutId, String aboutTitle, String aboutSub, String aboutTopic, String aboutImg, String name, int status) {
        this.aboutId = aboutId;
        this.aboutTitle = aboutTitle;
        this.aboutSub = aboutSub;
        this.aboutTopic = aboutTopic;
        this.aboutImg = aboutImg;
        this.name = name;
        this.status = status;
    }

    public About(String aboutTitle, String aboutSub, String aboutTopic, String aboutImg, String name, int status) {
        this.aboutTitle = aboutTitle;
        this.aboutSub = aboutSub;
        this.aboutTopic = aboutTopic;
        this.aboutImg = aboutImg;
        this.name = name;
        this.status = status;
    }

    public int getAboutId() {
        return aboutId;
    }

    public void setAboutId(int aboutId) {
        this.aboutId = aboutId;
    }

    public String getAboutTitle() {
        return aboutTitle;
    }

    public void setAboutTitle(String aboutTitle) {
        this.aboutTitle = aboutTitle;
    }

    public String getAboutSub() {
        return aboutSub;
    }

    public void setAboutSub(String aboutSub) {
        this.aboutSub = aboutSub;
    }

    public String getAboutTopic() {
        return aboutTopic;
    }

    public void setAboutTopic(String aboutTopic) {
        this.aboutTopic = aboutTopic;
    }

    public String getAboutImg() {
        return aboutImg;
    }

    public void setAboutImg(String aboutImg) {
        this.aboutImg = aboutImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
