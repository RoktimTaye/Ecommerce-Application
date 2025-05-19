package com.Roktim.E_COMMERCE_APP.Repository;

import com.Roktim.E_COMMERCE_APP.Model.Orders;
import com.Roktim.E_COMMERCE_APP.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders,Long> {

    @Query("Select * from Orders o Join FETCH o,user")
    List<Orders> findAllOrdersWithUsers();

    List<Orders> finByUser(User user);
}
