package LifeLeopard.TeamShop.Controllers;

import LifeLeopard.TeamShop.Models.*;
import LifeLeopard.TeamShop.Responsibility.AccountReps;
import LifeLeopard.TeamShop.Responsibility.CustomerRepos;
import LifeLeopard.TeamShop.Responsibility.ProductPageReps;
import LifeLeopard.TeamShop.Service.*;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(path = "/")
public class HomeController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    CustomerService customerService;
    @Autowired
    AccountService accountService;
    @Autowired
    ProductService productService;
    @Autowired
    private CustomerRepos customerRepos;
    @Autowired
    private AccountReps accountReps;
    @Autowired
    private ContactService contactService;
    @Autowired
    private AboutService aboutService;
    @Autowired
    private HomeSildeService homeSildeService;
    @Autowired
    private EventService eventService;
    @Autowired
    private ProductPageReps productPageReps;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public String index(Model model, Principal principal){
        if(principal != null){
            String username = principal.getName().trim();
            Customers customer =customerService.getByAccountId(accountService.getUsername(username).getAccountId());
            if(customer == null){
                return "redirect:/admin";
            }else{
                model.addAttribute("customer",customer);
            }

        }
        List<Event> eventList = eventService.getAllEventOn();
        List<HomeSlide> homeSlideList = homeSildeService.getAllHomeSlideOn();
        Pageable pageable = PageRequest.of(0,20);
        List<Product> productList = productPageReps.findAll(pageable).getContent();
        Contact contact = contactService.getContact();
        model.addAttribute("contact",contact);
        model.addAttribute("homeSlideList",homeSlideList);
        model.addAttribute("productList",productList);
        model.addAttribute("eventList",eventList);
        return "home/index";
    }
    @GetMapping("/user/login")
    public String login(Principal principal,Model model){
        List<Event> eventList = eventService.getAllEventOn();
        Contact contact = contactService.getContact();
        model.addAttribute("contact",contact);
        model.addAttribute("eventList",eventList);
        if(principal != null){
            return "redirect:/";
        }
        return "home/login";
    }
    @GetMapping("/user/login-error")
    public String loginError(Model model){
        List<Event> eventList = eventService.getAllEventOn();
        Contact contact = contactService.getContact();
        model.addAttribute("contact",contact);
        model.addAttribute("eventList",eventList);
        model.addAttribute("error",true);
        System.out.println("login_fail");
        return "home/login";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
    @GetMapping("/registration")
    public String registration(Model model,Principal principal){
        if(principal != null){
            return "redirect:/";
        }
        List<Event> eventList = eventService.getAllEventOn();
        Contact contact = contactService.getContact();
        model.addAttribute("contact",contact);
        model.addAttribute("eventList",eventList);
        model.addAttribute("customer",new Customers());
        model.addAttribute("account",new Accounts());
        return "home/registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("customer") Customers customersDetails, @ModelAttribute("account") Accounts accountDetails,Model model, HttpServletRequest request)
            throws UnsupportedEncodingException, MessagingException {
        List<Event> eventList = eventService.getAllEventOn();
        Contact contact = contactService.getContact();
        model.addAttribute("contact",contact);
        model.addAttribute("eventList",eventList);
        Boolean customers = customerRepos.existsByEmail(customersDetails.getEmail());
        if(customers){
            model.addAttribute("email_exists",true);
            return "home/registration";
        }

        String siteURL = request.getRequestURL().toString();
        if (customerService.createCustomer(customersDetails, accountDetails,siteURL)) {
            return "redirect:/registration/success";
        } else {
            model.addAttribute("exists",true);
        }

        return "home/registration";
    }
    @GetMapping("/registration/success")
    public String registerSuccess(Model model){
        List<Event> eventList = eventService.getAllEventOn();
        Contact contact = contactService.getContact();
        model.addAttribute("contact",contact);
        model.addAttribute("eventList",eventList);
        return "home/register_success";
    }

    @GetMapping("/registration/verify")
    public String verifyUser(@Param("code") String code,Model model) {
        List<Event> eventList = eventService.getAllEventOn();
        Contact contact = contactService.getContact();
        model.addAttribute("contact",contact);
        model.addAttribute("eventList",eventList);
        if (accountService.verifyUser(code)) {
            return "home/verify_success";
        } else {
            return "home/verify_fail";
        }
    }
    @GetMapping("/recovery")
    public String resetPassword(Model model,Principal principal){
        if(principal != null){
            return "redirect:/";
        }
        model.addAttribute("email", new String());
        List<Event> eventList = eventService.getAllEventOn();
        Contact contact = contactService.getContact();
        model.addAttribute("contact",contact);
        model.addAttribute("eventList",eventList);
        return "home/recovery";
    }
    @PostMapping("/recovery")
    public String resetPassword(@ModelAttribute("email") String Email,HttpServletRequest request, Model model) throws MessagingException, UnsupportedEncodingException {
//        System.out.println(Email);
        List<Event> eventList = eventService.getAllEventOn();
        model.addAttribute("eventList",eventList);
        boolean exists = customerRepos.existsByEmail(Email);
        if(exists){
//            System.out.println(Email);
            String siteURL = request.getRequestURL().toString();
            Customers customers = customerRepos.findByEmail(Email);
            Accounts accounts = accountReps.getById(customers.getAccountId());
            String randomCode = RandomString.make(64);
            accounts.setResetPassCode(randomCode);
            accountReps.save(accounts);
            customerService.sendResetPassWord(customers,siteURL);
            return "redirect:/recovery/success";
        }else{
            model.addAttribute("notfound",true);
        }
        return "home/recovery";
    }
    @GetMapping("/recovery/success")
    public String resetPasswordSuccess(Model model){
        List<Event> eventList = eventService.getAllEventOn();
        Contact contact = contactService.getContact();
        model.addAttribute("contact",contact);
        model.addAttribute("eventList",eventList);
        return "home/recovery_success";
    }
    @GetMapping("/recovery/verify")
    public String verifyPassword(@Param("code") String code,Model model) {
        boolean exists = accountReps.existsByResetPassCode(code);
        if(exists){
            model.addAttribute("password1",new String());
            model.addAttribute("password2",new String());
            model.addAttribute("code","/recovery/verify?code="+code);
            System.out.println(code);
            return "home/verify_password";
        }
        List<Event> eventList = eventService.getAllEventOn();
        Contact contact = contactService.getContact();
        model.addAttribute("contact",contact);
        model.addAttribute("eventList",eventList);
        return "error";

    }
    @PostMapping("/recovery/verify")
    public String verifyPassword(@Param("code") String code,@ModelAttribute("password1") String password1, @ModelAttribute("password2") String password2,Model model) {
        System.out.println(password1);
        System.out.println(password2);
        List<Event> eventList = eventService.getAllEventOn();
        Contact contact = contactService.getContact();
        model.addAttribute("contact",contact);
        model.addAttribute("eventList",eventList);
        if(password1.contentEquals(password2) && !password1.isEmpty() && !password2.isEmpty()){
//            System.out.println("success");
            System.out.println(code);
            if(accountService.verifyPassword(code, password1)){
//                System.out.println("success_verify");
                return "home/verify_password_success";
            }else{
                System.out.println("fail_verify");
                return "home/verify_fail";
            }
        }else{
            System.out.println("fail_same");
            model.addAttribute("error",true);
        }
        return "home/verify_password";

    }
    @GetMapping("/event/{id}")
    public String eventShop(@PathVariable("id") int id,Model model){
        List<Event> eventList = eventService.getAllEventOn();
        model.addAttribute("eventList",eventList);
        Event event = eventService.getEventByID(id);
        String listProductId = event.getListProductId();
        List<Product> productList = productService.getProductByEvent(listProductId);
        List<Category> categoryList = categoryService.getAllCategoryOn();
        Contact contact = contactService.getContact();
        model.addAttribute("contact",contact);
        model.addAttribute("productList",productList);
        model.addAttribute("eventName",event.getEventName());
        model.addAttribute("categoryList",categoryList);
        return "home/product";
    }
    @GetMapping("/{url}")
    public String urldef(Model model,Principal principal,@PathVariable("url") String url){
        if(principal != null){
            String username = principal.getName().trim();
            Customers customer =customerService.getByAccountId(accountService.getUsername(username).getAccountId());
            if(customer == null){
                return "redirect:/admin";
            }else{
                model.addAttribute("customer",customer);
            }

        }
        List<Event> eventList = eventService.getAllEventOn();
        List<About> aboutList = aboutService.getAllAboutOn();
        Contact contact = contactService.getContact();
        model.addAttribute("contact",contact);
        model.addAttribute("eventList",eventList);
        model.addAttribute("aboutList",aboutList);
        System.out.println(url);
        return "home/" + url;
    }
}
