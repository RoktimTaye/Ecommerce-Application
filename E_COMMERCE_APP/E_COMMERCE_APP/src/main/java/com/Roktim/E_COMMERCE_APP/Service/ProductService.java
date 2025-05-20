package com.Roktim.E_COMMERCE_APP.Service;

import com.Roktim.E_COMMERCE_APP.Model.Product;
import com.Roktim.E_COMMERCE_APP.Repository.ProductRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getallProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
      return productRepository.findById(id).orElse(null);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
