package semicolon.africa.bankproject.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import semicolon.africa.bankproject.dao.model.Transactions;
import semicolon.africa.bankproject.dao.repository.TransactionRepository;
import semicolon.africa.bankproject.dto.request.TransactionsRequest;

import java.util.List;

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
                .sender("Ololade")
                .benefactor("Adesuyi")
                .transactionAmount("900,000")
                .transactionType("Deposit")
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
                        .sender("Ololade")
                        .benefactor("Adesuyi")
                        .transactionAmount("900,000")
                        .transactionType("Deposit")
                       .build();
        assertEquals("Ololade", transactions.getSender());
    }

@Test
public void TransactionCanBeDone(){
    TransactionsRequest transactionsRequest = TransactionsRequest.builder()
            .sender("Ololade")
            .benefactor("Adesuyi")
            .transactionAmount("900,000")
            .transactionType("Deposit")
            .build();
   Transactions transactions = transactionServices.recordTransactions(transactionsRequest);
   assertThat(transactions).isNotNull();
   assertEquals(2, transactionServices.size());
}

@Test
    public void findTransactionById(){
        Transactions foundTransaction = transactionServices.findTransactionById(savedTransactions.getId());
       // assertThat(foundTransaction.getId()).isGreaterThan(0);
        assertThat(foundTransaction).isNotNull();
        assertThat(foundTransaction.getId()).isEqualTo(savedTransactions.getId());

    }

    @Test
    public void findAllTransaction(){
        transactionServices.findAllTransactions();
        assertEquals("900,000", transactionServices.findAllTransactions().get(0).getTransactionAmount());
        assertEquals("Adesuyi", transactionServices.findAllTransactions().get(0).getBenefactor());

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
    }






