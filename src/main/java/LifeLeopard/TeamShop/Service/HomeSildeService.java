package LifeLeopard.TeamShop.Service;

import LifeLeopard.TeamShop.Models.About;
import LifeLeopard.TeamShop.Models.Event;
import LifeLeopard.TeamShop.Models.HomeSlide;
import LifeLeopard.TeamShop.Responsibility.HomeSlideReps;
import LifeLeopard.TeamShop.UploadImagesProduct.FileUploadUtil;
import net.bytebuddy.utility.RandomString;
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
public class HomeSildeService {
    public static String UPLOAD_DIRECTORY = Paths.get("")
            .toAbsolutePath()
            .toString() + "/src/main/resources/static/images/slide";
    public static String DELETE_DIRECTORY = Paths.get("")
            .toAbsolutePath()
            .toString() + "/src/main/resources/static";
    @Autowired
    HomeSlideReps homeSlideReps;
    public List<HomeSlide> getAllHomeSlideOn(){
        return homeSlideReps.findAllByStatusIs(1);
    }
    public List<HomeSlide> getAllSlide(){
        return homeSlideReps.findAll();
    }

    public Optional<HomeSlide> getSlideHome(int slideID) {
        return homeSlideReps.findById(slideID);
    }

    public void ceateHomeSlide(HomeSlide homeSlideDetails, MultipartFile multipartFile) throws IOException {
        homeSlideReps.save(homeSlideDetails);
        String Check = StringUtils.getFilename(multipartFile.getOriginalFilename());
        if(!Check.isEmpty()){
            String FileName = RandomString.make(10);
            String Ex = StringUtils.getFilenameExtension(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
            FileName = FileName + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            FileName = FileName.replace('.','-');
            FileName = FileName.replace(' ','-');
            FileName = FileName + "."+ Ex;
            String uploadDir = UPLOAD_DIRECTORY + "/"+ homeSlideDetails.getSlideID();
            String urlImg = new String();
            urlImg = "/images/slide/" + homeSlideDetails.getSlideID() +"/"+ FileName;
            homeSlideDetails.setSlideImg(urlImg);
            homeSlideReps.save(homeSlideDetails);
            FileUploadUtil.saveFile(uploadDir,FileName,multipartFile);
        }

    }

    public void updateHoneSlide(HomeSlide homeSlideDetails, MultipartFile multipartFile) throws IOException {
        HomeSlide homeSlide = homeSlideReps.getById(homeSlideDetails.getSlideID());
        String urlImg = new String();
        String FileName = StringUtils.getFilename(multipartFile.getOriginalFilename());
        if(!FileName.isEmpty()){
            File file = new File(DELETE_DIRECTORY + homeSlide.getSlideImg());
            if (file.delete() || true) {
                String FileNameUpdate = RandomString.make(10);
                String Ex = StringUtils.getFilenameExtension(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
                FileNameUpdate = FileNameUpdate + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                FileNameUpdate = FileNameUpdate.replace('.','-');
                FileNameUpdate = FileNameUpdate.replace(' ','-');
                FileNameUpdate = FileNameUpdate + "."+ Ex;
                String uploadDir = UPLOAD_DIRECTORY + "/"+ homeSlideDetails.getSlideID();

                urlImg = "/images/slide/" + homeSlideDetails.getSlideID() +"/"+ FileNameUpdate;
                FileUploadUtil.saveFile(uploadDir,FileNameUpdate,multipartFile);
                System.out.println(FileNameUpdate);
                System.out.println(file.getName() + " is deleted!");
            } else {
                System.out.println("Delete operation is failed.");
            }

        }else{
            System.out.println("file null.");
        }
        homeSlide.setSlideSub(homeSlideDetails.getSlideSub());
        homeSlide.setSlideTitle(homeSlideDetails.getSlideTitle());
        homeSlide.setStatus(homeSlideDetails.getStatus());
        if(!FileName.isEmpty()){
            homeSlide.setSlideImg(urlImg);
        }
        homeSlideReps.save(homeSlide);
    }
}
