package com.backend.backend_challeng13.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend_challeng13.Dao.UserDao;
import com.backend.backend_challeng13.Entity.User;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> getAlluser() {
        return (List<User>) userDao.findAll();
    }

    public User addUser(User user) {
        return userDao.save(user);
    }

    public String getSpecificuser(){
        return userDao.findUser();
    }
}
