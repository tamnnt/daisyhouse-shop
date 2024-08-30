package LifeLeopard.TeamShop.Responsibility;

import LifeLeopard.TeamShop.Models.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductPageReps extends PagingAndSortingRepository<Product,Integer> {
}
