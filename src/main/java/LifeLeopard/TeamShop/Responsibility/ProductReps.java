package LifeLeopard.TeamShop.Responsibility;

import LifeLeopard.TeamShop.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;


@Repository
@Transactional
public interface ProductReps extends JpaRepository<Product,Integer> {
    List<Product> findAllByProductNameContaining(String productName);
    List<Product> findAllByStatusIs(int Status);
    @Query(value = "SELECT p FROM Product p WHERE p.ProductId IN :listProduct")
    List<Product> getProductEvent(@Param("listProduct") Collection<Integer> listProduct);

    @Query(value = "SELECT * FROM table_products  ORDER BY RAND() Limit 10 ", nativeQuery = true)
    List<Product> getListProductOther();

}
