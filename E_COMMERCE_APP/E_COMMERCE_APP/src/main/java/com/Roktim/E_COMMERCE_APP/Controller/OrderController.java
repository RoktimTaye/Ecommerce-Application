package com.Roktim.E_COMMERCE_APP.Controller;

import com.Roktim.E_COMMERCE_APP.Model.OrderRequest;
import com.Roktim.E_COMMERCE_APP.Service.OrderService;
import com.Roktim.E_COMMERCE_APP.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place/{userId}")
    public OrderDTO placeOrder(@PathVariable Long userId, @RequestBody OrderRequest orderRequest){
        return orderService.placeOrder(userId,orderRequest.getProductQuantities(),orderRequest.getTotalAmount());
    }
}
