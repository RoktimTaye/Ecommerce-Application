package com.Roktim.E_COMMERCE_APP.Service;

import com.Roktim.E_COMMERCE_APP.Model.OrderItem;
import com.Roktim.E_COMMERCE_APP.Model.Orders;
import com.Roktim.E_COMMERCE_APP.Model.Product;
import com.Roktim.E_COMMERCE_APP.Model.User;
import com.Roktim.E_COMMERCE_APP.Repository.OrderRepository;
import com.Roktim.E_COMMERCE_APP.Repository.ProductRepository;
import com.Roktim.E_COMMERCE_APP.Repository.UserRepository;
import com.Roktim.E_COMMERCE_APP.dto.OrderDTO;
import com.Roktim.E_COMMERCE_APP.dto.OrderItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    public OrderDTO placeOrder(Long userId, Map<Long, Integer> productQuantities, double totalAmount) {
         User user = userRepository.findById(userId)
                    .orElseThrow(()->new RuntimeException("user not found"));

         Orders order = new Orders();
         order.setUser(user);
         order.setOrderDate(new Date());
         order.setStatus("Pending");
         order.setTotalAmount(totalAmount);

        List<OrderItem> orderItems = new ArrayList<>();
        List<OrderItemDTO> orderItemDTOS = new ArrayList<>();

        for(Map.Entry<Long,Integer> entry:productQuantities.entrySet()){
           Product product =   productRepository.findById(entry.getKey())
                   .orElseThrow(()-> new RuntimeException("Product not found"));

           OrderItem orderItem = new OrderItem();
           orderItem.setOrder(order);
           orderItem.setProduct(product);
           orderItem.setQuantity(entry.getValue());
           orderItems.add(orderItem);

           orderItemDTOS.add(new OrderItemDTO(product.getName(),product.getPrice(),entry.getValue()));
        }

        order.setOrderItems(orderItems);
       Orders SaveOrder =  orderRepository.save(order);

       return new OrderDTO(SaveOrder.getId(),SaveOrder.getTotalAmount()
               ,SaveOrder.getStatus(),SaveOrder.getOrderDate(),orderItemDTOS);
    }
}
