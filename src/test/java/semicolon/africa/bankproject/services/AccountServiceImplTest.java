package semicolon.africa.bankproject.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import semicolon.africa.bankproject.dao.model.Account;
import semicolon.africa.bankproject.dto.request.DepositFundRequest;
import semicolon.africa.bankproject.dto.request.OpenAccountRequest;
import semicolon.africa.bankproject.dto.request.UpdateAccountRequest;
import semicolon.africa.bankproject.dto.request.WithdrawalFundRequest;

import semicolon.africa.bankproject.dto.response.WithdrawalFundResponse;


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
                .gender("female")
                .build();
        savedAccount = accountService.openAccount(openAccountRequest);

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
                .AccountName("Adesuyi")
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
        savedAccount = accountService.openAccount(openAccountRequest);
        assertThat(savedAccount).isNotNull();
        assertEquals(2, accountService.totalNumberOfAccount());

    }

    @Test
    public void findAccountById() {
        Account foundAccount = accountService.findAccountById(savedAccount.getId());
        assertThat(foundAccount).isNotNull();
        assertThat(foundAccount.getId()).isEqualTo(savedAccount.getId());

    }

    @Test
    public void findAllAccount() {
        accountService.findAllAccount();
        assertEquals("Adesuyi", accountService.findAllAccount().get(0).getAccountName());
        assertEquals("adesuyiololade@gmail.com", accountService.findAllAccount().get(0).getEmail());
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
                .AccountName("Demilade")
                .email("demilade@gmail.com")
                .AccountName("09031807593")
                .age("70")
                .build();
        updateAccountRequest.setAccountId(savedAccount.getId());
        accountService.updateAccount(updateAccountRequest);
        assertEquals("demilade@gmail.com", accountService.findAllAccount().get(0).getEmail());
        assertEquals("70", accountService.findAllAccount().get(0).getAge());
    }

    @Test
    public void testThatAccountCanPerformDepositFundTransaction() {
        DepositFundRequest depositFundRequest = DepositFundRequest
                .builder()
                .beneficiaryAccount("0782807561")
                .depositFunds(BigDecimal.valueOf(60000))
                .accountId(savedAccount.getId())
                .currentBalance(BigDecimal.valueOf(10000))
                .build();
     BigDecimal depositFundResponse = accountService.depositFundsIntoAccount(depositFundRequest);
        assertEquals(BigDecimal.valueOf(70000), depositFundResponse);

    }

    @Test
    public void testThatSenderCanTransferFundToBeneficiaryAccountWithValidPin() {
        WithdrawalFundRequest withdrawalFundRequest = WithdrawalFundRequest
                .builder()
                .pin(12345)
                .senderAccountNumber("45678")
                .beneficiaryAccountNumber("0782807561")
                .currentBalance(BigDecimal.valueOf(10000))
                .withdrawalAmount(BigDecimal.valueOf(5000))
                .build();
        WithdrawalFundResponse withdrawalFundResponse = accountService.TransferFundsithValidPin(withdrawalFundRequest);
        assertEquals("Transaction successful", withdrawalFundResponse.getMessage());
        assertEquals(BigDecimal.valueOf(5000), withdrawalFundResponse.getCurrentBalance());
    }



}