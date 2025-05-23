package com.Roktim.E_COMMERCE_APP.Repository;

import com.Roktim.E_COMMERCE_APP.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
