package com.miniproject.haimp004.service;

import com.miniproject.haimp004.data.User;
import com.miniproject.haimp004.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> listAll() {
            return userRepository.findAll();
    }

    public void save(User user){
        userRepository.save(user);
    }

    public User get(Integer id){
        return  userRepository.findById(id).get();
    }

    public void delete(Integer id){
        userRepository.deleteById(id);
    }

    public User getUserByName(String name){
        return userRepository.findByName(name);
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Integer countUser(){
        return userRepository.countUser();
    }

    public Page<User> listAllPaging(int page, int size){
        return userRepository.findAll(PageRequest.of(page, size));
    }
}
