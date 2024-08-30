package LifeLeopard.TeamShop.Models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "table_product_size")
public class ProductSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Product_Size_id")
    private int ProductSizeId;
    @ManyToOne(fetch=FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "Product_id")
    private Product product;
    @ManyToOne(fetch=FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "Size_id")
    private Size size;
    @Column(name = "Quantity")
    private int Quantity;
    @Column(name = "Price")
    private double Price;
    @Column(name = "Status")
    private int status;
    public ProductSize(){}

    public ProductSize(int productSizeId, Product product, Size size, int quantity, double price, int status) {
        ProductSizeId = productSizeId;
        this.product = product;
        this.size = size;
        Quantity = quantity;
        Price = price;
        this.status = status;
    }

    public int getProductSizeId() {
        return ProductSizeId;
    }

    public void setProductSizeId(int productSizeId) {
        ProductSizeId = productSizeId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProductSize{" +
                ", product=" + product +
                ", size=" + size +
                ", Quantity=" + Quantity +
                ", Price=" + Price +
                ", status=" + status +
                '}';
    }
}
