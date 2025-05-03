package com.example.taco2;

import jakarta.jws.WebParam;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("taco")
@RequestMapping("/taco/order")
public class OrderController {
    private IOrder orderRepository;
    @Autowired
    public OrderController(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }
    @GetMapping("/orderForm")
    public String getOrderForm(Model model){
        model.addAttribute("order",new Order());
        return "orderForm";
    }
    @PostMapping("/orderForm")
    public String processOrder(@Valid Order order, Errors errors){
        if(errors.hasErrors()){
            return "orderForm";
        }
        orderRepository.saveOrder(order);
        return "viewCommand";

    }

}
