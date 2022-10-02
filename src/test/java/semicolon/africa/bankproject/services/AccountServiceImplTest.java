//package semicolon.africa.bankproject.services;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import semicolon.africa.bankproject.dao.model.Account;
//import semicolon.africa.bankproject.dto.request.OpenAccountRequest;
//import semicolon.africa.bankproject.dto.response.OpenAccountResponse;
//
//import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest
//class AccountServiceImplTest {
//    @Autowired
//    private AccountService accountService;
//
//    @Test
//    public void accountCanBeCreated(){
//        Account account = Account
//                .builder()
//                .AccountName("Adesuyi")
//                .age("23")
//                .gender("female")
//                .build();
//        assertEquals("Adesuyi", account.getAccountName());
//
//    }
//
//    @Test
//    public void accountCanBeOpen(){
//        OpenAccountRequest openAccountRequest = new OpenAccountRequest();
//        openAccountRequest.setAccountName("");
//        openAccountRequest.setCustomerAge("");
//        accountService.openAccount(openAccountRequest);
//        OpenAccountResponse openAccountResponse = new OpenAccountResponse();
//        assertEquals("opemed",  openAccountResponse.getMessage());
//
//    }

//}