package LifeLeopard.TeamShop.Models;

import javax.persistence.*;

@Entity
@Table(name = "table_size")
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Size_id")
    private int SizeId;
    @Column(name = "Size_name")
    private String SizeName;
    public Size(){}

    public Size(int sizeId, String sizeName) {
        SizeId = sizeId;
        SizeName = sizeName;
    }

    public int getSizeId() {
        return SizeId;
    }

    public void setSizeId(int sizeId) {
        SizeId = sizeId;
    }

    public String getSizeName() {
        return SizeName;
    }

    public void setSizeName(String sizeName) {
        SizeName = sizeName;
    }

    @Override
    public String toString() {
        return "Size{" +
                "SizeId=" + SizeId +
                ", SizeName='" + SizeName + '\'' +
                '}';
    }
}
