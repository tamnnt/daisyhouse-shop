package LifeLeopard.TeamShop.Models;

import javax.persistence.*;

@Entity
@Table(name = "table_product_images")
public class ProductImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Product_images_id")
    private int ProductImagesId;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "Product_id")
    private Product product;

    @Column(name = "Product_imgaes_url")
    private String url;
    public ProductImages(){}

    public ProductImages(int productImagesId, Product product, String url) {
        ProductImagesId = productImagesId;
        this.product = product;
        this.url = url;
    }


    public int getProductImagesId() {
        return ProductImagesId;
    }

    public void setProductImagesId(int productImagesId) {
        ProductImagesId = productImagesId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
