package com.Roktim.E_COMMERCE_APP.Repository;

import com.Roktim.E_COMMERCE_APP.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
}
