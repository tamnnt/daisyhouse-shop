package LifeLeopard.TeamShop.Responsibility;

import LifeLeopard.TeamShop.Models.Customers;
import LifeLeopard.TeamShop.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface OrderReps extends JpaRepository<Order,Integer> {
    List<Order> findAllByCustomers(Customers customers);
    List<Order> findAllByStatusIn(Collection<Integer> CStatus);
}
