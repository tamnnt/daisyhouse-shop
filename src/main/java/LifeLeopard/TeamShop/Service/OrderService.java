package LifeLeopard.TeamShop.Service;

import LifeLeopard.TeamShop.Models.*;
import LifeLeopard.TeamShop.Responsibility.OrderReps;
import LifeLeopard.TeamShop.Responsibility.ProductOrderReps;
import LifeLeopard.TeamShop.Responsibility.ProductReps;
import LifeLeopard.TeamShop.Responsibility.ProductSizeReps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private ProductOrderReps productOrderReps;
    @Autowired
    private OrderReps orderReps;
    @Autowired
    private ProductSizeReps productSizeReps;
    @Autowired
    private ProductReps productReps;
    public List<Order> getAllOrderProcessing(){
        Collection<Integer> collection = new ArrayList<>();
        collection.add(1);
        collection.add(0);
        return orderReps.findAllByStatusIn(collection);
    }
    public List<Order> getAllOrderSuccess(){
        Collection<Integer> collection = new ArrayList<>();
        collection.add(2);
        return orderReps.findAllByStatusIn(collection);
    }
    public List<Order> findAllByCustomer(Customers customers){
        return orderReps.findAllByCustomers(customers);
    }
    public Order findbyid(int id){
        return orderReps.getById(id);
    }
    public List<ProductOrder> findAllByOrder(Order order){
        return productOrderReps.findAllByOrder(order);
    }
    public void Save(Order order){
        orderReps.save(order);
    }
    public void saveOrder(Order order, List<ProductCart> productCartList){
        orderReps.save(order);
        List<ProductOrder> productOrderList = new ArrayList<>();
        for(ProductCart productCart : productCartList){
            ProductOrder productOrder = new ProductOrder();
            productOrder.setOrder(order);
            productOrder.setProductSize(productCart.getProductSize());
            productOrder.setPrice(productCart.getTotal());
            productOrder.setQuantity(productCart.getQuantity());
            productOrderList.add(productOrder);
            ProductSize productSize = productSizeReps.getById(productCart.getProductSize().getProductSizeId());
            Optional<Product> product = productReps.findById(productOrder.getProductSize().getProduct().getProductId());
            product.get().setQuantity(product.get().getQuantity() - productCart.getQuantity());
            if(product.get().getQuantity() < 1){
                product.get().setStatus(0);
            }
            productReps.save(product.get());
            productSize.setQuantity(productSize.getQuantity() - productCart.getQuantity());
            if(productSize.getQuantity() < 1){
                productSize.setQuantity(0);
            }
            productSizeReps.save(productSize);
        }
        productOrderReps.saveAll(productOrderList);
    }
}
