package com.example.repository;

import com.example.entity.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Igor on 4/1/2017.
 */
@Repository
public class UserRepository {

    private static String PATHNAME = "users.json";
    private List<User> users;

    public UserRepository() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        users = mapper.readValue(new File(PATHNAME), new TypeReference<List<User>>(){});
    }

    public List<User> findAll(){
        return users;
    }

    public User findUserByUsername(String username){
        for(User user:findAll()){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return new User();
    }
}
