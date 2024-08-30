package LifeLeopard.TeamShop.Service;

import LifeLeopard.TeamShop.Models.Product;
import LifeLeopard.TeamShop.Models.ProductImages;
import LifeLeopard.TeamShop.Models.ProductSize;
import LifeLeopard.TeamShop.Responsibility.ProductImagesReps;
import LifeLeopard.TeamShop.Responsibility.ProductReps;
import LifeLeopard.TeamShop.Responsibility.ProductSizeReps;
import LifeLeopard.TeamShop.UploadImagesProduct.FileUploadUtil;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    public static String UPLOAD_DIRECTORY = Paths.get("")
            .toAbsolutePath()
            .toString() + "/src/main/resources/static/images/product";
    public static String DELETE_DIRECTORY = Paths.get("")
            .toAbsolutePath()
            .toString() + "/src/main/resources/static";
    //    public static String UPLOAD_DIRECTORY = Paths.get("")
//            .toAbsolutePath()
//            .toString() + "/TeamShop/src/main/resources/static/images/product";
//    public static String DELETE_DIRECTORY = Paths.get("")
//            .toAbsolutePath()
//            .toString() + "/TeamShop/src/main/resources/static";
    @Autowired
    ProductReps productReps;
    @Autowired
    ProductSizeReps productSizeReps;
    @Autowired
    ProductImagesReps productImagesReps;
    public List<Product> getAllProduct(){
        return productReps.findAllByStatusIs(1);
    }
    public Product getById(int id){
        return productReps.getById(id);
    }
    public List<Product> getOther(){
        return productReps.getListProductOther();
    }
    public List<Product> getProductByEvent(String listProductId){
        String[] arrayListProductId = listProductId.split(",");
        Collection<Integer> ClistProductId = new ArrayList<>();
        for (String productId: arrayListProductId) {
            Integer number = Integer.parseInt(productId);
            ClistProductId.add(number);
        }
        return productReps.getProductEvent(ClistProductId);
    }
    public List<ProductSize> getProductCart(String listProductSizeId){
        String[] arrayListProductId = listProductSizeId.split("-");
        Collection<Integer> ClistProductId = new ArrayList<>();
        for (String productId: arrayListProductId) {
            Integer number = Integer.parseInt(productId);
            ClistProductId.add(number);
        }
        return productSizeReps.getProductCart(ClistProductId);
    }
    @Transactional
    public Product save(Product product, List<ProductSize> productSizeList){
        productReps.save(product);
        for (ProductSize productSize:productSizeList) {
            productSize.setProduct(product);
            System.out.println(productSize.toString());
        }
        productSizeReps.saveAll(productSizeList);
        return product;
    }
    public void saveImgProduct(Product product, MultipartFile[] multipartFiles) throws IOException {
        List<String> urlImages =new ArrayList<>(4);
        for (MultipartFile multipartFile:multipartFiles) {
            String Check = StringUtils.getFilename(multipartFile.getOriginalFilename());
            if(!Check.isEmpty()){
                String FileName = RandomString.make(10);
                String Ex = StringUtils.getFilenameExtension(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
                FileName = FileName + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                FileName = FileName.replace('.','-');
                FileName = FileName.replace(' ','-');
                FileName = FileName + "."+ Ex;
                String uploadDir = UPLOAD_DIRECTORY + "/"+ product.getProductId();
                String urlImg = new String();
                urlImg = "/images/product/" + product.getProductId() +"/"+ FileName;
                urlImages.add(urlImg);
                FileUploadUtil.saveFile(uploadDir,FileName,multipartFile);
                System.out.println(FileName);
            }
        }
        for (String url:urlImages) {
            ProductImages productImages = new ProductImages();
            productImages.setProduct(product);
            productImages.setUrl(url);
            productImagesReps.save(productImages);
        }
        Product product1 = productReps.getById(product.getProductId());
        product1.setImages(urlImages.get(0));
        productReps.save(product1);
    }
    public void updateProduct(int id,Product productDetails,MultipartFile[] multipartFiles,int[] quantity,int[] Status,double[] price) throws IOException {
        Product product = productReps.getById(id);
        List<ProductSize> productSizeList = productSizeReps.findAllByProduct(product);
        List<ProductImages> productImagesList = productImagesReps.findAllByProduct(product);
        String thumbnail = null;
        for(int i = 0 ; i < productSizeList.size() ;i++){
            productSizeList.get(i).setQuantity(quantity[i]);
            productSizeList.get(i).setStatus(Status[i]);
            productSizeList.get(i).setPrice(price[i]);
        }
        for(int i = 0 ;i <4 ;i ++){
            String FileName = StringUtils.getFilename(multipartFiles[i].getOriginalFilename());
            if(!FileName.isEmpty()){
                File file = new File("a.txt");
                if(i < productImagesList.size()){
                    file = new File(DELETE_DIRECTORY +productImagesList.get(i).getUrl());
                }else{
                    ProductImages productImages = new ProductImages();
                    productImages.setProduct(product);
                    productImagesList.add(productImages);
                }
                if (file.delete() || true) {
                    String FileNameUpdate = RandomString.make(10);
                    String Ex = StringUtils.getFilenameExtension(StringUtils.cleanPath(multipartFiles[i].getOriginalFilename()));
                    FileNameUpdate = FileNameUpdate + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                    FileNameUpdate = FileNameUpdate.replace('.','-');
                    FileNameUpdate = FileNameUpdate.replace(' ','-');
                    FileNameUpdate = FileNameUpdate + "."+ Ex;
                    String uploadDir = UPLOAD_DIRECTORY + "/"+ product.getProductId();
                    String urlImg = new String();
                    urlImg = "/images/product/" + product.getProductId() +"/"+ FileNameUpdate;
                    if( i == 0){
                        thumbnail = urlImg;
                    }
                    if(i >= productImagesList.size()){
                        productImagesList.get(productImagesList.size()-1).setUrl(urlImg);
                    }else{
                        productImagesList.get(i).setUrl(urlImg);
                    }
                    FileUploadUtil.saveFile(uploadDir,FileNameUpdate,multipartFiles[i]);
                    System.out.println(FileNameUpdate);
                    System.out.println(file.getName() + " is deleted!");
                } else {
                    System.out.println("Delete operation is failed.");
                }

            }else{
                System.out.println("file null.");
            }
        }
        product.setProductName(productDetails.getProductName());
        product.setCategory(productDetails.getCategory());
        product.setShortDescription(productDetails.getShortDescription());
        product.setDescription(productDetails.getDescription());
        product.setStatus(productDetails.getStatus());
        product.setWeight(productDetails.getWeight());
        product.setDimensions(productDetails.getDimensions());
        product.setMaterials(productDetails.getMaterials());
        product.setPircePreview(productDetails.getPircePreview());
        if(thumbnail != null){
            product.setImages(thumbnail);
        }
        productReps.save(product);
        productSizeReps.saveAll(productSizeList);
        productImagesReps.saveAll(productImagesList);
    }

    public List<Product> getAllByName(String keyword) {
        return productReps.findAllByProductNameContaining(keyword);
    }

    public Optional<Product> findById(int id) {
        return productReps.findById(id);
    }
}
