package LifeLeopard.TeamShop.Responsibility;

import LifeLeopard.TeamShop.Models.Product;
import LifeLeopard.TeamShop.Models.ProductImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ProductImagesReps extends JpaRepository<ProductImages,Integer> {
    List<ProductImages> findAllByProduct(Product product);
}
