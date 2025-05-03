package com.example.taco2;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("/taco/ingredient")
public class IngredientController {
    private final IngredientRepository ingredientRepository;

    private List<Ingredient> findIngredientByType(Ingredient.Type type,List<Ingredient> ingredients){
        List<Ingredient> ingredientList = new ArrayList<Ingredient>();
        ingredients.forEach(ingredient -> {
            if(ingredient.getType().equals(type)){
                ingredientList.add(ingredient);
            }
        });

        return ingredientList;
    }

    @Autowired
    public IngredientController(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;

    }

    @GetMapping("/ingredients")
    public String getAllIngredient(Model model) {
        for (Ingredient.Type type : Ingredient.Type.values()) {
           model.addAttribute(type.toString().toLowerCase(),findIngredientByType(type, ingredientRepository.findAll())) ;

        }
        model.addAttribute("taco",new Taco());
        return "designForm";
    }


}
