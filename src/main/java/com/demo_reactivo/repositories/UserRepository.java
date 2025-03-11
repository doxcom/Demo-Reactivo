package com.demo_reactivo.repositories;

import com.demo_reactivo.models.Users;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<Users, Long> {

  //  Mono<Users> findById();
}
