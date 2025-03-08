package com.demo_reactivo.services;

import com.demo_reactivo.models.Users;
import com.demo_reactivo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

   @Autowired
   UserRepository userRepository;

   //Mono for single object/items 0-1
    //multiple strings Flux 0-N

    //get single object of type User
    public Mono<Users> getUserById(Long id){
        return userRepository.findById(id);
    }

    public Flux<Users> getUsers(){
        return userRepository.findAll();
    }

    public void addUser(Users users){
        userRepository.save(users).subscribe(); //subscribe ??..
    }

    public Mono<Users> updateUser(Users users){
        return userRepository.findById(users.getId())
                .switchIfEmpty(Mono.error(new Exception("User not found")))
                .map(olderUser ->{
                    if(users.getSurname()!=null) olderUser.setSurname(users.getSurname());
                    if(users.getUsername()!=null) olderUser.setUsername(users.getUsername());
                    if(users.getName() !=null) olderUser.setName(users.getName());
                    if(users.getEmail()!=null) olderUser.setEmail(users.getEmail());
                    return olderUser;

                })
                .flatMap(userRepository::save);  //save on db

    }

    public Mono<Void> deleteUser(Long id){
     return userRepository.deleteById(id)
             .switchIfEmpty(Mono.error(new Exception("User not found")));
    }

}
