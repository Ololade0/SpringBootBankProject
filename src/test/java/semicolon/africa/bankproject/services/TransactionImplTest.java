package semicolon.africa.bankproject.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import semicolon.africa.bankproject.dao.model.TransactionType;
import semicolon.africa.bankproject.dao.model.Transactions;
import semicolon.africa.bankproject.dto.request.DepositFundRequest;
import semicolon.africa.bankproject.dto.request.FindAllTransaction;
import semicolon.africa.bankproject.dto.request.TransactionsRequest;
import semicolon.africa.bankproject.dto.request.WithdrawalFundRequest;

import java.math.BigDecimal;

import java.time.LocalDateTime;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TransactionImplTest {

@Autowired
private TransactionServices transactionServices;
Transactions savedTransactions;

    @BeforeEach
    void setUp() {
        TransactionsRequest transactionsRequest = TransactionsRequest.builder()
                .accountNumber("0782384748")
                .currentBalance(BigDecimal.valueOf(10000))
                .pin("1234")
                .transactionDate(LocalDateTime.now())
                .transactionType(TransactionType.DEPOSIT)
                .transactionAmount(BigDecimal.valueOf(90000))
                .build();
        savedTransactions = transactionServices.recordTransactions(transactionsRequest);
        System.out.println(savedTransactions.getId());

    }

    @AfterEach
    void tearDown() {
        transactionServices.deleteAll();
    }

    @Test
    public void transactionCanBeCreated() {
        Transactions transactions =  Transactions.builder()
                        .accountNumber("0782384748")
                        .transactionAmount(BigDecimal.valueOf(90000))
                        .transactionType(TransactionType.DEPOSIT)
                       .build();
        assertEquals("0782384748", transactions.getAccountNumber());
    }

@Test
public void TransactionCanBeDone(){
    TransactionsRequest transactionsRequest = TransactionsRequest.builder()
            .accountNumber("8976544789")
//            .currentBalance(BigDecimal.valueOf(9000))
//            .transactionAmount(BigDecimal.valueOf(40000))
            .transactionType(TransactionType.DEPOSIT)
            .transactionDate(LocalDateTime.now())
            .build();
   Transactions transactions = transactionServices.recordTransactions(transactionsRequest);
   assertThat(transactions).isNotNull();
    assertEquals(2, transactionServices.size());
}

@Test
    public void findTransactionById(){
        Transactions foundTransaction = transactionServices.findTransactionById(savedTransactions.getId());
        assertThat(foundTransaction.getId()).isGreaterThan(String.valueOf(0));
        assertThat(foundTransaction).isNotNull();
        assertThat(foundTransaction.getId()).isEqualTo(savedTransactions.getId());

    }

    @Test
    public void findAllTransaction() {
        FindAllTransaction findAllTransaction = FindAllTransaction.builder()
                .numberOfPerPages(2)
                .pageNumber(1)
                .build();
        Page<Transactions> transactionPage = transactionServices.findAllTransactions(findAllTransaction);
        assertThat(transactionPage).isNotNull();
        assertThat(transactionPage.getTotalElements()).isGreaterThan(0);
        assertEquals(1L, transactionServices.findAllTransactions(findAllTransaction).getTotalElements());



    }
    }






