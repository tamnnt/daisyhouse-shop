package LifeLeopard.TeamShop.Responsibility;

import LifeLeopard.TeamShop.Models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventReps extends JpaRepository<Event,Integer> {
    List<Event> findAllByStatusIs(int Status);
}
