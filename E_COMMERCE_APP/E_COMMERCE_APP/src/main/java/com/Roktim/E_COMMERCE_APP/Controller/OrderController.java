package com.Roktim.E_COMMERCE_APP.Controller;

import com.Roktim.E_COMMERCE_APP.Model.OrderRequest;
import com.Roktim.E_COMMERCE_APP.Service.OrderService;
import com.Roktim.E_COMMERCE_APP.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
//2.YE VO CLASS HAIN JISME OrderRequest SE SIGNAL AYEGA KI KAM KARNA HAIN - MATLAB ORDER PLACE KARNA HAIN.BAKI CONTROLLER CLASSES SE YEIN DATA LETA HAIN.
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place/{userId}")
    public OrderDTO placeOrder(@PathVariable Long userId, @RequestBody OrderRequest orderRequest){
        return orderService.placeOrder(userId,orderRequest.getProductQuantities(),orderRequest.getTotalAmount());
        //Yaha se Service - yaniki OrderService class ko call ho raha hain. Response miln ke bad return hota hain.
    }

    @GetMapping("/all-orders")
    public List<OrderDTO> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/user/{userId}")
    public List<OrderDTO> getOrderByUser(@PathVariable Long userId){
            return orderService.getOrderByUser(userId);
    }
}
