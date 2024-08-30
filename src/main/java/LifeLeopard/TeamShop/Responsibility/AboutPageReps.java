package LifeLeopard.TeamShop.Responsibility;

import LifeLeopard.TeamShop.Models.About;
import LifeLeopard.TeamShop.Models.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AboutPageReps extends PagingAndSortingRepository<About,Integer> {
}
