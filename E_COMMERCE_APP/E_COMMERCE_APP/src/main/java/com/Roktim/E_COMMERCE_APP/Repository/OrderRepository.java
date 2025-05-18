package com.Roktim.E_COMMERCE_APP.Repository;

import com.Roktim.E_COMMERCE_APP.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders,Long> {
}
