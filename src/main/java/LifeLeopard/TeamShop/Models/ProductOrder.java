package LifeLeopard.TeamShop.Models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "table_product_order")
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Product_Order_id")
    private int productOrderId;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "Order_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name ="Product_id")
    private ProductSize productSize;
    @Column(name = "Quantity")
    private int quantity;
    @Column(name = "Price")
    private double price;
    public ProductOrder(){}

    public ProductOrder(int productOrderId, Order order, ProductSize productSize, int quantity, double price) {
        this.productOrderId = productOrderId;
        this.order = order;
        this.productSize = productSize;
        this.quantity = quantity;
        this.price = price;
    }

    public ProductOrder(Order order, ProductSize productSize, int quantity, double price) {
        this.order = order;
        this.productSize = productSize;
        this.quantity = quantity;
        this.price = price;
    }

    public int getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(int productOrderId) {
        this.productOrderId = productOrderId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ProductSize getProductSize() {
        return productSize;
    }

    public void setProductSize(ProductSize productSize) {
        this.productSize = productSize;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
