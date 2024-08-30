package LifeLeopard.TeamShop.Controllers;

import LifeLeopard.TeamShop.Models.Contact;
import LifeLeopard.TeamShop.Models.Customers;
import LifeLeopard.TeamShop.Models.Event;
import LifeLeopard.TeamShop.Responsibility.AccountReps;
import LifeLeopard.TeamShop.Responsibility.CustomerRepos;
import LifeLeopard.TeamShop.Service.ContactService;
import LifeLeopard.TeamShop.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class HandleErrorController implements ErrorController {
    @Autowired
    CustomerRepos customerRepos;
    @Autowired
    AccountReps accountReps;
    @Autowired
    private EventService eventService;
    @Autowired
    private ContactService contactService;
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request , Principal principal, Model model) {
        if(principal != null){
            String username = principal.getName().trim();
            Customers customer =customerRepos.findByAccountId(accountReps.findByUsername(username).getAccountId());
            model.addAttribute("customer",customer);
        }
        List<Event> eventList = eventService.getAllEventOn();
        Contact contact = contactService.getContact();
        model.addAttribute("contact",contact);
        model.addAttribute("eventList",eventList);
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error";
            }
        }

        return "error";
    }

}
