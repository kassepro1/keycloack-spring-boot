package com.kp.web;


import com.kp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ProductContoller {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/products")
    public String index(Model model){
        model.addAttribute("products",productRepository.findAll());
        return "products";
    }
}
