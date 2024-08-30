package LifeLeopard.TeamShop.Models;

public class ProductCart {
    private Product product;
    private ProductSize productSize;
    private int quantity;
    private double total;
    public ProductCart(){}

    public ProductCart(Product product, ProductSize productSize, int quantity, double total) {
        this.product = product;
        this.productSize = productSize;
        this.quantity = quantity;
        this.total = total;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ProducCart{" +
                "productSize=" + productSize.getProductSizeId() +
                ", quantity=" + quantity +
                ", total=" + total +
                '}';
    }
}
