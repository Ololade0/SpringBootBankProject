package semicolon.africa.bankproject.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import semicolon.africa.bankproject.dao.model.Account;
import semicolon.africa.bankproject.dto.request.OpenAccountRequest;
import semicolon.africa.bankproject.dto.request.UpdateAccountRequest;


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
    public void accountCanBeCreated(){
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
    public void accountCanBeOpen(){
        OpenAccountRequest openAccountRequest = OpenAccountRequest.builder()
                .phoneNumber("08109093828")
                .email("adesuyiololade@gmail.com")
                .AccountName("Adesuyi")
                .age("23")
                .gender("female")
                .build();
       savedAccount = accountService.openAccount(openAccountRequest);
        assertThat(savedAccount).isNotNull();
        assertEquals(2, accountService.totalNumberOfAccount());
        System.out.println(savedAccount.getId());

    }

    @Test
    public void findAccountById(){
       Account foundAccount =  accountService.findAccountById(savedAccount.getId());
        assertThat(foundAccount).isNotNull();
      //  assertThat(foundAccount.getId()).isGreaterThan(0);
        assertThat(foundAccount.getId()).isEqualTo(savedAccount.getId());

    }

    @Test
    public void findAllAccount(){
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
}