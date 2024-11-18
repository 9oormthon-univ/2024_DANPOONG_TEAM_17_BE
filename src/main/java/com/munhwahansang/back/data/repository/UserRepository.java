package com.munhwahansang.back.data.repository;


import com.munhwahansang.back.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {


    boolean existsByProvideId(Long providerId);

    User findByProvideId(Long providerId);

    User queryById(Integer id);

}
