package LifeLeopard.TeamShop.Controllers;

import LifeLeopard.TeamShop.Models.*;
import LifeLeopard.TeamShop.Responsibility.AccountReps;
import LifeLeopard.TeamShop.Responsibility.CustomerRepos;
import LifeLeopard.TeamShop.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping(path = "/product")
public class ProductController {
    @Autowired
    private CustomerRepos customerRepos;
    @Autowired
    private AccountReps accountReps;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private EventService eventService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private ProductCommentService productCommentService;


    @GetMapping("")
    public String getProduct(@Param("keyword") String keyword, Principal principal, Model model){
        if(principal != null){
            String username = principal.getName().trim();
            Customers customer =customerRepos.findByAccountId(accountReps.findByUsername(username).getAccountId());
            if(customer == null){
                return "redirect:/admin";
            }
            model.addAttribute("customer",customer);
        }
        List<Category> categoryList = categoryService.getAllCategoryOn();
        List<Product> productList = new ArrayList<>();
        if(keyword != null){
            productList = productService.getAllByName(keyword);
        }else{
            productList = productService.getAllProduct();
        }
        List<Event> eventList = eventService.getAllEventOn();
        Contact contact = contactService.getContact();
        model.addAttribute("contact",contact);
        model.addAttribute("eventList",eventList);
        model.addAttribute("productList",productList);
        model.addAttribute("categoryList",categoryList);
        return "home/product";
    }
    @GetMapping("/{id}")
    public String getAllProduct(@PathVariable int id,Model model, Principal principal){
        if(principal != null){
            String username = principal.getName().trim();
            Customers customer =customerRepos.findByAccountId(accountReps.findByUsername(username).getAccountId());
            if(customer == null){
                return "redirect:/admin";
            }
            model.addAttribute("customer",customer);
        }
        List<Event> eventList = eventService.getAllEventOn();
        Contact contact = contactService.getContact();
        model.addAttribute("contact",contact);
        model.addAttribute("eventList",eventList);
        Product product = productService.getById(id);
        if(product == null){
            return "error";
        }
        model.addAttribute("listComment",productCommentService.getAllCmtByProduct(product));
        model.addAttribute("commentUser",new ProductComment());
        List<Product> productListOther = productService.getOther();
        model.addAttribute("product",product);
        model.addAttribute("productListOther",productListOther);
        return "home/product-detail";
    }
    @PostMapping( value = "/comment/{id}" ,produces = "application/json")
    @ResponseBody
    public ResponseEntity<Boolean> createComment(@PathVariable("id") int id,Principal principal,@ModelAttribute("commentUser") ProductComment productComment){
        Optional<Product> product = productService.findById(id);
        Customers customer = new Customers();
        if(principal != null){
            String username = principal.getName().trim();
            customer =customerRepos.findByAccountId(accountReps.findByUsername(username).getAccountId());
            if(customer == null){
                return ResponseEntity.ok().body(false);
            }
        }
        if(product.isPresent()){
            productComment.setProduct(product.get());
            productComment.setCustomers(customer);
            productComment.setStatus(1);
            productCommentService.Save(productComment);
            return ResponseEntity.ok().body(true);
        }else{
            return ResponseEntity.ok().body(false);
        }
    }
    @GetMapping("/test")
    public String test(){
        return "home/product-detail";
    }

}
