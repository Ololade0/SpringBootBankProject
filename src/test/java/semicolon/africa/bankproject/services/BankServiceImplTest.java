package semicolon.africa.bankproject.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import semicolon.africa.bankproject.dao.model.Account;
import semicolon.africa.bankproject.dao.model.Bank;
import semicolon.africa.bankproject.dao.model.Customer;
import semicolon.africa.bankproject.dto.request.*;
import semicolon.africa.bankproject.dto.response.BankRegisterResponse;
import semicolon.africa.bankproject.dto.response.CustomerRegisterResponse;
import semicolon.africa.bankproject.dto.response.OpenAccountResponse;
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

    OpenAccountResponse savedAccount;

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
                .customerAccountNumber("65758696")
                .customerGender("Female")
                .customerAge("28")
                .build();
        savedCustomer = bankService.saveCustomer(customerRegisterRequest);


        OpenAccountRequest openAccountRequest = OpenAccountRequest.builder()
                .bankId(savedBank.getBankId())
                .customerId(savedCustomer.getCustomerId())
                .AccountName("ololade")
                .age("")
                .email("")
                .phoneNumber("")
                .build();
        savedAccount = bankService.openCustomerAccount(openAccountRequest);
   }

    @AfterEach
    void tearDown() {

        bankService.deleteAll();
        bankService.deletedAllCustomers();
        bankService.deleteAllAccount();
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
        Bank foundBank = bankService.findBankById(savedBank.getBankId());
        Bank foundBank1 = bankService.findBankById(savedBank1.getBankId());
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
    public void testThatBankProfileCanBeUpdated() {
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
        assertThat(savedCustomer).isNotNull();
        assertEquals(2, bankService.findTotalNumbersOfCustomers());
    }

    @Test
    public void testThatBankCanFindCustomerById() {
        FindCustomerRequest findCustomerRequest = new FindCustomerRequest();
        findCustomerRequest.setBankId(savedBank.getBankId());
       findCustomerRequest.setCustomerId(savedCustomer.getCustomerId());
        Customer foundCustomer = bankService.findCustomerId(findCustomerRequest);
        assertThat(foundCustomer.getCustomerId()).isEqualTo(savedCustomer.getCustomerId());
    }

    @Test
    public void testThatBankCanFindAllCustomer() {
        FindAllCustomerRequest findAllCustomerRequest = FindAllCustomerRequest.builder()
                .bankId(savedBank1.getBankId())
               // .customerId(savedCustomer.getCustomerId())
               // .customerName(savedCustomer.getCustomerName())
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
        String customer = bankService.deleteALLCustomers(deleteAllCustomerRequest);
        assertEquals(0, bankService.findTotalNumbersOfCustomers());
        assertEquals("Customer successfully deleted", customer);
    }

    @Test
    public void testThatBankCanUpdateCustomerProfile() {

        FindAllCustomerRequest findAllCustomerRequest = FindAllCustomerRequest.builder()
                .bankId(savedBank1.getBankId())
                .build();
        List<Customer> foundCustomer = bankService.findAllCustomers(findAllCustomerRequest);

        UpdateCustomerProfileRequest updateCustomerProfileRequest = new UpdateCustomerProfileRequest();
        updateCustomerProfileRequest.setCustomerName("Demilade");
        updateCustomerProfileRequest.setCustomerGender("male");
        updateCustomerProfileRequest.setCustomerAge("45");
        updateCustomerProfileRequest.setCustomerId(savedCustomer.getCustomerId());
        updateCustomerProfileRequest.setBankId(savedBank.getBankId());
        UpdateCustomerProfileResponse response = bankService.updateCustomerProfile(updateCustomerProfileRequest);
        assertEquals("Demilade", bankService.findAllCustomers(findAllCustomerRequest).get(0).getCustomerName());
        assertEquals("male", bankService.findAllCustomers(findAllCustomerRequest).get(0).getCustomerGender());
        assertEquals("Customer profile successfully updated", response.getMessage());

    }

    @Test
    public void testThatBankCanOpenAccount() {
        OpenAccountRequest openAccountRequest = OpenAccountRequest.builder()
                .bankId(savedBank.getBankId())
                .customerId(savedCustomer.getCustomerId())
                .AccountName(savedCustomer.getCustomerName())
                .age("60")
                .email("ololade@gmail.com")
                .phoneNumber("08109093828")
                .accountNumber("0782807561")
                .build();
        OpenAccountResponse foundAccount = bankService.openCustomerAccount(openAccountRequest);
        assertThat(foundAccount).isNotNull();
        assertEquals(2, bankService.findTotalNumbersOfAccounts());
    }

    @Test
    public void testThatBankCanFindAccountById() {
        FindAccountRequest findAccountRequest = FindAccountRequest
                .builder()
                .accoundId(savedAccount.getId())
                .bankId(savedBank.getBankId())
                .customerId(savedCustomer.getCustomerId())
                .build();
        Account foundAccount = bankService.findAccountById(findAccountRequest);
        assertThat(foundAccount.getId()).isEqualTo(savedAccount.getId());
        assertThat(foundAccount.getId()).isNotNull();

    }

    @Test
    public void testThatBankCanFindAllAccounts() {
        FindAllAccountRequest findAllAccountRequest = FindAllAccountRequest.builder()
                .customerId(savedCustomer.getCustomerId())
                .bankId(savedBank.getBankId())
              .accountId(savedAccount.getId())
                .build();
        List<Account> foundAccount = bankService.findAllAccounts(findAllAccountRequest);
        assertThat(foundAccount.get(0).getId()).isNotNull();
        assertThat(foundAccount.get(0).getId()).isEqualTo(savedAccount.getId());
    }

    @Test
    void bankCanDeleteAccount() {
        bankService.deleteAllAccount();
        assertEquals(0, bankService.findTotalNumbersOfAccounts());
    }

    @Test
    public void findAccountByAccountName() {
        Account foundAccount = bankService.findAccountByAccountName(savedAccount.getAccountName());
        assertThat(foundAccount).isNotNull();
//       assertThat(foundAccount.getAccountName()).isEqualTo(savedAccount.getAccountName());

    }
    @Test
    public void findAccountByAccountNumber() {
        Account foundAccount = bankService.findAccountByAccountNUmber(savedAccount.getAccountNumber());
        assertThat(foundAccount).isNotNull();
        assertThat(foundAccount.getId()).isEqualTo(savedAccount.getId());

   }

    @Test
    public void testThatBankCanDeleteAccountById() {
        DeleteAccountRequest deleteAccountRequest = DeleteAccountRequest.builder()
                .customerId(savedCustomer.getCustomerId())
                .bankId(savedBank.getBankId())
                .accountId(savedAccount.getId())
                .build();
        bankService.deleteAccountById(deleteAccountRequest);
        assertEquals(0, bankService.findTotalNumbersOfAccounts());
    }

    @Test
    public void testThatBankCanUpdateAccountProfile() {
        FindAllAccountRequest findAllAccountRequest = FindAllAccountRequest.builder()
                .customerId(savedCustomer.getCustomerId())
                .bankId(savedBank.getBankId())
                .accountId(savedAccount.getId())
                .build();
        List<Account> foundAccount = bankService.findAllAccounts(findAllAccountRequest);

              UpdateAccountRequest updateAccountRequest = UpdateAccountRequest.builder()
                      .email("Adesuyiololade@gmail.com")
//                      ("Ololade~Demilade")
                      .phoneNumber("08034752394")
                      .age("100")
                      .customerId(savedCustomer.getCustomerId())
                      .bankId(savedBank.getBankId())
                      .accountName(savedAccount.getId())
                      .build();
              bankService.updateAccountProfile(updateAccountRequest);
//        assertEquals("Ololade~Demilade", bankService.findAllAccounts(findAllAccountRequest).get(0).getAccountName());
        assertEquals("100", bankService.findAllAccounts(findAllAccountRequest).get(0).getAge());
        assertEquals("Adesuyiololade@gmail.com", bankService.findAllAccounts(findAllAccountRequest).get(0).getEmail());
        assertEquals("08034752394", bankService.findAllAccounts(findAllAccountRequest).get(0).getPhoneNumber());

    }


}