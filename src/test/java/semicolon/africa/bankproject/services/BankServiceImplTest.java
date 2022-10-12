package semicolon.africa.bankproject.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import semicolon.africa.bankproject.dao.model.Bank;
import semicolon.africa.bankproject.dao.model.Customer;
import semicolon.africa.bankproject.dto.request.*;
import semicolon.africa.bankproject.dto.response.BankRegisterResponse;
import semicolon.africa.bankproject.dto.response.CustomerRegisterResponse;
import semicolon.africa.bankproject.dto.response.UpdateCustomerProfileResponse;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BankServiceImplTest {
    @Autowired
    private BankService bankService;

    BankRegisterResponse savedBank;
    BankRegisterResponse savedBank1;
    CustomerRegisterResponse savedCustomer;

    @BeforeEach
    void setUp() {
        BankRegisterRequest bankRegisterRequest = new BankRegisterRequest();
        BankRegisterRequest bankRegisterRequest1 = new BankRegisterRequest();
        bankRegisterRequest.setBankName("Access Bank");
        bankRegisterRequest.setBanklocation("Sabo");
        bankRegisterRequest1.setBankName("GT Bank");
        bankRegisterRequest1.setBanklocation("Lekki");
        savedBank = bankService.registerBank(bankRegisterRequest);
        savedBank1 = bankService.registerBank(bankRegisterRequest1);


        CustomerRegisterRequest customerRegisterRequest = CustomerRegisterRequest.builder()
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
        bank.setBankLocation("Sabo");
        assertEquals("Access Bank", bank.getBankName());

    }

    @Test
    public void testThatBankCanBeRegister() {
        BankRegisterRequest bankRegisterRequest = new BankRegisterRequest();
        BankRegisterRequest bankRegisterRequest1 = new BankRegisterRequest();
        savedBank = bankService.registerBank(bankRegisterRequest);
        savedBank1 = bankService.registerBank(bankRegisterRequest1);
        assertEquals(4, bankService.totalNumbersOfBanks());


    }

    @Test
    public void testThatBankCanBeFindById() {
        Bank foundBank = bankService.getBankById(savedBank.getBankId());
        Bank foundBank1 = bankService.getBankById(savedBank1.getBankId());
        assertThat(foundBank).isNotNull();
        assertThat(foundBank1).isNotNull();
        assertThat(foundBank.getId()).isEqualTo(savedBank.getBankId());
        assertThat(foundBank1.getId()).isEqualTo(savedBank1.getBankId());
    }

    @Test
    public void findAllBank() {
        bankService.findAllBanks();
        assertEquals("Access Bank", bankService.findAllBanks().get(0).getBankName());
        assertEquals("GT Bank", bankService.findAllBanks().get(1).getBankName());

    }


    @Test
    public void testThatAllBanksCanBeDeleted() {
        bankService.deleteAll();
        assertEquals(0, bankService.totalNumbersOfBanks());

    }

    @Test
    public void deleteBankBYId() {
        bankService.deleteById(savedBank.getBankId());
        assertEquals(1, bankService.totalNumbersOfBanks());

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
        assertEquals("Lekki", bankService.findAllBanks().get(0).getBankLocation());
    }


    @Test
    public void testThatBankCanSaveCustomer() {
        CustomerRegisterRequest customerRegisterRequest = new CustomerRegisterRequest();
        customerRegisterRequest.setBankId(savedBank.getBankId());
        customerRegisterRequest.setBankId(savedBank1.getBankId());
        savedCustomer = bankService.saveCustomer(customerRegisterRequest);
        assertThat(savedCustomer.getCustomerId()).isGreaterThan(0);
        assertThat(savedCustomer).isNotNull();
        assertEquals(2, bankService.findTotalNumbersOfCustomers());
    }

    @Test
    public void testThatBankCanFindCustomerById() {
        FindBankRequest findBankRequest = new FindBankRequest();
        findBankRequest.setBankId(savedBank.getBankId());
        findBankRequest.setCustomerId(savedCustomer.getCustomerId());
        Customer foundCustomer = bankService.findCustomerId(findBankRequest);
        assertThat(foundCustomer.getCustomerId()).isGreaterThan(0);
        assertThat(foundCustomer.getCustomerId()).isEqualTo(savedCustomer.getCustomerId());
    }
    @Test
    public void testThatBankCanFindAllCustomer() {
        FindAllCustomerRequest findAllCustomerRequest = FindAllCustomerRequest.builder()
                .bankId(savedBank1.getBankId())
                .customerId(savedCustomer.getCustomerId())
                .customerName(savedCustomer.getCustomerName())
                .build();
        List<Customer> foundCustomer = bankService.findAllCustomers(findAllCustomerRequest);
        assertEquals("Ololade", bankService.findAllCustomers(findAllCustomerRequest).get(0).getCustomerName());
        assertThat(foundCustomer.get(0).getCustomerId()).isEqualTo(savedCustomer.getCustomerId());
    }

    @Test
    public void testThatBankCanDeleteCustomerById() {
        DeleteCustomerRequest deleteCustomerRequest = DeleteCustomerRequest.builder()
                .customerName("Ololade")
                .customerId(savedCustomer.getCustomerId())
                .bankId(savedBank.getBankId())
                .build();
           bankService.deleteCustomerById(deleteCustomerRequest);
        assertEquals(0, bankService.findTotalNumbersOfCustomers());
   }

    @Test
    public void testThatBankCanDeleteAllCustomers() {
        DeleteAllCustomerRequest deleteAllCustomerRequest = new DeleteAllCustomerRequest();
        deleteAllCustomerRequest.setCustomerId(savedCustomer.getCustomerId());
        deleteAllCustomerRequest.setBankId(savedBank.getBankId());
       String customer =  bankService.deleteALLCustomers(deleteAllCustomerRequest);
        assertEquals(0, bankService.findTotalNumbersOfCustomers());
        assertEquals("",  customer);
    }

    @Test
    public void testThatBankCanUpdateCustomerProfile() {
        UpdateCustomerProfileRequest updateCustomerProfileRequest = new UpdateCustomerProfileRequest();
        updateCustomerProfileRequest.setCustomerName("Demilade");
             updateCustomerProfileRequest.setCustomerGender("male");
             updateCustomerProfileRequest.setCustomerAge("45");
             updateCustomerProfileRequest.setBankId(savedBank.getBankId());
             updateCustomerProfileRequest.setCustomerId(savedCustomer.getCustomerId());
             updateCustomerProfileRequest.setBankId(savedBank.getBankId());
      UpdateCustomerProfileResponse response =  bankService.updateCustomerProfile(updateCustomerProfileRequest);
        assertEquals("", response.getMessage() );


    }


}