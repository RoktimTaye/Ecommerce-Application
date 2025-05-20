package com.Roktim.E_COMMERCE_APP.Controller;

import com.Roktim.E_COMMERCE_APP.Model.Product;
import com.Roktim.E_COMMERCE_APP.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public List<Product> getallProducts(){
        return productService.getallProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long Id){
        return productService.getProductById(Id);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
}
