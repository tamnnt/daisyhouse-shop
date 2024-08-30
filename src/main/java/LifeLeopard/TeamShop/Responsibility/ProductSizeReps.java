package LifeLeopard.TeamShop.Responsibility;

import LifeLeopard.TeamShop.Models.Product;
import LifeLeopard.TeamShop.Models.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Repository
@Transactional
public interface ProductSizeReps extends JpaRepository<ProductSize,Integer> {
    List<ProductSize> findAllByProduct(Product product);
    @Query(value = "SELECT p FROM ProductSize p WHERE p.ProductSizeId IN :listProduct")
    List<ProductSize> getProductCart(@Param("listProduct") Collection<Integer> listProduct);
}
