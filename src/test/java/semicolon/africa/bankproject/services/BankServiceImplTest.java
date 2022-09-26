package semicolon.africa.bankproject.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import semicolon.africa.bankproject.dao.model.Bank;
import semicolon.africa.bankproject.dao.model.Customer;
import semicolon.africa.bankproject.dao.repository.BankRepository;
import semicolon.africa.bankproject.dao.repository.CustomerRepository;
import semicolon.africa.bankproject.dto.request.BankRegisterRequest;
import semicolon.africa.bankproject.dto.request.CustomerRegisterRequest;
import semicolon.africa.bankproject.dto.request.OpenAccountRequest;
import semicolon.africa.bankproject.dto.response.BankRegisterResponse;
import semicolon.africa.bankproject.dto.response.CustomerRegisterResponse;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BankServiceImplTest {
    @Autowired
    private BankService bankService;

    @BeforeEach
    void setUp() {


        BankRegisterRequest bankRegisterRequest = BankRegisterRequest
                .builder()
                .bankName("Access Bank")
                .bankLOcation("Sabo")
                .build();
        //bankService.registerBank(bankRegisterRequest);

    }

    @AfterEach
    void tearDown() {
        bankService.deleteAll();
    }

    @Test
    public void bankCanBeCreated() {
        Bank bank = Bank
                .builder()
                .bankName("Access Bank")
                .bankLocation("Sabo")
                .build();
        assertEquals("Access Bank", bank.getBankName());

    }

    @Test
    public void CustomerCanBeCreated() {
        Customer customer = Customer.
                builder()
                .customerName("Ololade")
                .customerAge("15")
                .build();
        assertEquals("Ololade", customer.getCustomerName());
    }

    @Test
    public void testThatBankCanBeRegister() {
        BankRegisterRequest bankRegisterRequest = BankRegisterRequest
                .builder()
                .bankName("Access Bank")
                .bankLOcation("Sabo")
                .build();
       bankService.registerBank(bankRegisterRequest);

        assertEquals(1, bankService.totalNumbersOfBanks());


    }

    @Test
    public void testThatCustomerExistInABank() {
        BankRegisterResponse bankRegisterResponse = BankRegisterResponse.builder().build();

        BankRegisterRequest bankRegisterRequest = BankRegisterRequest
                .builder()
                .bankName("Access Bank")
                .bankLOcation("Sabo")
                .build();
        bankRegisterResponse = bankService.registerBank(bankRegisterRequest);

        CustomerRegisterRequest customerRegister = CustomerRegisterRequest
                .builder()
                .bankId(bankRegisterResponse.getId())
                .customerName("Adesuyi Ololade")
                .customerAge("34")
                .customerGender("Female")
                .build();
        CustomerRegisterResponse  customerRegisterResponse = bankService.registerCustomer(customerRegister);
        assertEquals("Customer successfully registered",customerRegisterResponse.getMessage());


    }

    //    @Test
//    public void testThatBankCanOPenAccountForCustomer(){
//       OpenAccountRequest openAccountRequest = new OpenAccountRequest();
//       openAccountRequest.setAccountName("olol");
//       openAccountRequest.setCustomerGender("f");
//       openAccountRequest.setCustomerAge("50");
//       bankService.openAccount(openAccountRequest);
//       assertEquals(1, bankService.size());
//      // assertEquals("", bankService.findAllAccounts().get(0).getAccountName());
//
//
//    }
//
    @Test
    public void testThatAllBanksCanBeDeleted() {
        bankService.deleteAll();
        assertEquals(0, bankService.totalNumbersOfBanks());

    }

}
