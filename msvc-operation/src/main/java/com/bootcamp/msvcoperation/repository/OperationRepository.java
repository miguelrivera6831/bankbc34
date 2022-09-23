package com.bootcamp.msvcoperation.repository;


import com.bootcamp.msvcoperation.model.document.Operation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface OperationRepository extends ReactiveMongoRepository<Operation, String> {

    //Mono<Operation> findById(String id);

}
