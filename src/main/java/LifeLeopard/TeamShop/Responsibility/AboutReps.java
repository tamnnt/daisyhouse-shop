package LifeLeopard.TeamShop.Responsibility;

import LifeLeopard.TeamShop.Models.About;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AboutReps extends JpaRepository<About,Integer> {
    List<About> findAllByStatusIs(int status);
}
