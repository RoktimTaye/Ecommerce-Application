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

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private UserRepository userRepository; //User ko search karne ke liye DB main.
    @Autowired
    private ProductRepository productRepository; //Product ko search karne ke liye DB main.
    @Autowired
    private OrderRepository orderRepository; //Order ko save karne ke liye DB main.

    //Order place Function and the main functionality after all other functionalities.
    public OrderDTO placeOrder(Long userId, Map<Long, Integer> productQuantities, double totalAmount) {
        //Yaha se har entity ke sath deal karenge hum usinf DTO.

        //1.User ko find karna Db se.
         User user = userRepository.findById(userId) //agar User miljaye to
                    .orElseThrow(()->new RuntimeException("user not found")); //Agar user na mile to.

        //2.User milne ke bad order placing ka process start hoga.Yaha loop nahi lagaya kyuki sirf ek user hain multiple Items ke liye.
         Orders order = new Orders(); //Batana padega ki data order table main cahiye.
         order.setUser(user); //Order miln ke kiye pahle user seet kar diya ki konsa user order karega.
         order.setOrderDate(new Date());
         order.setStatus("Pending");
         order.setTotalAmount(totalAmount);

         //3.Order banane ke bad order ke andar items kya kya honge, unhain dalna hain.
        List<OrderItem> orderItems = new ArrayList<>(); //Har ek item jo ek single order ke andar hoga, fir OrderItemDTO main dalenge.
        List<OrderItemDTO> orderItemDTOS = new ArrayList<>(); //Har item ab OrderItemDTO Me ghusega one by one.

        //4.Ek ek karke product details ko lake dalna.
        for(Map.Entry<Long,Integer> entry:productQuantities.entrySet()){ //Loop isliye use kiya kyuki ek usr bar bar item add kar sakta hain.
           Product product = productRepository.findById(entry.getKey()) //Product key mil gayi Map se.
                   .orElseThrow(()-> new RuntimeException("Product not found")); //Agar product DB main available nahi hain to.

            //5.Product milne ke bad OrderItem set hoga har ek item ke liye DB main milne ke bad.
           OrderItem orderItem = new OrderItem(); //OrderItem set karne ko bol diya.
           orderItem.setOrder(order); //order set kar diya.
           orderItem.setProduct(product); //Order kee andar ka product set kar diya.
           orderItem.setQuantity(entry.getValue()); //Value se quantity mil gaya joki entry se aya or entry ke andar productQuantity hain iteration main joki Product class use karke data lata hain.
           orderItems.add(orderItem); //Ekbar order agaya to fir usko OrderItem main add kar diya.

           orderItemDTOS.add(new OrderItemDTO(product.getName(),product.getPrice(),entry.getValue()));
        }

        //5.OrderItems set karke save kar deenge.
        order.setOrderItems(orderItems); //set kar diya OrderItems
       Orders SaveOrder =  orderRepository.save(order); //Save kar diya OrderItems OrderRepository main Order ka use karke.

        //6.Jo jo order kiya hain sirf vo rturn karna hain user ko.
       return new OrderDTO(SaveOrder.getId(),SaveOrder.getTotalAmount()
               ,SaveOrder.getStatus(),SaveOrder.getOrderDate(),orderItemDTOS);
    }

    public List<OrderDTO> getAllOrders() {
      List<Orders> orders = orderRepository.findAllOrdersWithUsers();
      return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private OrderDTO convertToDTO(Orders orders) {
          List<OrderItemDTO> OrderItems = Orders.getOrderItems().stream()
                    .map(item-> new OrderItemDTO(
                            item.getProduct().getName(),
                            item.getProduct().getPrice(),
                            item.getQuantity())).collect(Collectors.toList());
          return new OrderDTO(
                  orders.getId(),
                  orders.getTotalAmount(),
                  orders.getStatus(),
                  orders.getOrderDate(),
                  orders.getUser()!=null ? orders.getUser().getName() : "unknown",
                  orders.getUser()!=null ? orders.getUser().getEmail() : "unknown",
                  OrderItems
          );
    }

    public List<OrderDTO> getOrderByUser(Long userId) {
        Optional<User> userOp = userRepository.findById(userId);

        if (userOp.isEmpty()){
            throw new RuntimeException("user not found");
        }
        User user = userOp.get();
        List<Orders> ordersList = orderRepository.finByUser(user);
        return ordersList.stream().map(this::convertToDTO).collect(Collectors.toList());

    }
}
