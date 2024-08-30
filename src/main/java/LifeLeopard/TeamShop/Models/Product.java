package LifeLeopard.TeamShop.Models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "table_products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Product_id")
    private int ProductId;
    @ManyToOne
    @JoinColumn(name = "Category_id")
    private Category category;
    @Column(name = "Product_name")
    private String productName;
    @Column(name = "Product_images_preview")
    private String images;
    @Column(name = "Short_description")
    private String ShortDescription;
    @Column(name = "description")
    private String Description;
    @Column(name = "Quantity")
    private int Quantity;
    @Column(name = "pirce_preview")
    private double pircePreview;
    @Column(name = "Status")
    private int status;
    @Column(name = "Weight")
    private double weight;
    @Column(name = "Dimensions")
    private String dimensions;
    @Column(name = "Materials")
    private String materials;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImages> productImagesList;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<ProductSize> productSizeList;
    public Product(){}

    public Product(Category category, String productName, String images, String shortDescription, String description, int quantity, int status, List<ProductImages> productImagesList, List<ProductSize> productSizeList) {
        this.category = category;
        this.productName = productName;
        this.images = images;
        ShortDescription = shortDescription;
        Description = description;
        Quantity = quantity;
        this.status = status;
        this.productImagesList = productImagesList;
        this.productSizeList = productSizeList;
    }

    public Product(int productId, Category category, String productName, String images, String shortDescription, String description, int quantity, int status, List<ProductImages> productImagesList) {
        ProductId = productId;
        this.category = category;
        this.productName = productName;
        this.images = images;
        ShortDescription = shortDescription;
        Description = description;
        Quantity = quantity;
        this.status = status;
        this.productImagesList = productImagesList;
    }

    public Product(int productId, Category category, String productName, String images, String shortDescription, String description, int quantity, double pircePreview, int status, double weight, String dimensions, String materials, List<ProductImages> productImagesList, List<ProductSize> productSizeList) {
        ProductId = productId;
        this.category = category;
        this.productName = productName;
        this.images = images;
        ShortDescription = shortDescription;
        Description = description;
        Quantity = quantity;
        this.pircePreview = pircePreview;
        this.status = status;
        this.weight = weight;
        this.dimensions = dimensions;
        this.materials = materials;
        this.productImagesList = productImagesList;
        this.productSizeList = productSizeList;
    }

    public Product(int productId, Category category, String images, String shortDescription, String description, int quantity, int status, List<ProductImages> productImagesList) {
        ProductId = productId;
        this.category = category;
        this.images = images;
        ShortDescription = shortDescription;
        Description = description;
        Quantity = quantity;
        this.status = status;
        this.productImagesList = productImagesList;
    }

    public Product(int productId, Category category, String images, String shortDescription, String description, int quantity, int status) {
        ProductId = productId;
        this.category = category;
        this.images = images;
        ShortDescription = shortDescription;
        Description = description;
        Quantity = quantity;
        this.status = status;
    }

    public Product(int productId, Category category, String productName, String images, String shortDescription, String description, int quantity, int status, double weight, String dimensions, String materials) {
        ProductId = productId;
        this.category = category;
        this.productName = productName;
        this.images = images;
        ShortDescription = shortDescription;
        Description = description;
        Quantity = quantity;
        this.status = status;
        this.weight = weight;
        this.dimensions = dimensions;
        this.materials = materials;
    }

    public Product(int productId, Category category, String productName, String images, String shortDescription, String description, int quantity, int status, List<ProductImages> productImagesList, List<ProductSize> productSizeList) {
        ProductId = productId;
        this.category = category;
        this.productName = productName;
        this.images = images;
        ShortDescription = shortDescription;
        Description = description;
        Quantity = quantity;
        this.status = status;
        this.productImagesList = productImagesList;
        this.productSizeList = productSizeList;
    }

    public Product(int productId, Category category, String productName, String images, String shortDescription, String description, int quantity, int status, double weight, String dimensions, String materials, List<ProductImages> productImagesList, List<ProductSize> productSizeList) {
        ProductId = productId;
        this.category = category;
        this.productName = productName;
        this.images = images;
        ShortDescription = shortDescription;
        Description = description;
        Quantity = quantity;
        this.status = status;
        this.weight = weight;
        this.dimensions = dimensions;
        this.materials = materials;
        this.productImagesList = productImagesList;
        this.productSizeList = productSizeList;
    }

    public Product(Category category, String productName, String images, String shortDescription, String description, int quantity, int status, double weight, String dimensions, String materials, List<ProductImages> productImagesList, List<ProductSize> productSizeList) {
        this.category = category;
        this.productName = productName;
        this.images = images;
        ShortDescription = shortDescription;
        Description = description;
        Quantity = quantity;
        this.status = status;
        this.weight = weight;
        this.dimensions = dimensions;
        this.materials = materials;
        this.productImagesList = productImagesList;
        this.productSizeList = productSizeList;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getShortDescription() {
        return ShortDescription;
    }

    public void setShortDescription(String shortDescription) {
        ShortDescription = shortDescription;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public List<ProductImages> getProductImagesList() {
        return productImagesList;
    }

    public void setProductImagesList(List<ProductImages> productImagesList) {
        this.productImagesList = productImagesList;
    }

    public double getPircePreview() {
        return pircePreview;
    }

    public void setPircePreview(double pircePreview) {
        this.pircePreview = pircePreview;
    }

    public List<ProductSize> getProductSizeList() {
        return productSizeList;
    }

    public void setProductSizeList(List<ProductSize> productSizeList) {
        this.productSizeList = productSizeList;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    @Override
    public String toString() {
        return "Product{" +
                "ProductId=" + ProductId +
                ", category=" + category +
                ", productName='" + productName + '\'' +
                ", images='" + images + '\'' +
                ", ShortDescription='" + ShortDescription + '\'' +
                ", Description='" + Description + '\'' +
                ", Quantity=" + Quantity +
                ", status=" + status +
                ", weight=" + weight +
                ", dimensions='" + dimensions + '\'' +
                ", materials='" + materials + '\'' +
                ", productImagesList=" + productImagesList +
                ", productSizeList=" + productSizeList +
                '}';
    }
}
