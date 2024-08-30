package LifeLeopard.TeamShop.Service;

import LifeLeopard.TeamShop.Models.Event;
import LifeLeopard.TeamShop.Responsibility.EventReps;
import LifeLeopard.TeamShop.UploadImagesProduct.FileUploadUtil;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import javax.mail.Multipart;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


@Service
public class EventService {
    public static String UPLOAD_DIRECTORY = Paths.get("")
            .toAbsolutePath()
            .toString() + "/src/main/resources/static/images/event";
    public static String DELETE_DIRECTORY = Paths.get("")
            .toAbsolutePath()
            .toString() + "/src/main/resources/static";
    @Autowired
    private EventReps eventReps;
    public List<Event> getAllEventOn(){
        return eventReps.findAllByStatusIs(1);
    }
    public Event getEventByID(int id){
        return eventReps.getById(id);
    }
    public int createEvent(Event event, MultipartFile multipartFile) throws IOException {
        event.setEventImg("null");
        eventReps.save(event);
        String Check = StringUtils.getFilename(multipartFile.getOriginalFilename());
        if(!Check.isEmpty()){
            String FileName = RandomString.make(10);
            String Ex = StringUtils.getFilenameExtension(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
            FileName = FileName + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            FileName = FileName.replace('.','-');
            FileName = FileName.replace(' ','-');
            FileName = FileName + "."+ Ex;
            String uploadDir = UPLOAD_DIRECTORY + "/"+ event.getEventId();
            String urlImg = new String();
            urlImg = "/images/event/" + event.getEventId() +"/"+ FileName;
            Event event1 = eventReps.getById(event.getEventId());
            event1.setEventImg(urlImg);
            eventReps.save(event1);

            FileUploadUtil.saveFile(uploadDir,FileName,multipartFile);
        }
        return event.getEventId();
    }
    public List<Event> getAllEvent(){
        return eventReps.findAll();
    }
    public Optional<Event> findById(int id){
        return eventReps.findById(id);
    }
    public void updateEvent(Event eventDetails, MultipartFile multipartFile) throws IOException{
        Event event = eventReps.getById(eventDetails.getEventId());
        String urlImg = new String();
        String FileName = StringUtils.getFilename(multipartFile.getOriginalFilename());
        if(!FileName.isEmpty()){
            File file = new File(DELETE_DIRECTORY + event.getEventImg());
            if (file.delete() || true) {
                String FileNameUpdate = RandomString.make(10);
                String Ex = StringUtils.getFilenameExtension(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
                FileNameUpdate = FileNameUpdate + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                FileNameUpdate = FileNameUpdate.replace('.','-');
                FileNameUpdate = FileNameUpdate.replace(' ','-');
                FileNameUpdate = FileNameUpdate + "."+ Ex;
                String uploadDir = UPLOAD_DIRECTORY + "/"+ event.getEventId();

                urlImg = "/images/event/" + event.getEventId() +"/"+ FileNameUpdate;
                FileUploadUtil.saveFile(uploadDir,FileNameUpdate,multipartFile);
                System.out.println(FileNameUpdate);
                System.out.println(file.getName() + " is deleted!");
            } else {
                System.out.println("Delete operation is failed.");
            }

        }else{
            System.out.println("file null.");
        }
        event.setEventName(eventDetails.getEventName());
        if(!FileName.isEmpty()){
            event.setEventImg(urlImg);
        }
        event.setEventTopic(eventDetails.getEventTopic());
        event.setStatus(eventDetails.getStatus());
        event.setListProductId(eventDetails.getListProductId());
        eventReps.save(event);
    }
}
