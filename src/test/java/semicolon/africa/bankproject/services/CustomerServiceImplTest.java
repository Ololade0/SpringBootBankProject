package semicolon.africa.bankproject.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import semicolon.africa.bankproject.dao.model.Account;
import semicolon.africa.bankproject.dao.model.Customer;
import semicolon.africa.bankproject.dto.request.*;
import semicolon.africa.bankproject.dto.response.DepositFundResponse;
import semicolon.africa.bankproject.dto.response.OpenAccountResponse;
import semicolon.africa.bankproject.dto.response.WithdrawalFundResponse;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CustomerServiceImplTest {
    @Autowired
    private CustomerService customerService;
    Customer savedCustomer;
    OpenAccountResponse savedAccount;

    @BeforeEach
    void setUp() {
        CustomerRegisterRequest customerRegister = new CustomerRegisterRequest();
        customerRegister.setCustomerName("Adesuyi");
        customerRegister.setCustomerGender("female");
        customerRegister.setCustomerAccountNumber("34567");
        customerRegister.setCustomerAge("55");
        savedCustomer = customerService.saveNewCustomer(customerRegister);

        OpenAccountRequest openAccountRequest = OpenAccountRequest.builder()
                .phoneNumber("")
                .email("adesuyiololade@gmail.com")
                .AccountName(savedCustomer.getCustomerName())
                .age(savedCustomer.getCustomerAge())
                .gender(savedCustomer.getCustomerGender())
                .customerId(savedCustomer.getCustomerId())
                .build();
        savedAccount = customerService.openAccount(openAccountRequest);
    }

    @AfterEach
    void tearDown() {

        customerService.deleteAll();
        //  customerService.deleteAllAccount();
        customerService.deleteAllAccounts();

    }

    @Test
    public void customerCanBeTested() {
        Customer customer = new Customer();
        customer.setCustomerName("Adesuyi");
        customer.setCustomerAge("12");
        customer.setCustomerGender("Female");
        assertEquals("Adesuyi", customer.getCustomerName());

    }

    @Test
    public void customerCanBeRegister() {
        CustomerRegisterRequest customerRegisterRequest = new CustomerRegisterRequest();
        customerRegisterRequest.setCustomerName("Adesuyi");
        customerRegisterRequest.setCustomerGender("female");
        customerRegisterRequest.setCustomerAge("55");
        Customer customer = customerService.saveNewCustomer(customerRegisterRequest);
        assertThat(customer.getCustomerId()).isNotNull();
        assertEquals(2, customerService.totalNumberOfCustomer());
        System.out.println(customer.getCustomerId());

    }

    @Test
    public void findCustomerById() {
        Customer foundCustomer = customerService.findCustomerById(savedCustomer.getCustomerId());
        assertThat(foundCustomer).isNotNull();
        assertThat(foundCustomer.getCustomerId()).isEqualTo(savedCustomer.getCustomerId());

    }

    @Test
    public void findAllCustomer() {
        customerService.findAllCustomers();
        assertEquals("Adesuyi", customerService.findAllCustomers().get(0).getCustomerName());
        assertEquals("55", customerService.findAllCustomers().get(0).getCustomerAge());
    }

    @Test
    public void deleteCustomerById() {
        DeleteCustomerRequest deleteCustomerRequest = new DeleteCustomerRequest();
        deleteCustomerRequest.setCustomerId(savedCustomer.getCustomerId());
        customerService.deleteCustomersById(deleteCustomerRequest);
        assertEquals(0, customerService.totalNumberOfCustomer());

    }

    @Test
    public void findAllCustomerCanBeDeleted() {
        customerService.deleteAll();
        assertEquals(0, customerService.totalNumberOfCustomer());
    }

    @Test
    public void findAllCustomerProfileCanBeUpdated() {
        UpdateCustomerProfileRequest updateCustomerProfileRequest = UpdateCustomerProfileRequest.builder()
                .customerName("Ololade")
                .customerGender("transgender")
                .customerAge("28")
                .build();
        updateCustomerProfileRequest.setCustomerId(savedCustomer.getCustomerId());
        customerService.updateCustomerProfile(updateCustomerProfileRequest);
        assertEquals("28", customerService.findAllCustomers().get(0).getCustomerAge());
        assertEquals("Ololade", customerService.findAllCustomers().get(0).getCustomerName());
        assertEquals("transgender", customerService.findAllCustomers().get(0).getCustomerGender());
    }


    @Test
    public void CustomerCanOpenAccount() {
        OpenAccountRequest openAccountRequest = OpenAccountRequest.builder()
                .phoneNumber("08109093828")
                .email("adesuyiololade@gmail.com")
                .AccountName(savedCustomer.getCustomerName())
                .age(savedCustomer.getCustomerAge())
                .gender(savedCustomer.getCustomerGender())
                .customerId(savedCustomer.getCustomerId())
                .build();
        OpenAccountResponse savedAccount = customerService.openAccount(openAccountRequest);
        assertThat(savedAccount).isNotNull();
        assertEquals(2, customerService.totalNumberOfAccount());

    }

    @Test
    void customerCanDeleteAllAccounts() {
        customerService.deleteAllAccounts();
        assertEquals(0, customerService.totalNumberOfAccount());
    }

    @Test
    void customerCanFindAccountById() {
        FindAccountRequest findAccountRequest = FindAccountRequest
                .builder()
                .accoundId(savedAccount.getId())
                .customerId(savedCustomer.getCustomerId())
                .build();
        Account foundAccount = customerService.findAccountById(findAccountRequest);
        assertThat(foundAccount.getId()).isEqualTo(savedAccount.getId());
        System.out.println(foundAccount);
        System.out.println(savedAccount);
    }

    @Test
    public void testThatCustomerCanFindAllAccounts() {
        FindAllAccountRequest findAllAccountRequest = FindAllAccountRequest.builder()
                .customerId(savedCustomer.getCustomerId())
                .accountId(savedAccount.getId())
                .build();
        List<Account> foundAccounts = customerService.findAllAccounts(findAllAccountRequest);
        assertEquals("Adesuyi", customerService.findAllAccounts(findAllAccountRequest).get(0).getAccountName());
        assertEquals("female", customerService.findAllAccounts(findAllAccountRequest).get(0).getGender());
        assertThat(foundAccounts.get(0).getId()).isEqualTo(savedAccount.getId());
    }


    @Test
    public void testThatCustomerCanDeleteAccountById() {
        DeleteAccountRequest deleteAccountRequest = DeleteAccountRequest.builder()
                .accountId(savedAccount.getId())
                .customerId(savedCustomer.getCustomerId())
                .build();
        customerService.deleteAccountById(deleteAccountRequest);
        assertEquals(0, customerService.totalNumberOfAccount());
    }

    @Test
    public void customerCanTransfertFundToAnotherCustomerAccount_BeneficairyBalanceIncreases(){
        DepositFundRequest depositFundRequest = DepositFundRequest.builder()
                        .depositFunds(BigDecimal.valueOf(3000))
                .currentBalance(BigDecimal.valueOf(10000))
                .beneficiaryAccount(savedAccount.getAccountNumber())
                .senderAccountNumber("12345")
                .pin(1234)
                .customerId(savedCustomer.getCustomerId())
                .accountId(savedAccount.getId())
                                                .build();
        DepositFundResponse depositFundResponse = customerService.depositFunds(depositFundRequest);
        assertEquals("Fund sucessfully deposited", depositFundResponse.getMessage());
        assertEquals(BigDecimal.valueOf(13000), depositFundResponse.getCurrentBalance());

    }
    @Test
    public void customerCanTransfetFundToAnotherCustomerAccount_SenderBalanceDecrease(){
        WithdrawalFundRequest withdrawalFundRequest = WithdrawalFundRequest
                .builder()
                .withdrawalAmount(BigDecimal.valueOf(10000))
                .currentBalance(BigDecimal.valueOf(100000))
                .senderAccountNumber(savedAccount.getAccountNumber())
                .pin(1234)
                .build();
        WithdrawalFundResponse withdrawalFundResponse = customerService.WithdrawFund(withdrawalFundRequest);
        assertEquals(BigDecimal.valueOf(90000), withdrawalFundResponse.getCurrentBalance());

    }


}