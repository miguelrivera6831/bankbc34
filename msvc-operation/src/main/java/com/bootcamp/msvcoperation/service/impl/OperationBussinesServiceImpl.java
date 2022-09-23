package com.bootcamp.msvcoperation.service.impl;

import com.bootcamp.msvcoperation.model.document.Operation;
import com.bootcamp.msvcoperation.model.mapper.OperationMapper;
import com.bootcamp.msvcoperation.repository.OperationRepository;
import com.bootcamp.msvcoperation.webclient.MsvcAccountWebClient;
import com.specification.api.dto.AccountDto;
import com.specification.api.dto.BalanceDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Service
@Slf4j
public class OperationBussinesServiceImpl {



    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private MsvcAccountWebClient accountWebClient;

    @Autowired
    private OperationMapper mapper;


    @Value("${spring.typeOne}")
    public String typeOne;

    @Value("${spring.typeTwo}")
    private String typeTwo;

    @Value("${spring.typeThree}")
    private String typeThree;

    @Value("${spring.typeFour}")
    private String typeFour;


    public Mono<Operation> saveOperation( Operation operation) {

        Mono<Operation> result = new Mono<Operation>() {
            @Override
            public void subscribe(CoreSubscriber<? super Operation> actual) {

            }
        };

       // Mono<Operation> OperationDB =


            if(operation.getType().equals(typeOne)){
                result=  accountWebClient.getAccountById(operation.getIdAccount())
                        .switchIfEmpty(Mono.error(new Throwable("Cuenta no encontrada")))
                        .flatMap(account ->
                                Mono.just(account.getProductList().stream().map(product -> {
                                    log.info(" operation"+operation.toString());
                                    if (product.getId().equals(operation.getIdProductOne())) {
                                        product.setBalance(product.getBalance() - operation.getBalanceOperation());
                                    } else if  (product.getId().equals(operation.getIdProductTwo())) {
                                        product.setBalance(product.getBalance() + operation.getBalanceOperation());
                                    }
                                      BalanceDto balance = new BalanceDto();
                                    balance.setBalance(product.getBalance());

                                    return accountWebClient.updateAccount(account.getId(),product.getId(), Mono.just(balance));
                                }).collect(Collectors.toList()))

                        )
                        .flatMap(operationDB->operationRepository.save(operation));
            }
            else if(operation.getType().equals(typeTwo)){
                result=   accountWebClient.getAccountById(operation.getIdAccount())
                    .switchIfEmpty(Mono.error(new Throwable("Cuenta no encontrada")))
                    .flatMap(account ->
                            Mono.just(account.getProductList().stream().map(product -> {
                                if (product.getId() == operation.getIdProductOne()) {
                                    product.setBalance(product.getBalance() + operation.getBalanceOperation());
                                }
                                BalanceDto balance = new BalanceDto();
                                balance.setBalance(product.getBalance());

                                return accountWebClient.updateAccount(account.getId(),product.getId(), Mono.just(balance));
                            }).collect(Collectors.toList()))

                    )
                    .flatMap(operationDB->operationRepository.save(operation));
        }

            else if(operation.getType().equals(typeThree)){
                result=   accountWebClient.getAccountById(operation.getIdAccount())
                        .switchIfEmpty(Mono.error(new Throwable("Cuenta no encontrada")))
                        .flatMap(account ->
                                Mono.just(account.getProductList().stream().map(product -> {
                                    if (product.getId() == operation.getIdProductOne()) {
                                        product.setBalance(product.getBalance() - operation.getBalanceOperation());
                                    }
                                    BalanceDto balance = new BalanceDto();
                                    balance.setBalance(product.getBalance());

                                    return accountWebClient.updateAccount(account.getId(),product.getId(), Mono.just(balance));
                                }).collect(Collectors.toList()))

                        )
                        .flatMap(operationDB->operationRepository.save(operation));
            }


          return result;


    }
}
