package LifeLeopard.TeamShop.Service;

import LifeLeopard.TeamShop.Models.Blog;
import LifeLeopard.TeamShop.Models.BlogComment;
import LifeLeopard.TeamShop.Responsibility.BlogCmtReps;
import LifeLeopard.TeamShop.Responsibility.BlogReps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogCmtService {
    @Autowired
    private BlogCmtReps blogCmtReps;


    //lấy tất cả cmt của blog chỉ định
    public List<BlogComment> findAllByblog(Blog blog){
        return blogCmtReps.findAllByBlog(blog);
    }
//lưu một cmt
    public void save(BlogComment blogComment) {
        blogCmtReps.save(blogComment);
    }
//tìm một blogcmt theo id
    public Optional<BlogComment> findbyId(int id) {
        return blogCmtReps.findById(id);
    }
    //xoa blogcmt theo di
    public void delete(int id) {
        blogCmtReps.deleteById(id);
    }
}
