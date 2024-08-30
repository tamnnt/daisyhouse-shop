package LifeLeopard.TeamShop.Models;

import javax.persistence.*;

@Entity
@Table(name ="table_event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private int eventId;
    @Column(name = "event_name")
    private String eventName;
    @Column(name = "event_topic")
    private String eventTopic;
    @Column(name = "event_img")
    private String eventImg;
    @Column(name = "list_product_id")
    private String listProductId;
    @Column(name = "status")
    private int status;
    public Event(){}

    public Event(int eventId, String eventName, String eventTopic, String eventImg, String listProductId, int status) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventTopic = eventTopic;
        this.eventImg = eventImg;
        this.listProductId = listProductId;
        this.status = status;
    }

    public Event(String eventName, String eventTopic, String eventImg, String listProductId, int status) {
        this.eventName = eventName;
        this.eventTopic = eventTopic;
        this.eventImg = eventImg;
        this.listProductId = listProductId;
        this.status = status;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventTopic() {
        return eventTopic;
    }

    public void setEventTopic(String eventTopic) {
        this.eventTopic = eventTopic;
    }

    public String getEventImg() {
        return eventImg;
    }

    public void setEventImg(String eventImg) {
        this.eventImg = eventImg;
    }

    public String getListProductId() {
        return listProductId;
    }

    public void setListProductId(String listProductId) {
        this.listProductId = listProductId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
