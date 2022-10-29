package semicolon.africa.bankproject.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import semicolon.africa.bankproject.dao.model.TransactionType;
import semicolon.africa.bankproject.dao.model.Transactions;
import semicolon.africa.bankproject.dto.request.DepositFundRequest;
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
                .transactionDate(LocalDateTime.now())
                .transactionAmount(BigDecimal.valueOf(90000))
                .transactionType(TransactionType.DEPOSIT)
                .build();
        savedTransactions = transactionServices.recordTransactions(transactionsRequest);

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
            .transactionAmount(BigDecimal.valueOf(40000))
                .transactionType(TransactionType.DEPOSIT)
            .transactionDate(LocalDateTime.now())
            .build();
   Transactions transactions = transactionServices.recordTransactions(transactionsRequest);
   assertThat(transactions).isNotNull();
    System.out.println(transactions);
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
    public void findAllTransaction(){
        transactionServices.findAllTransactions();
        assertEquals(BigDecimal.valueOf(90000), transactionServices.findAllTransactions().get(0).getTransactionAmount());
        assertEquals("0782384748", transactionServices.findAllTransactions().get(0).getAccountNumber());

        }

        @Test
        public void testThatAllTransactionCanBeDeleted() {
       transactionServices.deleteAll();
       assertEquals(0, transactionServices.size());
        }

    @Test
    public void testThatTransactionCanBeDeletedById() {
        transactionServices.deleteById(savedTransactions.getId());
        assertEquals(0, transactionServices.size());

    }


        @Test
    public void customerCanTransfertFundToAnotherCustomerAccount_BeneficairyBalanceIncreases() throws Exception {
        DepositFundRequest depositFundRequest = DepositFundRequest.builder()
                .transactionAmount(BigDecimal.valueOf(10000))
//                .transactionDate(LocalDateTime.now())
//                .transactionType(TransactionType.DEPOSIT)
//                .currentBalance(BigDecimal.valueOf(3000))
//                .id(savedTransactions.getId())
                .beneficiaryAccount("12345")
//                .pin("2345")
                .build();
      var savedTransactions = transactionServices.depositFunds(depositFundRequest);
        assertEquals(BigDecimal.valueOf(13000), savedTransactions);


    }
    @Test
    public void customerCanTransferFundToAnotherCustomerAccount_SenderBalanceDecrease(){
        WithdrawalFundRequest withdrawalFundRequest = WithdrawalFundRequest
                .builder()
                .accountNumber("1234")
                .transactionDate(LocalDateTime.now())
                .transactionType(TransactionType.DEPOSIT)
                .withdrawalAmount(BigDecimal.valueOf(10000))
                .currentBalance(BigDecimal.valueOf(100000))
                .pin("1234")
                .build();
        BigDecimal savedTransaction  = transactionServices.TransferFund(withdrawalFundRequest);
        assertEquals(BigDecimal.valueOf(90000), savedTransaction);

    }

    }






