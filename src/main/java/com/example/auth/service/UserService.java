package com.example.auth.service;

import com.example.auth.model.User;
import com.example.auth.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    public boolean register(User user){
        User user1 = userRepo.findById(user.getUsername()).orElse(null);
        if(user1==null){
            userRepo.save(user);
            return true;
        }else {
            return false;
        }
    }
    public String login(User user){
        User user1 = userRepo.findById(user.getUsername()).orElse(null);
        if(user1==null){
            return "D";
        }else if(user1.getPassword().equals(user.getPassword())){
            return user1.getType();
        }else {
            return "E";
        }
    }
}
