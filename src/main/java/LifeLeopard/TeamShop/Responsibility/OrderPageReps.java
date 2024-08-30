package LifeLeopard.TeamShop.Responsibility;

import LifeLeopard.TeamShop.Models.Order;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;
import java.util.List;

public interface OrderPageReps extends PagingAndSortingRepository<Order, Integer> {

}
