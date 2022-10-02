package semicolon.africa.bankproject.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import semicolon.africa.bankproject.dao.model.Bank;
import semicolon.africa.bankproject.dao.model.Customer;
import semicolon.africa.bankproject.dto.request.BankRegisterRequest;
import semicolon.africa.bankproject.dto.request.CustomerRegisterRequest;
import semicolon.africa.bankproject.dto.response.BankRegisterResponse;
import semicolon.africa.bankproject.dto.response.CustomerRegisterResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BankServiceImplTest {
    @Autowired
    private BankService bankService;

//    @BeforeEach
//    void setUp() {
//        BankRegisterRequest bankRegisterRequest = BankRegisterRequest.builder()
//                .bankName("Access Bank")
//                .banklocation("Sabo")
//                .build();
//        BankRegisterResponse bankRegisterResponse =  bankService.registerBank(bankRegisterRequest);

  //  }

//    @AfterEach
//    void tearDown() {
//        bankService.deleteAll();
//    }

    @Test
    public void bankCanBeCreated() {
        Bank bank = new Bank();

                bank.setBankName("Access Bank");
               bank .setBankLocation("Sabo");
                      assertEquals("Access Bank", bank.getBankName());

    }

    @Test
    public void CustomerCanBeCreated() {
        Customer customer = new Customer();
               customer .setCustomerName("Ololade");
               customer .setCustomerGender("15");
        assertEquals("Ololade", customer.getCustomerName());
    }

    @Test
    public void testThatBankCanBeRegister() {
        BankRegisterRequest bankRegisterRequest = BankRegisterRequest.builder()
                .bankName("Access Bank")
                .banklocation("Sabo")
                .build();
        BankRegisterResponse bankRegisterResponse =  bankService.registerBank(bankRegisterRequest);
         assertEquals("Bank successfully registered", bankRegisterResponse.getMessage());
        assertThat(bankRegisterResponse).isNotNull();
        assertThat(bankRegisterResponse.getBankId()).isGreaterThan(0);

    }
   @Test
    public void testThatAnotherBankCanBeRegister_RepositoryIncreases() {
        BankRegisterRequest bankRegisterRequest1 = new  BankRegisterRequest ();
        bankRegisterRequest1.setBankName("GT Bank");
        bankRegisterRequest1.setBanklocation("Lekki");
     BankRegisterResponse bankRegisterResponse1 =   bankService.addNewBank(bankRegisterRequest1);
        assertEquals("Another bank successfully registered", bankRegisterResponse1.getMessage());
        assertThat(bankRegisterResponse1).isNotNull();

    }



   @Test
    public void testThatCustomerExistInABank() {

       BankRegisterRequest bankRegisterRequest = BankRegisterRequest.builder()
               .bankName("Access Bank")
               .banklocation("Sabo")
               .build();
       BankRegisterResponse bankRegisterResponse =  bankService.registerBank(bankRegisterRequest);
       assertEquals("Bank successfully registered", bankRegisterResponse.getMessage());
       assertThat(bankRegisterResponse).isNotNull();
       assertThat(bankRegisterResponse.getBankId()).isGreaterThan(0);

//       BankRegisterRequest bankRegisterRequest = new BankRegisterRequest();
//        BankRegisterResponse  bankRegisterResponse = bankService.registerBank(bankRegisterRequest);
        CustomerRegisterRequest customerRegister = new CustomerRegisterRequest();
        customerRegister.setCustomerName("olol");
        customerRegister.setCustomerGender("f");
        customerRegister.setCustomerAge("55");
        customerRegister.setBankId(bankRegisterResponse.getBankId());
        CustomerRegisterResponse customerRegisterResponse = bankService.registerCustomer(customerRegister);

        assertEquals("Customer successfully registered",customerRegisterResponse.getMessage());

   }
//
     //   @Test
//    public void testThatBankCanOPenAccountForCustomer(){
//            BankRegisterRequest bankRegisterRequest = new BankRegisterRequest();
//            BankRegisterResponse  bankRegisterResponse = bankService.registerBank(bankRegisterRequest);
//            CustomerRegisterRequest customerRegister = new CustomerRegisterRequest();
//            CustomerRegisterResponse  customerRegisterResponse = bankService.registerCustomer(customerRegister);
//       OpenAccountRequest openAccountRequest = new OpenAccountRequest();
//       openAccountRequest.setAccountName("ooo");
//       openAccountRequest.setBankId(bankRegisterResponse.getBankId());
//       openAccountRequest.setCustomerId(customerRegisterResponse.getCustomerId());
//       openAccountRequest.setCustomerGender("f");
//       openAccountRequest.setCustomerAge("50");
//            OpenAccountResponse openAccountResponse =  bankService.openAccount(openAccountRequest);
//            assertEquals("Account Succesfully opened", openAccountResponse.getMessage());
//    }
//
//
//
//    @Test
//    public void testThatAllBanksCanBeDeleted() {
//        bankService.deleteAll();
//        assertEquals(0, bankService.totalNumbersOfBanks());
//
//    }

}
