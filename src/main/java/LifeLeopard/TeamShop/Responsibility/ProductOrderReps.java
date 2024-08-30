package LifeLeopard.TeamShop.Responsibility;

import LifeLeopard.TeamShop.Models.Order;
import LifeLeopard.TeamShop.Models.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOrderReps extends JpaRepository<ProductOrder,Integer> {
    List<ProductOrder> findAllByOrder(Order order);
 }
