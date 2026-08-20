package com.dev.service;

import com.dev.model.Order;
import com.dev.model.Seller;
import com.dev.model.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction createTransaction(Order order);
    List<Transaction> getTransactionBySellerId(Seller seller);
    List<Transaction> getAllTransaction();
}
