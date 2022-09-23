package com.msvcwallet.wallet.repository;

import com.msvcwallet.wallet.model.Wallet;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface WalletRepository extends ReactiveMongoRepository<Wallet,String> {
}
