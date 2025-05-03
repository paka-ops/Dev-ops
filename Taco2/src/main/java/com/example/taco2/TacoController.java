package com.example.taco2;

import jakarta.validation.Valid;
import org.hibernate.validator.constraints.Mod10Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("order")
@RequestMapping("/taco")
public class TacoController {
    private final ITaco tacoRepository;
    @Autowired
    public TacoController(TacoRepository tacoRepository){
        this.tacoRepository = tacoRepository;
    }

    @ModelAttribute(name="order")
    public Order getOrder(){
        return new Order();
    }
    @ModelAttribute("Taco")
    public Taco getTaco(){
        return new Taco();
    }


    @PostMapping("/saveTaco")
    public String processTaco(@Valid Taco taco, Errors errors, Order order, Model model){
        if(errors.hasErrors()){
            return "designForm";
        }
        order.setTaco(taco);
        tacoRepository.saveTaco(taco);
        model.addAttribute("order",order);

        return "redirect:http://localhost:8080/taco/order/orderForm";
    }
}
