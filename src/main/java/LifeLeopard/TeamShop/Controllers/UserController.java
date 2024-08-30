package LifeLeopard.TeamShop.Controllers;

import LifeLeopard.TeamShop.Models.*;
import LifeLeopard.TeamShop.Models.Event;
import LifeLeopard.TeamShop.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.awt.*;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    CustomerService customerService;
    @Autowired
    AccountService accountService;
    @Autowired
    private EventService eventService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private OrderService orderService;
    @GetMapping("")
    public String proFileUser(Model model,Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        String username = principal.getName().trim();
        Customers customer =customerService.getByAccountId(accountService.getUsername(username).getAccountId());
        List<Order> orderList = orderService.findAllByCustomer(customer);
        List<Event> eventList = eventService.getAllEventOn();
        Contact contact = contactService.getContact();
        model.addAttribute("orderList",orderList);
        model.addAttribute("customer",customer);
        model.addAttribute("contact",contact);
        model.addAttribute("eventList",eventList);
        return "home/user/profile-user";
    }
    @GetMapping("/edit")
    public String editProfileUser(Model model,Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        String username = principal.getName().trim();
        Customers customer =customerService.getByAccountId(accountService.getUsername(username).getAccountId());
        List<Event> eventList = eventService.getAllEventOn();
        Contact contact = contactService.getContact();
        model.addAttribute("contact",contact);
        model.addAttribute("eventList",eventList);
        model.addAttribute("customer",customer);
        return "home/user/edit-profile";
    }
    @PostMapping("/edit")
    public String editProfileUser(Model model, Principal principal,@ModelAttribute("customer") Customers customers, @RequestParam("password") String password){
        if(customerService.editProfile(customers,password,principal)){
            if(principal == null){
                return "redirect:/login";
            }
            String username = principal.getName().trim();
            Customers customer =customerService.getByAccountId(accountService.getUsername(username).getAccountId());
            List<Event> eventList = eventService.getAllEventOn();
            Contact contact = contactService.getContact();
            model.addAttribute("contact",contact);
            model.addAttribute("eventList",eventList);
            model.addAttribute("customer",customer);
            model.addAttribute("edit_profile_success",true);
            return "home/user/edit-profile";
        }else{
            if(principal == null){
                return "redirect:/login";
            }
            String username = principal.getName().trim();
            Customers customer =customerService.getByAccountId(accountService.getUsername(username).getAccountId());
            List<Event> eventList = eventService.getAllEventOn();
            Contact contact = contactService.getContact();
            model.addAttribute("contact",contact);
            model.addAttribute("eventList",eventList);
            model.addAttribute("customer",customer);
            model.addAttribute("edit_profile_fail",true);
            return "home/user/edit-profile";
        }
    }
    @PostMapping("/reset/password")
    public String resetPassword(Model model, Principal principal, @RequestParam("current_password") String currentPassword,
                                @RequestParam("new_password") String newPassword, @RequestParam("confirm_password") String confirmPassword){
        if(newPassword.equals(confirmPassword) && !newPassword.isEmpty()){
            if(accountService.changesPassword(currentPassword,newPassword,principal)){
                model.addAttribute("changes_pass_success",true);

                System.out.println("changes_pass_success");
            }else{
                model.addAttribute("changes_pass_fail",true);
                System.out.println("current_password_pass_fail");
            }
        }else{
            model.addAttribute("changes_pass_fail",true);
            System.out.println("confirm_pass_fail");
        }
        return "redirect:/user";
    }
    @GetMapping("/order/{id}")
    public String getDetailOrder(@PathVariable("id") int id,Model model,Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        String username = principal.getName().trim();
        Customers customer =customerService.getByAccountId(accountService.getUsername(username).getAccountId());
        Order order = orderService.findbyid(id);
        List<ProductOrder> productOrderList = orderService.findAllByOrder(orderService.findbyid(id));
        List<Event> eventList = eventService.getAllEventOn();
        Contact contact = contactService.getContact();

        model.addAttribute("id",id);
        model.addAttribute("total",order.getTotal());
        model.addAttribute("productOrderList",productOrderList);
        model.addAttribute("contact",contact);
        model.addAttribute("eventList",eventList);
        model.addAttribute("customer",customer);
        if(productOrderList.isEmpty()){
            return "redirect:/user";
        }
        return "home/user/user-order";
    }

}
