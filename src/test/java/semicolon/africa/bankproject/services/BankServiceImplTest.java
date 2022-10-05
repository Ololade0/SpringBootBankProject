package semicolon.africa.bankproject.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import semicolon.africa.bankproject.dao.model.Bank;
import semicolon.africa.bankproject.dao.model.Customer;
import semicolon.africa.bankproject.dto.request.BankRegisterRequest;
import semicolon.africa.bankproject.dto.request.CustomerRegisterRequest;
import semicolon.africa.bankproject.dto.request.UpdateAccountRequest;
import semicolon.africa.bankproject.dto.request.UpdateBankRequest;
import semicolon.africa.bankproject.dto.response.BankRegisterResponse;
import semicolon.africa.bankproject.dto.response.CustomerRegisterResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BankServiceImplTest {
    @Autowired
    private BankService bankService;

    BankRegisterResponse savedBank;
    CustomerRegisterResponse savedCustomer;

    @BeforeEach
    void setUp() {
        BankRegisterRequest bankRegisterRequest = BankRegisterRequest.builder()
                .bankName("Access Bank")
                .banklocation("Sabo")
                .build();
        savedBank =  bankService.registerBank(bankRegisterRequest);



        CustomerRegisterRequest customerRegisterRequest =  CustomerRegisterRequest.builder()
                .bankId(savedBank.getBankId())
                .customerName("Ololade")
                .customerGender("Female")
                .customerAge("28")
                .build();
         savedCustomer = bankService.saveCustomer(customerRegisterRequest);

    }

    @AfterEach
    void tearDown() {
        bankService.deleteAll();
    }

    @Test
    public void bankCanBeCreated() {
        Bank bank = new Bank();

                bank.setBankName("Access Bank");
               bank .setBankLocation("Sabo");
                      assertEquals("Access Bank", bank.getBankName());

    }

    @Test
    public void testThatBankCanBeRegister() {
        BankRegisterRequest bankRegisterRequest = BankRegisterRequest.builder()
                .bankName("Access Bank")
                .banklocation("Sabo")
                .build();
      savedBank =  bankService.registerBank(bankRegisterRequest);
        assertEquals(2, bankService.totalNumbersOfBanks());
        assertThat(savedBank.getBankId()).isGreaterThan(0);

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
        assertEquals(2, bankService.totalNumbersOfBanks());
    }
    @Test
    public void testThatBankCanBeFindById(){
       Bank foundBank = bankService.getBankById(savedBank.getBankId());
       assertThat(foundBank).isNotNull();
        assertThat(foundBank.getId()).isEqualTo(savedBank.getBankId());
    }

    @Test
    public void findAllBank(){
        bankService.findAllBanks();
        assertEquals("Access Bank", bankService.findAllBanks().get(0).getBankName());
        System.out.println(bankService.findAllBanks().get(0).getBankName());
    }


    @Test
    public void testThatAllBanksCanBeDeleted() {
        bankService.deleteAll();
        assertEquals(0, bankService.totalNumbersOfBanks());

    }
    @Test
    public void deleteBankBYId() {
        bankService.deleteById(savedBank.getBankId());
        assertEquals(0, bankService.totalNumbersOfBanks());

    }

    @Test
    public void testThatBankCanBeUpdated() {
        UpdateBankRequest updateBankRequest = UpdateBankRequest.builder()
                .bankName("Diamond Bank")
                .bankLocation("Lekki")
                .build();
        updateBankRequest.setBankId(savedBank.getBankId());
        bankService.updateBankProfile(updateBankRequest);
        assertEquals("Diamond Bank", bankService.findAllBanks().get(0).getBankName());
        assertEquals("Lekki", bankService.findAllBmrmrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           anks().get(0).getBankLocation());
    }




    @Test
    public void testThatBankCanSaveCustomer(){
        CustomerRegisterRequest customerRegisterRequest = new CustomerRegisterRequest();
        customerRegisterRequest.setBankId(savedBank.getBankId());
       savedCustomer = bankService.saveCustomer(customerRegisterRequest);
        assertThat(savedCustomer.getCustomerId()).isGreaterThan(0);
        assertThat(savedCustomer).isNotNull();
        assertEquals(2, bankService.findTotalNumbersOfCustomers());
    }

    @Test
    public void  testThatBankCanFindCustomerById(){;
       Customer foundCustomer  =  bankService.findCustomerId(savedCustomer.getCustomerId());
       assertThat(foundCustomer.getCustomerId()).isGreaterThan(0);
        assertThat(foundCustomer.getCustomerId()).isEqualTo(savedCustomer.getCustomerId());
    }



}
