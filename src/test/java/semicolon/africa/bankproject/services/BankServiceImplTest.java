package semicolon.africa.bankproject.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import semicolon.africa.bankproject.dao.model.Account;
import semicolon.africa.bankproject.dao.model.Bank;
import semicolon.africa.bankproject.dao.model.Customer;
import semicolon.africa.bankproject.dto.request.*;
import semicolon.africa.bankproject.dto.response.*;
import semicolon.africa.bankproject.exception.BankNameAlreadyExistException;

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
    void setUp() throws BankNameAlreadyExistException {
        BankRegisterRequest bankRegisterRequest = new BankRegisterRequest();
        BankRegisterRequest bankRegisterRequest1 = new BankRegisterRequest();
        bankRegisterRequest.setBankName("Ores Bank");
        bankRegisterRequest.setBanklocation("Sabo");
        bankRegisterRequest.setCustomerRegisterRequestList(bankRegisterRequest.getCustomerRegisterRequestList());
        bankRegisterRequest1.setBankName("Ore Bank");
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
                .AccountName("ololade")
                .age("40")
                .email("Ololadedemilade@gmail.com")
                .phoneNumber("09031807593")
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
    public void testThatBankCanBeRegister() throws BankNameAlreadyExistException {
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
        FindAllBankRequest findAllBankRequest = FindAllBankRequest
                .builder()
                .numberOfPages(3)
                .pageNumber(2)
                .build();
      Page<Bank> bankPage =  bankService.findAllBanks(findAllBankRequest);
        assertThat(bankPage.getTotalElements()).isNotNull();
        assertThat(bankPage.getTotalElements()).isGreaterThan(0);
        assertEquals(2L, bankService.findAllBanks(findAllBankRequest).getTotalElements());


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
                .bankId(savedBank.getBankId())
                .bankLocation("Lekki")
                .build();
       UpdateBankResponse bank = bankService.updateBankProfile(updateBankRequest);
       assertEquals("Diamond Bank", bank.getBankName());
       assertThat(bank).isNotNull();

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
                .pageNumber(1)
                .numberOfPages(2)
                        .build();
        Page<Customer> foundCustomer = bankService.findAllCustomers(findAllCustomerRequest);
        System.out.println(foundCustomer.getTotalElements());
        assertThat(foundCustomer.getTotalElements()).isNotNull();
       assertEquals(1L, bankService.findAllCustomers(findAllCustomerRequest).getTotalElements());

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
        UpdateCustomerProfileRequest updateCustomerProfileRequest = new UpdateCustomerProfileRequest();
        updateCustomerProfileRequest.setCustomerName("Demilade");
        updateCustomerProfileRequest.setCustomerGender("male");
        updateCustomerProfileRequest.setCustomerAge("45");
        updateCustomerProfileRequest.setCustomerId(savedCustomer.getCustomerId());
        updateCustomerProfileRequest.setBankId(savedBank.getBankId());
        UpdateCustomerProfileResponse response = bankService.updateCustomerProfile(updateCustomerProfileRequest);
        assertEquals("Customer profile successfully updated", response.getMessage());

    }

    @Test
    public void testThatBankCanOpenAccountForCustomer() {
        OpenAccountRequest openAccountRequest = OpenAccountRequest.builder()
                .bankId(savedBank.getBankId())
                .AccountName("Ololade")
                .age("60")
                .email("ololade@gmail.com")
                .phoneNumber("08109093828")
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
                .build();
        Account foundAccount = bankService.findAccountById(findAccountRequest);
        assertThat(foundAccount.getId()).isEqualTo(savedAccount.getId());
        assertThat(foundAccount.getId()).isNotNull();

    }

    @Test
    public void testThatBankCanFindAllAccounts() {
        FindAllAccountRequest findAllAccountRequest = FindAllAccountRequest.builder()
              .bankId(savedBank.getBankId())
                .numberOfPages(3)
                .pageNumber(2)
                .build();
        Page<Account> foundAccount = bankService.findAllAccounts(findAllAccountRequest);
    }

    @Test
    void bankCanDeleteAccount() {
        bankService.deleteAllAccount();
        assertEquals(0, bankService.findTotalNumbersOfAccounts());
    }

    @Test
    public void findAccountByAccountName() {
        FindAccountByName findAccountByName = FindAccountByName
                .builder()
                .bankId(savedBank.getBankId())
                 .accountName(savedAccount.getAccountName())
                 .build();
     Account foundAccount =   bankService.findByAccountName(findAccountByName);


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
                                .bankId(savedBank.getBankId())
                .accountId(savedAccount.getId())
                .build();
        bankService.deleteAccountById(deleteAccountRequest);
        assertEquals(0, bankService.findTotalNumbersOfAccounts());
    }

    @Test
    public void testThatBankCanUpdateAccountProfile() {
           UpdateAccountRequest updateAccountRequest = UpdateAccountRequest.builder()
                      .email("Adesuyiololade@gmail.com")
                   .accountName("Ololade~Demilade")
                     .phoneNumber("08034752394")
                      .age("100")
                      .bankId(savedBank.getBankId())
                   .accountId(savedAccount.getId())
                      .build();
           UpdateAccountResponse updatedAccount =   bankService.updateAccountProfile(updateAccountRequest);
              assertEquals("Ololade~Demilade", updatedAccount.getAccountName());

    }




}