package com.backend.backend_challeng13.Dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.backend.backend_challeng13.Entity.User;

public interface UserDao extends CrudRepository<User, Long> {
    public static final String FIND_USER = "SELECT nome, prenome, cin, date_naissance FROM user";

    @Query(value = FIND_USER, nativeQuery = true)
    public String findUser();
}
