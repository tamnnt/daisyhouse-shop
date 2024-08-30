package LifeLeopard.TeamShop.Service;

import LifeLeopard.TeamShop.Models.Product;
import LifeLeopard.TeamShop.Models.ProductComment;
import LifeLeopard.TeamShop.Responsibility.ProductCommentReps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCommentService {
    @Autowired
    private ProductCommentReps productCommentReps;
    public List<ProductComment> getAllCmtByProduct(Product product){
        return productCommentReps.findAllByProduct(product);
    }

    public Optional<ProductComment> findById(int id) {
        return productCommentReps.findById(id);
    }

    public void Save(ProductComment productComment) {
        productCommentReps.save(productComment);
    }
}
