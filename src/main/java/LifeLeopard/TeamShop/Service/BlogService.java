package LifeLeopard.TeamShop.Service;

import LifeLeopard.TeamShop.Models.About;
import LifeLeopard.TeamShop.Models.Blog;
import LifeLeopard.TeamShop.Responsibility.BlogReps;
import LifeLeopard.TeamShop.UploadImagesProduct.FileUploadUtil;

import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    public static String UPLOAD_DIRECTORY = Paths.get("")
            .toAbsolutePath()
            .toString() + "/src/main/resources/static/images/blogs";
    public static String DELETE_DIRECTORY = Paths.get("")
            .toAbsolutePath()
            .toString() + "/src/main/resources/static";
    @Autowired
    private BlogReps blogReps;

    public List<Blog> findAllBlog(){
        return blogReps.findAll();
    }

    public Optional<Blog> findBlogById(int id) {
        return blogReps.findById(id);
    }
    public int CreateBlog(Blog blog, MultipartFile multipartFile) throws IOException {
        blogReps.save(blog);
        String Check = StringUtils.getFilename(multipartFile.getOriginalFilename());
        if(!Check.isEmpty()){
            String FileName = RandomString.make(10);
            String Ex = StringUtils.getFilenameExtension(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
            FileName = FileName + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            FileName = FileName.replace('.','-');
            FileName = FileName.replace(' ','-');
            FileName = FileName + "."+ Ex;
            String uploadDir = UPLOAD_DIRECTORY + "/"+ blog.getBlogId();
            String urlImg = new String();
            urlImg = "/images/blogs/" + blog.getBlogId() +"/"+ FileName;
            blog.setImg(urlImg);
            blogReps.save(blog);
            FileUploadUtil.saveFile(uploadDir,FileName,multipartFile);
        }
        return blog.getBlogId();
    }
    public List<Blog> getAllBlog(){
        return blogReps.findAll();
    }
    public Optional<Blog> getBlogById(int id){
        return blogReps.findById(id);
    }
    public void updateBlog(Blog blogDetails,MultipartFile multipartFiles) throws IOException {
        Blog blog = blogReps.getById(blogDetails.getBlogId());
        String urlImg = new String();
        String FileName = StringUtils.getFilename(multipartFiles.getOriginalFilename());
        if(!FileName.isEmpty()){
            File file = new File(DELETE_DIRECTORY + blog.getImg());
            if (file.delete() || true) {
                String FileNameUpdate = RandomString.make(10);
                String Ex = StringUtils.getFilenameExtension(StringUtils.cleanPath(multipartFiles.getOriginalFilename()));
                FileNameUpdate = FileNameUpdate + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                FileNameUpdate = FileNameUpdate.replace('.','-');
                FileNameUpdate = FileNameUpdate.replace(' ','-');
                FileNameUpdate = FileNameUpdate + "."+ Ex;
                String uploadDir = UPLOAD_DIRECTORY + "/"+ blog.getBlogId();

                urlImg = "/images/blogs/" + blog.getBlogId() +"/"+ FileNameUpdate;
                FileUploadUtil.saveFile(uploadDir,FileNameUpdate,multipartFiles);
                System.out.println(FileNameUpdate);
                System.out.println(file.getName() + " is deleted!");
            } else {
                System.out.println("Delete operation is failed.");
            }

        }else{
            System.out.println("file null.");
        }
        if(!FileName.isEmpty()){
            blog.setImg(urlImg);
        }
        blog.setBlogTitle(blogDetails.getBlogTitle());
        blog.setBlogContent(blogDetails.getBlogContent());
        blog.setBlogAuthor(blogDetails.getBlogAuthor());
        blog.setBlogDesc(blogDetails.getBlogDesc());
        blogReps.save(blog);
    }
}