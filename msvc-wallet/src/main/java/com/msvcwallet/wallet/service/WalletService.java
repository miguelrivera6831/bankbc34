package com.msvcwallet.wallet.service;
import com.msvcwallet.wallet.model.Wallet;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WalletService {

    Mono<Wallet> save(Wallet wallet);
    Mono<Wallet> update(Wallet wallet);
    Flux<Wallet> findAll();
    Mono<Wallet> getId(String id);
    Mono<Void> delete(String id);

}




