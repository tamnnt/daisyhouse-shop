package LifeLeopard.TeamShop.Controllers;


import LifeLeopard.TeamShop.Models.*;
import LifeLeopard.TeamShop.Responsibility.AccountReps;
import LifeLeopard.TeamShop.Responsibility.BlogCmtReps;
import LifeLeopard.TeamShop.Responsibility.CustomerRepos;
import LifeLeopard.TeamShop.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path="/blog")
public class BlogController {
    @Autowired
    private CustomerRepos customerRepos;
    @Autowired
    private AccountReps accountReps;
    @Autowired
    private BlogService blogService;
    @Autowired
    CustomerService customerService;
    @Autowired
    AccountService accountService;
    @Autowired
    private EventService eventService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private BlogCmtService blogCmtService;
    @Autowired
    private BlogCmtReps blogCmtReps;

    @GetMapping("")
    public String listBlog(Model model, Principal principal){
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
        Contact contact = contactService.getContact();
        model.addAttribute("contact",contact);
        model.addAttribute("eventList",eventList);
        model.addAttribute("listBlog", blogService.findAllBlog());
        return "home/blog";
    }
    @GetMapping("/{id}")
    public String blogDetails(@PathVariable("id") int id, Model model, Principal principal){
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
        Contact contact = contactService.getContact();
        model.addAttribute("contact",contact);
        model.addAttribute("eventList",eventList);
        Optional<Blog>  blog = blogService.getBlogById(id);
        if(!blog.isPresent()){
            return "error";
        }
        List<BlogComment> blogComments = blogCmtService.findAllByblog(blog.get());
        model.addAttribute("comment",new BlogComment());
        model.addAttribute("blogComments",blogComments);
        model.addAttribute("blog", blog.get());
        return "home/blog-detail";
    }
    @PostMapping( value = "/comment/{id}",produces = "application/json")
    @ResponseBody
    public ResponseEntity<Boolean> createComment(@PathVariable("id") int id,@ModelAttribute("comment") BlogComment blogComment,Principal principal){
        Customers customer = new Customers();
        if(principal != null){
            String username = principal.getName().trim();
            customer =customerRepos.findByAccountId(accountReps.findByUsername(username).getAccountId());
            if(customer == null){
                return ResponseEntity.ok().body(false);
            }
        }
        Optional<Blog> blog = blogService.getBlogById(id);
        if(blog.isPresent()){
            blogComment.setBlog(blog.get());
            blogComment.setCustomers(customer);
            blogCmtService.save(blogComment);
            return ResponseEntity.ok().body(true);
        }else{
            return ResponseEntity.ok().body(false);
        }
    }

    @PostMapping( value = "/deleteCmt/{id}",produces = "application/json")
    @ResponseBody
    public ResponseEntity<Boolean> deteleComment(@PathVariable("id") int id){

        Optional<BlogComment> blogComment = blogCmtService.findbyId(id);

        if(blogComment.isPresent()){
            blogCmtService.delete(id);
            return ResponseEntity.ok().body(true);
        }
        return ResponseEntity.ok().body(false);
    }
}