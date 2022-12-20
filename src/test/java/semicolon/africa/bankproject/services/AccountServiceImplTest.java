package semicolon.africa.bankproject.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import semicolon.africa.bankproject.dao.model.Account;
import semicolon.africa.bankproject.dao.model.TransactionType;
import semicolon.africa.bankproject.dto.request.*;
import semicolon.africa.bankproject.utils.Utils;


import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class AccountServiceImplTest {
    @Autowired
    private AccountService accountService;
    Account savedAccount;

    @BeforeEach
    void setUp() {
        OpenAccountRequest openAccountRequest = OpenAccountRequest.builder()
                .phoneNumber("08109093828")
                .email("adesuyiololade@gmail.com")
                .AccountName("Adesuyi")
                .age("23")
                .balance(BigDecimal.valueOf(10_000))
                .gender("female")
                .build();
      savedAccount =  accountService.openAccount(openAccountRequest);
    }

    @AfterEach
    void tearDown() {
        accountService.deleteAll();
    }

    @Test
    public void accountCanBeCreated() {
        Account account = Account
                .builder()
                .phoneNumber("08109093828")
                .email("adesuyiololade@gmail.com")
                .accountName("Adesuyi")
                .age("23")
                .gender("female")
                .build();
        assertEquals("Adesuyi", account.getAccountName());

    }

    @Test
    public void accountCanBeOpen() {
        OpenAccountRequest openAccountRequest = OpenAccountRequest.builder()
                .phoneNumber("08109093828")
                .email("adesuyiololade@gmail.com")
                .AccountName("Adesuyi")
                .age("23")
                .balance(BigDecimal.valueOf(10_000))
                .gender("female")
                .build();
        assertThat(savedAccount).isNotNull();
        accountService.openAccount(openAccountRequest);
        assertEquals(2, accountService.totalNumberOfAccount());

    }

    @Test
    public void findAccountById() {
        Account foundAccount = accountService.findAccountById(savedAccount.getId());
        assertThat(foundAccount).isNotNull();
        assertThat(foundAccount.getId()).isEqualTo(savedAccount.getId());

    }
    @Test
    public void findAccountByAccountName() {
        Account foundAccount = accountService.findAccountByAccountName(savedAccount.getAccountName());
        assertThat(foundAccount).isNotNull();
        assertThat(foundAccount.getAccountName()).isEqualTo(savedAccount.getAccountName());

    }
    @Test
    public void findAccountByAccountNumber() {
        Account foundAccount = accountService.findAccountByAccountNUmber(savedAccount.getAccountNumber());
        assertThat(foundAccount).isNotNull();
        assertThat(foundAccount.getId()).isEqualTo(savedAccount.getId());

    }

    @Test
    public void findAllAccount() {
        FindAllAccountRequest findAllAccountRequest = FindAllAccountRequest.builder()
                .pageNumber(3)
                .numberOfPages(1)
                .build();
       Page<Account> accountPage = accountService.findAllAccount(findAllAccountRequest);
        assertThat(accountPage.getTotalElements()).isNotNull();
        assertEquals(1L, accountService.findAllAccount(findAllAccountRequest).getTotalElements());

    }

    @Test
    public void testThatAllAccountCanBeDeleted() {
        accountService.deleteAll();
        assertEquals(0, accountService.totalNumberOfAccount());
    }

    @Test
    public void testThatAccountCanBeDeletedById() {
        accountService.deleteBYId(savedAccount.getId());
        assertEquals(0, accountService.totalNumberOfAccount());
    }

    @Test
    public void testThatAccountCanBeUpdated() {
        UpdateAccountRequest updateAccountRequest = UpdateAccountRequest.builder()
                .accountId(savedAccount.getId())
                .accountName("Demilade")
                .email("demilade@gmail.com")
               .phoneNumber("09031807593")
                .age("70")
                .build();
      Account updatedAccount =  accountService.updateAccount(updateAccountRequest);
      assertEquals("Demilade", updatedAccount.getAccountName());

    }

    @Test
    public void customerCanTransferFundToAnotherCustomerAccount_BeneficairyBalanceIncreases()throws Exception {
        DepositFundRequest depositFundRequest = DepositFundRequest
                .builder()
                .beneficiaryAccount(savedAccount.getAccountNumber())
                .transactionAmount(BigDecimal.valueOf(60000))
                .build();
     BigDecimal depositFundResponse = accountService.depositFundsIntoAccount(depositFundRequest);
        assertEquals(BigDecimal.valueOf(70000), depositFundResponse);
    }

    @Test
    public void accountCanTransferFundToAnotherAccount_SenderBalanceDecrease(){
        WithdrawalFundRequest withdrawalFundRequest = WithdrawalFundRequest
                .builder()
                .accountNumber(savedAccount.getAccountNumber())
                .withdrawalAmount(BigDecimal.valueOf(5000))
                .build();
      BigDecimal withdrawalFundResponse = accountService.WithdrawFundFromAccount(withdrawalFundRequest);
        assertEquals(BigDecimal.valueOf(5000), withdrawalFundResponse);
    }




}