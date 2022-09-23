package com.msvcwallet.wallet.service;

import com.msvcwallet.wallet.model.Wallet;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class WalletServiceImpl  implements WalletService  {


    @Override
    public Mono<Wallet> save(Wallet wallet) {
        return null;
    }

    @Override
    public Mono<Wallet> update(Wallet wallet) {
        return null;
    }

    @Override
    public Flux<Wallet> findAll() {
        return null;
    }

    @Override
    public Mono<Wallet> getId(String id) {
        return null;
    }

    @Override
    public Mono<Void> delete(String id) {
        return null;
    }
}
