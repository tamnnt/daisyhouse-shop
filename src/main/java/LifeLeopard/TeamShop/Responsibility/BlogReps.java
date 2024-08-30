package LifeLeopard.TeamShop.Responsibility;

import LifeLeopard.TeamShop.Models.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogReps extends JpaRepository<Blog,Integer> {
}