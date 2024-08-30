package LifeLeopard.TeamShop.Responsibility;

import LifeLeopard.TeamShop.Models.HomeSlide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeSlideReps extends JpaRepository<HomeSlide,Integer> {
    List<HomeSlide> findAllByStatusIs(int status);
}
