package LifeLeopard.TeamShop.Service;

import LifeLeopard.TeamShop.Models.*;
import LifeLeopard.TeamShop.Responsibility.AboutReps;
import LifeLeopard.TeamShop.UploadImagesProduct.FileUploadUtil;
import net.bytebuddy.utility.RandomString;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class AboutService {
    public static String UPLOAD_DIRECTORY = Paths.get("")
            .toAbsolutePath()
            .toString() + "/src/main/resources/static/images/about";
    public static String DELETE_DIRECTORY = Paths.get("")
            .toAbsolutePath()
            .toString() + "/src/main/resources/static";
    @Autowired
    private AboutReps aboutReps;
    public Optional<About> findById(int id){
        return aboutReps.findById(id);
    }
    public List<About> getAllAboutOn(){
        return aboutReps.findAllByStatusIs(1);
    }
    public int CreateAboutInfo(About about, MultipartFile multipartFile) throws IOException {
        aboutReps.save(about);
        String Check = StringUtils.getFilename(multipartFile.getOriginalFilename());
        if(!Check.isEmpty()){
            String FileName = RandomString.make(10);
            String Ex = StringUtils.getFilenameExtension(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
            FileName = FileName + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            FileName = FileName.replace('.','-');
            FileName = FileName.replace(' ','-');
            FileName = FileName + "."+ Ex;
            String uploadDir = UPLOAD_DIRECTORY + "/"+ about.getAboutId();
            String urlImg = new String();
            urlImg = "/images/about/" + about.getAboutId() +"/"+ FileName;
            about.setAboutImg(urlImg);
            aboutReps.save(about);
            FileUploadUtil.saveFile(uploadDir,FileName,multipartFile);
        }
        return about.getAboutId();
    }
    public List<About> GetAllAboutInfo(){
        return aboutReps.findAll();
    }
    public About getById(int id){
        return aboutReps.getById(id);
    }
    public void updateAbout(About aboutDetails,MultipartFile multipartFiles) throws IOException {
        About about = aboutReps.getById(aboutDetails.getAboutId());
        String urlImg = new String();
        String FileName = StringUtils.getFilename(multipartFiles.getOriginalFilename());
        if(!FileName.isEmpty()){
            File file = new File(DELETE_DIRECTORY + about.getAboutImg());
            if (file.delete() || true) {
                String FileNameUpdate = RandomString.make(10);
                String Ex = StringUtils.getFilenameExtension(StringUtils.cleanPath(multipartFiles.getOriginalFilename()));
                FileNameUpdate = FileNameUpdate + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                FileNameUpdate = FileNameUpdate.replace('.','-');
                FileNameUpdate = FileNameUpdate.replace(' ','-');
                FileNameUpdate = FileNameUpdate + "."+ Ex;
                String uploadDir = UPLOAD_DIRECTORY + "/"+ about.getAboutId();

                urlImg = "/images/product/" + about.getAboutId() +"/"+ FileNameUpdate;
                FileUploadUtil.saveFile(uploadDir,FileNameUpdate,multipartFiles);
                System.out.println(FileNameUpdate);
                System.out.println(file.getName() + " is deleted!");
            } else {
                System.out.println("Delete operation is failed.");
            }

        }else{
            System.out.println("file null.");
        }
        about.setAboutSub(aboutDetails.getAboutSub());
        about.setAboutTopic(aboutDetails.getAboutTopic());
        if(!FileName.isEmpty()){
            about.setAboutImg(urlImg);
        }
        about.setAboutTitle(aboutDetails.getAboutTitle());
        about.setName(aboutDetails.getName());
        about.setStatus(aboutDetails.getStatus());
        aboutReps.save(about);
    }

}
