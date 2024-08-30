package LifeLeopard.TeamShop.Responsibility;

import LifeLeopard.TeamShop.Models.Blog;
import LifeLeopard.TeamShop.Models.BlogComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BlogCmtReps extends JpaRepository<BlogComment,Integer> {
    List<BlogComment> findAllByBlog(Blog blog);
}
