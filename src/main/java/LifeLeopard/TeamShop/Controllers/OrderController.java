package LifeLeopard.TeamShop.Controllers;

import LifeLeopard.TeamShop.Models.*;
import LifeLeopard.TeamShop.Responsibility.ProductSizeReps;
import LifeLeopard.TeamShop.Service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping(path = "order")
public class OrderController {
    @Autowired
    private EventService eventService;
    @Autowired
    private ContactService contactService;
    @Autowired
    CustomerService customerService;
    @Autowired
    AccountService accountService;
    @Autowired
    ProductSizeReps productSizeReps;
    @Autowired
    ProductService productService;
    @Autowired
    OrderService orderService;

    @GetMapping("/cart")
    public String getCartV2(Model model, Principal principal, HttpServletRequest request) throws JsonProcessingException {
        if (principal != null) {
            String username = principal.getName().trim();
            Customers customer = customerService.getByAccountId(accountService.getUsername(username).getAccountId());
            model.addAttribute("customer", customer);
        }
        double totalCart = 0;
        String valueDetails = "";
        List<ProductCart> productCartList = new ArrayList<>();
        Map<String, List<Map<String, Map<String, Integer>>>> cartDetails = new HashMap<>();
        List<Map<String, Map<String, Integer>>> dataCookie = new ArrayList<>();
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals("cart")) {
                valueDetails = c.getValue();
                valueDetails = valueDetails.replace('%', '"');
                valueDetails = valueDetails.replace('_', ',');
                valueDetails = valueDetails.replace('-', '\\');
                cartDetails = new ObjectMapper().readValue(valueDetails, Map.class);
                dataCookie = cartDetails.get("data");
            }
        }
        for (Map<String, Map<String, Integer>> data : dataCookie) {
            for (Map.Entry<String, Map<String, Integer>> mapEntry : data.entrySet()) {
                ProductSize productSize = new ProductSize();
                Product productDetails = new Product();
                int quantity = 0;
                for (Map.Entry<String, Integer> product : mapEntry.getValue().entrySet()) {
                    if(product.getKey().equals("productId")){
                        productDetails = productService.getById(product.getValue());
                    }
                    if (product.getKey().equals("itemId")) {
                        productSize = productSizeReps.getById(product.getValue());
                    }
                    if(product.getKey().equals("quantity")){
                        quantity = product.getValue();
                    }

                }
                totalCart = totalCart + productSize.getPrice() * quantity;
                ProductCart productCart = new ProductCart(productDetails,productSize, quantity, productSize.getPrice() * quantity);
                productCartList.add(productCart);
            }
        }

        List<Event> eventList = eventService.getAllEventOn();
        Contact contact = contactService.getContact();
        model.addAttribute("productCartList", productCartList);
        model.addAttribute("orderInformation", new Order());
        model.addAttribute("contact", contact);
        model.addAttribute("eventList", eventList);
        model.addAttribute("totalCart", totalCart);
        return "home/cartv2";
    }

    @PostMapping(value = "/addtocart", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Boolean> addToCarV2(@RequestParam("productId") int productId,@RequestParam("productSizeId") int itemId, @RequestParam("numProduct") int numProduct, HttpServletResponse response, HttpServletRequest request) throws JsonProcessingException {
        String valueDetails = "";
        Map<String, Integer> dataDetails = new HashMap<>();
        Map<String, Map<String, Integer>> mapDetails = new HashMap<>();
        Map<String, List<Map<String, Map<String, Integer>>>> cartDetails = new HashMap<>();
        List<Map<String, Map<String, Integer>>> dataCookie = new ArrayList<>();
        Cookie[] cookies = request.getCookies();
        Boolean check = true;
        for (Cookie c : cookies) {
            if (c.getName().equals("cart")) {
                valueDetails = c.getValue();
                valueDetails = valueDetails.replace('%', '"');
                valueDetails = valueDetails.replace('_', ',');
                valueDetails = valueDetails.replace('-', '\\');
                cartDetails = new ObjectMapper().readValue(valueDetails, Map.class);
                dataCookie = cartDetails.get("data");
                System.out.println(cartDetails);
            }
        }
        dataDetails.put("productId",productId);
        dataDetails.put("itemId", itemId);
        dataDetails.put("quantity", numProduct);
        mapDetails.put(String.valueOf(itemId), dataDetails);
        for (Map<String, Map<String, Integer>> m : dataCookie) {
            for (Map.Entry<String, Map<String, Integer>> mapEntry : m.entrySet()) {
                if (mapEntry.getKey().equals(String.valueOf(itemId))) {
                    System.out.println(m.get(mapEntry.getKey()).get("quantity"));
                    System.out.println(numProduct);
                    int quantity = numProduct + m.get(mapEntry.getKey()).get("quantity");
                    System.out.println(quantity);
                    m.get(mapEntry.getKey()).put("quantity", quantity);
                    System.out.println(m.get(mapEntry.getKey()).get("quantity"));
                    check = false;
                    break;
                }
            }
            if (!check) {
                break;
            }
        }
        if (dataCookie.size() < 1) {
            dataCookie.add(mapDetails);
        } else {
            if (check) {
                dataCookie.add(mapDetails);
            }
        }
        cartDetails.put("data", dataCookie);
        System.out.println(cartDetails);
        valueDetails = new ObjectMapper().writeValueAsString(cartDetails);
        System.out.println(valueDetails);
        valueDetails = valueDetails.replace('"', '%');
        valueDetails = valueDetails.replace(',', '_');
        valueDetails = valueDetails.replace('\\', '-');
        Cookie cookie = new Cookie("cart", valueDetails);
        cookie.setMaxAge(86400);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.ok().body(true);
    }

    @PostMapping(value = "/updatecart", produces = "application/json")
    public ResponseEntity<Boolean> updateCartV2(@Param("id") String id,
                                                HttpServletResponse response, HttpServletRequest request) throws JsonProcessingException {
        String valueDetails = "";
        Map<String, List<Map<String, Map<String, Integer>>>> cartDetails = new HashMap<>();
        List<Map<String, Map<String, Integer>>> dataCookie = new ArrayList<>();
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals("cart")) {
                valueDetails = c.getValue();
                valueDetails = valueDetails.replace('%', '"');
                valueDetails = valueDetails.replace('_', ',');
                valueDetails = valueDetails.replace('-', '\\');
                cartDetails = new ObjectMapper().readValue(valueDetails, Map.class);
                dataCookie = cartDetails.get("data");
                System.out.println(cartDetails);
            }
        }
        int index = 0;
        boolean check = false;
        for (Map<String, Map<String, Integer>> data : dataCookie) {
            for (Map.Entry<String, Map<String, Integer>> mapEntry : data.entrySet()) {
                if(mapEntry.getKey().equals(id)){
                    check = true;
                    break;
                }
                index++;
            }
            if(check){
                break;
            }
        }
        dataCookie.remove(index);
        System.out.println(dataCookie);
        cartDetails.put("data",dataCookie);
        valueDetails = new ObjectMapper().writeValueAsString(cartDetails);
        System.out.println(valueDetails);
        valueDetails = valueDetails.replace('"', '%');
        valueDetails = valueDetails.replace(',', '_');
        valueDetails = valueDetails.replace('\\', '-');
        Cookie cookie = new Cookie("cart", valueDetails);
        cookie.setMaxAge(86400);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.ok().body(true);
    }
    @PostMapping(value = "/uơdatequantity",produces = "application/json")
    public ResponseEntity<Boolean> updateQuantityV2(@Param("id") String id,@Param("quantity") String quantity,
                                                    HttpServletResponse response, HttpServletRequest request) throws JsonProcessingException{
        String valueDetails = "";
        Map<String, List<Map<String, Map<String, Integer>>>> cartDetails = new HashMap<>();
        List<Map<String, Map<String, Integer>>> dataCookie = new ArrayList<>();
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals("cart")) {
                valueDetails = c.getValue();
                valueDetails = valueDetails.replace('%', '"');
                valueDetails = valueDetails.replace('_', ',');
                valueDetails = valueDetails.replace('-', '\\');
                cartDetails = new ObjectMapper().readValue(valueDetails, Map.class);
                dataCookie = cartDetails.get("data");
                System.out.println(cartDetails);
            }
        }
        boolean check = false;
        for (Map<String, Map<String, Integer>> data : dataCookie) {
            for (Map.Entry<String, Map<String, Integer>> mapEntry : data.entrySet()) {
                if(mapEntry.getKey().equals(id)){
                    mapEntry.getValue().put("quantity",Integer.valueOf(quantity));
                    check = true;
                    break;
                }
            }
            if(check){
                break;
            }
        }
        System.out.println(dataCookie);
        cartDetails.put("data",dataCookie);
        valueDetails = new ObjectMapper().writeValueAsString(cartDetails);
        System.out.println(valueDetails);
        valueDetails = valueDetails.replace('"', '%');
        valueDetails = valueDetails.replace(',', '_');
        valueDetails = valueDetails.replace('\\', '-');
        Cookie cookie = new Cookie("cart", valueDetails);
        cookie.setMaxAge(86400);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.ok().body(true);
    }
    @PostMapping("/buynow")
    public String buyNowV2(@ModelAttribute("orderInformation") Order orderDetails,  Model model, HttpServletRequest request, HttpServletResponse response, Principal principal, RedirectAttributes redirectAttributes) throws JsonProcessingException {
        Customers customer;
        if (principal != null) {
            String username = principal.getName().trim();
            customer = customerService.getByAccountId(accountService.getUsername(username).getAccountId());
            model.addAttribute("customer", customer);
        } else {
            return "redirect:/user/login";
        }
        double totalCart = 0;
        String valueDetails = "";
        List<ProductCart> productCartList = new ArrayList<>();
        Map<String, List<Map<String, Map<String, Integer>>>> cartDetails = new HashMap<>();
        List<Map<String, Map<String, Integer>>> dataCookie = new ArrayList<>();
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals("cart")) {
                valueDetails = c.getValue();
                valueDetails = valueDetails.replace('%', '"');
                valueDetails = valueDetails.replace('_', ',');
                valueDetails = valueDetails.replace('-', '\\');
                cartDetails = new ObjectMapper().readValue(valueDetails, Map.class);
                dataCookie = cartDetails.get("data");
            }
        }
        for (Map<String, Map<String, Integer>> data : dataCookie) {
            for (Map.Entry<String, Map<String, Integer>> mapEntry : data.entrySet()) {
                ProductSize productSize = new ProductSize();

                Product productDetails = new Product();
                int quantity = 0;
                for (Map.Entry<String, Integer> product : mapEntry.getValue().entrySet()) {
                    if(product.getKey().equals("productId")){
                        productDetails = productService.getById(product.getValue());
                    }else{
                        if (product.getKey().equals("itemId")) {
                            productSize = productSizeReps.getById(product.getValue());
                        } else {
                            System.out.println(product.getKey()+" "+ product.getValue());
                            quantity = product.getValue();
                            if(productSize.getQuantity() < quantity){
                                redirectAttributes.addFlashAttribute("fail",productSize);
                                return "redirect:/order/cart";
                            }
                        }
                    }
                }
                totalCart = totalCart + productSize.getPrice() * quantity;
                ProductCart productCart = new ProductCart(productDetails,productSize, quantity, productSize.getPrice() * quantity);
                System.out.println(productCart);
                productCartList.add(productCart);
            }
        }
        if (orderDetails.getFirstName().isEmpty()) {
            orderDetails.setFirstName(customer.getFirstName());
        }
        if (orderDetails.getLastName().isEmpty()) {
            orderDetails.setLastName(customer.getLastName());
        }
        if (orderDetails.getAddress().isEmpty()) {
            orderDetails.setAddress(customer.getAddress());
        }
        if (orderDetails.getPhoneNumber().isEmpty()) {
            orderDetails.setPhoneNumber(customer.getPhoneNumber());
        }
        orderDetails.setTotal(totalCart);
        orderDetails.setCustomers(customer);
        orderService.saveOrder(orderDetails,productCartList);
        Cookie cookie = new Cookie("cart", null);
        cookie.setMaxAge(0);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        redirectAttributes.addFlashAttribute("buy_success", true);
        return "redirect:/order/cart";
    }

    @GetMapping(value = "/price",produces = "application/json")
    @ResponseBody
    public double getPrice(@Param("size") String size){
        int id = Integer.parseInt(size);
        return productSizeReps.findById(id).get().getPrice();
    }
}