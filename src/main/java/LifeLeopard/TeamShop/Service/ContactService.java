package LifeLeopard.TeamShop.Service;

import LifeLeopard.TeamShop.Models.Contact;
import LifeLeopard.TeamShop.Responsibility.ContactReps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContactService {
    @Autowired
    private ContactReps contactReps;
    public Contact getContact(){
        return contactReps.findByStatusIs(1);
    }
    public boolean updateContact(Contact contactDetails){
       Contact contact = contactReps.findByStatusIs(1);
       if(contact != null){
           contact.setAddress(contactDetails.getAddress());
           contact.setEmailsuport(contactDetails.getEmailsuport());
           contact.setPhoneNumber(contactDetails.getPhoneNumber());
           contactReps.save(contact);
           return true;
       }else{
           return false;
       }
    }

}
