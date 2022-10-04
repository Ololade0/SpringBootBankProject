package semicolon.africa.bankproject.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import semicolon.africa.bankproject.dao.model.Bank;
import semicolon.africa.bankproject.dao.model.Customer;
import semicolon.africa.bankproject.dto.request.BankRegisterRequest;
import semicolon.africa.bankproject.dto.request.CustomerRegisterRequest;
import semicolon.africa.bankproject.dto.request.GetCustomerRequest;
import semicolon.africa.bankproject.dto.request.OpenAccountRequest;
import semicolon.africa.bankproject.dto.response.BankRegisterResponse;
import semicolon.africa.bankproject.dto.response.CustomerRegisterResponse;
import semicolon.africa.bankproject.dto.response.OpenAccountResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.predicate;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BankServiceImplTest {
    @Autowired
    private BankService bankService;

    BankRegisterResponse bankRegisterResponse;

    @BeforeEach
    void setUp() {
        BankRegisterRequest bankRegisterRequest = BankRegisterRequest.builder()
                .bankName("Access Bank")
                .banklocation("Sabo")
                .build();
        bankRegisterResponse =  bankService.registerBank(bankRegisterRequest);
    }

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
        assertEquals("Another newBank successfully registered", bankRegisterResponse1.getMessage());
        assertThat(bankRegisterResponse1).isNotNull();
        assertThat(bankRegisterResponse1.getBankId()).isGreaterThan(0);

    }

    @Test
    public void testThatBankCanBeFindById(){
        BankRegisterRequest bankRegisterRequest = new BankRegisterRequest();
        var response =   bankService.registerBank(bankRegisterRequest);
       Bank foundBank = bankService.getBankById(response.getBankId());
       assertThat(foundBank).isNotNull();
        assertThat(foundBank.getId()).isEqualTo(response.getBankId());
    }


    @Test
    public void testThatAllBanksCanBeDeleted() {
        bankService.deleteAll();
        assertEquals(0, bankService.totalNumbersOfBanks());

    }
    @Test
    public void testThatBankCanSaveCustomer(){
        BankRegisterRequest bankRegisterRequest = new BankRegisterRequest();
        var response =   bankService.registerBank(bankRegisterRequest);
        CustomerRegisterRequest customerRegisterRequest = new CustomerRegisterRequest();
        customerRegisterRequest.setBankId(response.getBankId());
        customerRegisterRequest.setCustomerName("Ololade");
        customerRegisterRequest.setCustomerGender("");
        customerRegisterRequest.setCustomerAge("");
       CustomerRegisterResponse customerRegisterResponse = bankService.saveCustomer(customerRegisterRequest);
        assertEquals("Customer successfully registered", customerRegisterResponse.getMessage());
        assertThat(customerRegisterResponse.getCustomerId()).isGreaterThan(0);
        assertThat(customerRegisterResponse).isNotNull();
    }

    @Test
    public void  testThatBankCanGetOneCustomer(){
       Customer customer  =  bankService.findCustomerId(bankRegisterResponse.getCustomerId());
//       assertThat(customer.getCustomerId()).isGreaterThan(0);
        //assertEquals("", bankService.findAllCustomers().get(0).getCustomerName());


    }



}




























