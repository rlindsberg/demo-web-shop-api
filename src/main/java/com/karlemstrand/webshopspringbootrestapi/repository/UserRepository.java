package com.karlemstrand.webshopspringbootrestapi.repository;

import com.karlemstrand.webshopspringbootrestapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
