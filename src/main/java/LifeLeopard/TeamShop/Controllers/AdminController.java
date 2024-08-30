package LifeLeopard.TeamShop.Controllers;

import LifeLeopard.TeamShop.Models.*;
import LifeLeopard.TeamShop.Responsibility.*;
import LifeLeopard.TeamShop.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.Multipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping(path = "/admin")
public class AdminController {
//    public static String UPLOAD_DIRECTORY = Paths.get("")
//            .toAbsolutePath()
//            .toString() + "/src/main/resources/static";
    @Autowired
    private EmployeeRepos employeeRepos;
    @Autowired
    private AccountReps accountReps;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SizeService sizeService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductPageReps productPageReps;
    @Autowired
    private ContactService contactService;
    @Autowired
    private EventService eventService;
    @Autowired
    private AboutService aboutService;
    @Autowired
    private AboutPageReps aboutPageReps;
    @Autowired
    private OrderService orderService;
    @Autowired
    private HomeSildeService homeSildeService;
    @Autowired
    private BlogService blogService;

    @GetMapping("")
    public String index(Model model, Principal principal){

        if(principal != null){
            String username = principal.getName().trim();
            Employee employee =employeeRepos.findByAccountId(accountReps.findByUsername(username).getAccountId());
            model.addAttribute("employee",employee);
        }
//        System.out.println(UPLOAD_DIRECTORY);
        return "admin/admin.index";
    }
    @GetMapping("/login")
    public String login(Principal principal){
        if(principal != null){
            return "redirect:/admin";
        }
        return "admin/login";
    }
    @GetMapping("/login-error")
    public String loginError(Model model){
        model.addAttribute("error",true);
        System.out.println("login_fail");
        return "admin/login";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/admin";
    }
    @GetMapping("/product")
    public String getAllProduct(Model model, Principal principal,@RequestParam(value = "page",defaultValue = "1") int page){

        if(principal != null){
            String username = principal.getName().trim();
            Employee employee =employeeRepos.findByAccountId(accountReps.findByUsername(username).getAccountId());
            model.addAttribute("employee",employee);
        }
        int pagesize =10;
        Pageable pageable = PageRequest.of(page-1,pagesize);
        int totalPages = productPageReps.findAll(pageable).getTotalPages();
        List<Product> productList = productPageReps.findAll(pageable).getContent();
        model.addAttribute("productList",productList);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("currentPage",page);

        return "admin/show-all-product";
    }
    @PostMapping("/product")
    public String findProduct(@RequestParam("keyword") String keyword, @RequestParam("search") int search, Model model){
        System.out.println(keyword);
        System.out.println(search);
        List<Product> productList = new ArrayList<>();
        try {
            if(search == 0){
                Product product =  productService.getById(Integer.parseInt(keyword));
                productList.add(product);
            }else{
                productList = productService.getAllByName(keyword);
            }
        }catch (Exception e){
            return "admin/admin-error";
        }

        model.addAttribute("productList",productList);
        System.out.println(productList.get(0).toString());
        return "admin/show-all-product";
//        return null;
    }
    @GetMapping("/product/create")
    public String createNewProduct(Model model, Principal principal){


        if(principal != null){
            String username = principal.getName().trim();
            Employee employee =employeeRepos.findByAccountId(accountReps.findByUsername(username).getAccountId());
            model.addAttribute("employee",employee);
        }
        model.addAttribute("product",new Product());
        model.addAttribute("categoryList",categoryService.getAllCategory());
        model.addAttribute("sizes",sizeService.getAllSize());
//        model.addAttribute("message",message);
        return "admin/create-product";
    }
    @PostMapping("/product/create")
    public String createProduct(Model model, RedirectAttributes redirectAttributes, Principal principal, @ModelAttribute("product") Product product,
                                @RequestParam("files") MultipartFile[] multipartFiles,
                                @RequestParam("details_quantity") int[] quantity, @RequestParam("details_status") int[] status, @RequestParam("details_price") double[] price) throws IOException {

        if(principal != null){
            String username = principal.getName().trim();
            Employee employee =employeeRepos.findByAccountId(accountReps.findByUsername(username).getAccountId());
            model.addAttribute("employee",employee);
        }
        List<Size> sizeList = sizeService.getAllSize();
        List<ProductSize> productSizeList = new ArrayList<>(sizeList.size());
        for(int i = 0; i<sizeList.size() ;i++){
            ProductSize productSize = new ProductSize();
            productSize.setSize(sizeList.get(i));
            productSize.setQuantity(quantity[i]);
            if(quantity[i] == 0){
                status[i] = 0;
            }
            productSize.setStatus(status[i]);
            productSize.setPrice(price[i]);
            productSizeList.add(productSize);
        }
        Product productRepos = productService.save(product,productSizeList);
        System.out.println(productRepos.toString());
        productService.saveImgProduct(productRepos,multipartFiles);

//        System.out.println(productSizes.get(0).toString());
        System.out.println("success");
        model.addAttribute("create_product_success",true);
        redirectAttributes.addFlashAttribute("message",
                true);
        redirectAttributes.addFlashAttribute("productid",productRepos.getProductId());
        return "redirect:/admin/product/create";
    }
    @GetMapping("/product/update/{id}")
    public String getProductUpdateById(Model model, Principal principal ,@PathVariable int id){
        if(principal != null){
            String username = principal.getName().trim();
            Employee employee =employeeRepos.findByAccountId(accountReps.findByUsername(username).getAccountId());
            model.addAttribute("employee",employee);
        }
        Product product = productService.getById(id);
//        System.out.println(product.getProductImagesList().size());
        model.addAttribute("product",product);
        model.addAttribute("categoryList",categoryService.getAllCategory());
        return "admin/update-product";
    }
    @PostMapping("/product/update/{id}")
    public String updateProduct(@PathVariable("id") int id,RedirectAttributes redirectAttributes, Principal principal,@ModelAttribute("product") Product productDetails,
                                @RequestParam("files") MultipartFile[] multipartFiles,
                                @RequestParam("details_quantity") int[] quantity,@RequestParam("details_status") int[] status,@RequestParam("details_price") double[] price) throws IOException {

        productService.updateProduct(id,productDetails,multipartFiles,quantity,status,price);
        redirectAttributes.addFlashAttribute("update_product_success",true);
        redirectAttributes.addFlashAttribute("update_product_id",id);
        return "redirect:/admin/product";
    }
    @GetMapping("/updatecontact")
    public String updateContact(Model model){
        model.addAttribute("contact",contactService.getContact());
        return "admin/update-contact";
    }
    @PostMapping("/updatecontact")
    public String updateContact(@ModelAttribute("contact") Contact contact,RedirectAttributes redirectAttributes){
        if(contactService.updateContact(contact)){
            redirectAttributes.addFlashAttribute("success",true);
        }else{
            redirectAttributes.addFlashAttribute("fail",true);
        }
        return "redirect:/admin/updatecontact";
    }

    @GetMapping("/about/create")
    public String CreateAboutInfoo(Model model,Principal principal){
        model.addAttribute("about",new About());
        return "admin/create-aboutinformation";
    }
    @PostMapping("/about/create")
    public String CreateAboutInfoo(@ModelAttribute("about") About about, Principal principal, RedirectAttributes redirectAttributes, @RequestParam("thumbnail")MultipartFile MultipartFile) throws IOException {
        Optional<About> about1 = aboutService.findById(about.getAboutId());
        if(about1.isPresent()){
            aboutService.updateAbout(about,MultipartFile);
            redirectAttributes.addFlashAttribute("update_aboutinfo_success",true);
            redirectAttributes.addFlashAttribute("update_aboutinfo_id",about1.get().getAboutId());
            return "redirect:/admin/about";
        }else{
            int id = aboutService.CreateAboutInfo(about, MultipartFile);
            redirectAttributes.addFlashAttribute("message",true);
            redirectAttributes.addFlashAttribute("aboutId",id);
            return "redirect:/admin/about/create";
        }
    }

    @GetMapping("/about")
    public String getAllAboutInfo(Model model, Principal principal,@RequestParam(value = "page",defaultValue = "1") int page){

        if(principal != null){
            String username = principal.getName().trim();
            Employee employee =employeeRepos.findByAccountId(accountReps.findByUsername(username).getAccountId());
            model.addAttribute("employee",employee);
        }
        int pagesize =10;
        Pageable pageable = PageRequest.of(page-1,pagesize);
        int totalPages = aboutPageReps.findAll(pageable).getTotalPages();
        List<About> aboutList = aboutPageReps.findAll(pageable).getContent();
        model.addAttribute("aboutList",aboutList);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("currentPage",page);

        return "admin/showallaboutinfo";
    }
    @GetMapping("/about/update/{id}")
    public String getAboutUpdateById(Model model, Principal principal ,@PathVariable int id){
        if(principal != null){
            String username = principal.getName().trim();
            Employee employee =employeeRepos.findByAccountId(accountReps.findByUsername(username).getAccountId());
            model.addAttribute("employee",employee);
        }
        About about = aboutService.getById(id);
        model.addAttribute("about",about);
        return "admin/create-aboutinformation";
    }
    @GetMapping("/order")
    public String getAllOrder(Model model,Principal principal){
        List<Order> orderList = orderService.getAllOrderProcessing();
        model.addAttribute("orderList",orderList);
        return "admin/show-all-order";
    }
    @GetMapping(value ="/order/processing/{id}",produces = "application/json")
    @ResponseBody
    public ResponseEntity<Boolean> updateOrderProcessing(@PathVariable("id") int id, Model model, Principal principal){
        Order order = orderService.findbyid(id);
        order.setStatus(1);
        orderService.Save(order);
        return ResponseEntity.ok().body(true);
    }
    @GetMapping( value = "/order/successing/{id}",produces = "application/json")
    @ResponseBody
    public ResponseEntity<Boolean> updateOrderSuccessing(@PathVariable("id") int id,Model model,Principal principal){
        Order order = orderService.findbyid(id);
        order.setStatus(2);
        orderService.Save(order);
        return ResponseEntity.ok().body(true);
    }
    @GetMapping("/order/{id}" )
    public String getOrderDetails(@PathVariable("id") int id,Model model,Principal principal){
        Order order = orderService.findbyid(id);
        List<ProductOrder> productOrderList = orderService.findAllByOrder(orderService.findbyid(id));
        model.addAttribute("productOrderList",productOrderList);
        model.addAttribute("total",order.getTotal());
        model.addAttribute("order",order);
        return "admin/order-details";
    }
    @GetMapping("/order/success")
    public String getAllOrderSuccess(Model model,Principal principal){
        List<Order> orderList = orderService.getAllOrderSuccess();
        model.addAttribute("orderList",orderList);
        return "admin/order-success";
    }
    @GetMapping("/category")
    public String getAllCategory(Model model,Principal principal){
        List<Category> categoryList = categoryService.getAllCategory();
        model.addAttribute("categoryList",categoryList);
        return "admin/show-all-category";
    }
    @GetMapping("/category/create")
    public String createCategory(Model model,Principal principal){
        model.addAttribute("category",new Category());
        return "admin/create-category";
    }
    @GetMapping("/category/update/{id}")
    public String edditCategory(@PathVariable("id") int id,Model model,Principal principal){
        Optional<Category> category = categoryService.getCategoryById(id);
        if(category.isPresent()){
            model.addAttribute("category",category.get());
            return "admin/create-category";

        }
        return "redirect:/admin/category";
    }
    @PostMapping("/category/create")
    public String updateCategory(Model model,Principal principal,@ModelAttribute("category") Category categoryDetails,RedirectAttributes redirectAttributes){
        Optional<Category> category = categoryService.getCategoryById(categoryDetails.getCategoryId());
        if(category.isPresent()){
            System.out.println(categoryDetails);
            category.get().setCategoryName(categoryDetails.getCategoryName());
            category.get().setStatus(categoryDetails.getStatus());
            categoryService.Save(category.get());
            redirectAttributes.addFlashAttribute("message",true);
            redirectAttributes.addFlashAttribute("id",category.get().getCategoryId());
            return "redirect:/admin/category";
        }else{
            categoryService.Save(categoryDetails);
            redirectAttributes.addFlashAttribute("message",true);
        }
        return "redirect:/admin/category/create";
    }
    @GetMapping("/slidehome")
    public String getAllslide(Model model,Principal principal){
        List<HomeSlide> homeSlideList = homeSildeService.getAllSlide();
        model.addAttribute("homeSlideList",homeSlideList);
        return "admin/slide";
    }
    @GetMapping("/slidehome/create")
    public String createSlide(Model model,Principal principal){
        model.addAttribute("slide",new HomeSlide());
        return "admin/create-slide";
    }
    @GetMapping("/slidehome/update/{id}")
    public String createSlide(@PathVariable("id") int id,Model model,Principal principal){
        Optional<HomeSlide> homeSlide= homeSildeService.getSlideHome(id);
        if(homeSlide.isPresent()){
            model.addAttribute("slide",homeSlide.get());
        }else{
            model.addAttribute("slide",new HomeSlide());
        }
        return "admin/create-slide";
    }
    @PostMapping("/slidehome/create")
    public String createSlide(Model model,Principal principal,@ModelAttribute("slide") HomeSlide homeSlideDetails,RedirectAttributes redirectAttributes,@RequestParam("thumbnail") MultipartFile multipartFile) throws IOException {
        Optional<HomeSlide> homeSlide = homeSildeService.getSlideHome(homeSlideDetails.getSlideID());
        if(homeSlide.isPresent()){
            homeSildeService.updateHoneSlide(homeSlideDetails,multipartFile);
            redirectAttributes.addFlashAttribute("message",true);
            redirectAttributes.addFlashAttribute("id",homeSlide.get().getSlideID());
            return "redirect:/admin/slidehome";
        }else{
            homeSildeService.ceateHomeSlide(homeSlideDetails,multipartFile);
            redirectAttributes.addFlashAttribute("message",true);
            return "admin/create-slide";
        }
    }
    @GetMapping("/event/create")
    public String createEvent(Model model,Principal principal){
        model.addAttribute("event",new Event());
        return "admin/create-event";
    }
    @PostMapping("/event/create")
    public String createEvent(@ModelAttribute("event") Event event, Principal principal, RedirectAttributes redirectAttributes, @RequestParam("thumbnail")MultipartFile MultipartFile) throws IOException {
        Optional<Event> event1 = eventService.findById(event.getEventId());
        if(event1.isPresent()){
            eventService.updateEvent(event,MultipartFile);
            redirectAttributes.addFlashAttribute("update_event_success", true);
            redirectAttributes.addFlashAttribute("update_event_id", event1.get().getEventId());
            return "redirect:/admin/event";
        }
        else {
            int id = eventService.createEvent(event, MultipartFile);
            redirectAttributes.addFlashAttribute("message", true);
            redirectAttributes.addFlashAttribute("eventId", id);
            return "redirect:/admin/event/create";
        }
    }

    @GetMapping("/event/update/{id}")
    public String getEventUpdateById(Model model, Principal principal ,@PathVariable int id){
        if(principal != null){
            String username = principal.getName().trim();
            Employee employee =employeeRepos.findByAccountId(accountReps.findByUsername(username).getAccountId());
            model.addAttribute("employee",employee);
        }
        Event event = eventService.getEventByID(id);
        model.addAttribute("event",event);
        return "admin/create-event";
    }
    @GetMapping("/event")
    public String getAllEvent(Model model, Principal principal,@RequestParam(value = "page",defaultValue = "1") int page){
        if(principal != null){
            String username = principal.getName().trim();
            Employee employee =employeeRepos.findByAccountId(accountReps.findByUsername(username).getAccountId());
            model.addAttribute("employee",employee);
        }
        int pagesize =10;
        Pageable pageable = PageRequest.of(page-1,pagesize);
        List<Event> eventList = eventService.getAllEvent();
        model.addAttribute("eventList",eventList);
        return "admin/show-all-event";
    }
    @GetMapping("/{id}")
    public String blogDetails(@PathVariable("id") int id, Model model, Principal principal){
        if(principal != null){
            String username = principal.getName().trim();
            Employee employee =employeeRepos.findByAccountId(accountReps.findByUsername(username).getAccountId());
            model.addAttribute("employee",employee);
        }
        Optional<Blog>  blog = blogService.findBlogById(id);
        List<Event> eventList = eventService.getAllEventOn();
        Contact contact = contactService.getContact();
        model.addAttribute("contact",contact);
        model.addAttribute("eventList",eventList);
        model.addAttribute("blog", blog.get());
        return "home/blog-detail";
    }

    @GetMapping("/blog")
    public String blogShow(Model model){
        model.addAttribute("blogList",blogService.getAllBlog());
        return "admin/blog-show";
    }

    @GetMapping("/blog/create")
    public  String blogCreate(Model model, Principal principal){
        model.addAttribute("blog",new Blog());
        return "admin/create-blog";
    }

    @PostMapping("/blog/create")
    public String CreateBlog(@ModelAttribute("blog") Blog blog, Principal principal, RedirectAttributes redirectAttributes, @RequestParam("thumbnail")MultipartFile MultipartFile) throws IOException {
        Optional<Blog> blog1 = blogService.findBlogById(blog.getBlogId());
        if(blog1.isPresent()){
            blogService.updateBlog(blog,MultipartFile);
            redirectAttributes.addFlashAttribute("message",true);
            redirectAttributes.addFlashAttribute("blogId",blog1.get().getBlogId());
            return "redirect:/admin/blog";
        }else{
            int id = blogService.CreateBlog(blog, MultipartFile);
            redirectAttributes.addFlashAttribute("message",true);
            redirectAttributes.addFlashAttribute("blogId",id);
        }

        return "redirect:/admin/blog/create";
    }
///
    @GetMapping("/blog/edit/{id}")
    public  String blogEdit(@PathVariable("id") int id,Model model, Principal principal){
        model.addAttribute("blog",blogService.getBlogById(id).get());
        return "admin/create-blog";
    }

}
