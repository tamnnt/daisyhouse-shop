package LifeLeopard.TeamShop.Responsibility;

import LifeLeopard.TeamShop.Models.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface SizeReps extends JpaRepository<Size,Integer> {
}
