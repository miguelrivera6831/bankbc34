package com.bootcamp.msvcoperation.service.impl;

import com.bootcamp.msvcoperation.model.document.Operation;
import com.bootcamp.msvcoperation.model.mapper.OperationMapper;
import com.bootcamp.msvcoperation.repository.OperationRepository;
import com.bootcamp.msvcoperation.service.OperationService;
import com.bootcamp.msvcoperation.webclient.MsvcAccountWebClient;
import com.specification.api.dto.NewOperationDto;
import com.specification.api.dto.OperationAccountDto;
import com.specification.api.dto.OperationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Service
@Slf4j
public class OperationServiceImpl implements OperationService {

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private MsvcAccountWebClient accountWebClient;

    @Autowired
    private OperationMapper mapper;

    @Autowired
    private OperationBussinesServiceImpl operationBussinesServiceImpl;




    @Override
    public Flux<Operation> findAll() {
        return   operationRepository.findAll()
                .switchIfEmpty(Mono.error(
                        new Throwable("No hay operationes registrados")));



    }

    @Override
    public Mono<Operation> findById(String id) {
        return operationRepository.findById(id)
                .switchIfEmpty(Mono.error(new Throwable("Dni no encontrado")));
    }

    @Override
    public Mono<Void> delete(Operation operation) {
        log.info("OperationServiceImpl: delete");
        return operationRepository.delete(operation);
    }
    @Override
    public Mono<Operation> save(Operation operation) {
        return operationRepository.findAll()
                .filter(operationDB ->
                        operationDB.getId().equals(operation.getId())
                )
                .collectList()
                .flatMap(operations -> {
                    if (operations.size() > 0) {
                        return Mono.error(new Throwable("operation exist"));
                    }
                    return operationRepository.save(operation);
                });
    }

    @Override
    public Mono<Operation> saveOperation(Operation operation) {



        return    operationBussinesServiceImpl.saveOperation(operation);





//        return    accountWebClient.getAccountById(operation.getIdAccount())
//                .switchIfEmpty(Mono.error(new Throwable("Cuenta no encontrada")))
//                  .flatMap(op -> {
//
//                    switch ((String)operation.getType()) {
//                        case typeOne:
//                            return  accountWebClient.getAccountById(operation.getIdAccount())
//                                    .switchIfEmpty(Mono.error(new Throwable("Cuenta no encontrada")))
//
//                                    .flatMap(account ->
//                                            Mono.just(account.getProductList().stream().map(product -> {
//                                                if (product.getId() == operation.getIdProductOne()) {
//                                                    product.setBalance(product.getBalance() - operation.getBalanceOperation());
//                                                } else if  (product.getId() == operation.getIdProductTwo()) {
//                                                    product.setBalance(product.getBalance() + operation.getBalanceOperation());
//                                                }
//                                                return accountWebClient.updateAccount(mapper.toAccountDto(account));
//                                            }).collect(Collectors.toList()))
//
//                                    );
//                                    //.flatMap(operationDB->save(operation))
//                        case typeTwo:
//                            return  accountWebClient.getAccountById(operation.getIdAccount())
//                                    .switchIfEmpty(Mono.error(new Throwable("Cuenta no encontrada")))
//
//                                    .flatMap(account ->
//                                            Mono.just(account.getProductList().stream().map(product -> {
//                                                if (product.getId() == operation.getIdProductOne()) {
//                                                    product.setBalance(product.getBalance() - operation.getBalanceOperation());
//                                                } else if  (product.getId() == operation.getIdProductTwo()) {
//                                                    product.setBalance(product.getBalance() + operation.getBalanceOperation());
//                                                }
//                                                return accountWebClient.updateAccount(mapper.toAccountDto(account));
//                                            }).collect(Collectors.toList()))
//
//                                    );
//                                  //  .flatMap(operationDB->save(operation));
//
//                        case typeThree:
//                            return  accountWebClient.getAccountById(operation.getIdAccount())
//                                    .switchIfEmpty(Mono.error(new Throwable("Cuenta no encontrada")))
//
//                                    .flatMap(account ->
//                                            Mono.just(account.getProductList().stream().map(product -> {
//                                                if (product.getId() == operation.getIdProductOne()) {
//                                                    product.setBalance(product.getBalance() - operation.getBalanceOperation());
//                                                } else if  (product.getId() == operation.getIdProductTwo()) {
//                                                    product.setBalance(product.getBalance() + operation.getBalanceOperation());
//                                                }
//                                                return accountWebClient.updateAccount(mapper.toAccountDto(account));
//                                            }).collect(Collectors.toList()))
//
//                                    );
//                                   // .flatMap(operationDB->save(operation));
//                    }
//                    return save(operation);
//                  });



       // return save(operation);






    }


    @Override
    public Mono<Operation> update(Operation operation, String id) {

        return operationRepository.findById(id)
                .flatMap(operationDB -> {

                    return operationRepository.save(operationDB);
                });

    }
}
