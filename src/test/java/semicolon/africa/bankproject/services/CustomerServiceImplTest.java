package semicolon.africa.bankproject.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import semicolon.africa.bankproject.dao.model.Account;
import semicolon.africa.bankproject.dao.model.Customer;
import semicolon.africa.bankproject.dao.repository.CustomerRepository;
import semicolon.africa.bankproject.dto.request.*;
import semicolon.africa.bankproject.dto.response.DepositFundResponse;
import semicolon.africa.bankproject.dto.response.OpenAccountResponse;
import semicolon.africa.bankproject.dto.response.WithdrawalFundResponse;
import semicolon.africa.bankproject.utils.Utils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CustomerServiceImplTest {
    @Autowired
    private CustomerService customerService;
    @Autowired
    Utils utils;
    Customer savedCustomer;
    OpenAccountResponse savedAccount;

    @BeforeEach
    void setUp() {
        CustomerRegisterRequest customerRegister = new CustomerRegisterRequest();
        customerRegister.setCustomerName("Adesuyi");
        customerRegister.setCustomerGender("female");
        customerRegister.setCustomerEmail("Ololadedemilade@gmail.com");
        customerRegister.setCustomerAccountNumber("34567");
        customerRegister.setCustomerAge("55");
        customerRegister.setCustomerPassword("1235");
        savedCustomer = customerService.saveNewCustomer(customerRegister);

    }

    @AfterEach
    void tearDown() {

        customerService.deleteAll();


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


    }

    @Test
    public void findCustomerById() {
        Customer foundCustomer = customerService.findCustomerById(savedCustomer.getCustomerId());
        assertThat(foundCustomer).isNotNull();
        assertThat(foundCustomer.getCustomerId()).isEqualTo(savedCustomer.getCustomerId());

    }

    @Test
    public void findAllCustomer() {
        FindAllCustomerRequest findAllCustomerRequest = FindAllCustomerRequest.builder()
                .numberOfPages(1)
                .pageNumber(2)
                .build();
      Page<Customer> customerPage=  customerService.findAllCustomers(findAllCustomerRequest);
        assertThat(customerPage).isNotNull();
        assertThat(customerPage.getTotalElements()).isGreaterThan(0);

    }

    @Test
    public void deleteCustomerById() {
        DeleteCustomerRequest deleteCustomerRequest = new DeleteCustomerRequest();
        deleteCustomerRequest.setCustomerId(savedCustomer.getCustomerId());
        customerService.deleteCustomersById(deleteCustomerRequest);
        assertEquals(0, customerService.totalNumberOfCustomer());

    }

    @Test
    public void AllCustomerCanBeDeleted() {
        customerService.deleteAll();
        assertEquals(0, customerService.totalNumberOfCustomer());
    }

    @Test
    public void findAllCustomerProfileCanBeUpdated() {
        UpdateCustomerProfileRequest updateCustomerProfileRequest = UpdateCustomerProfileRequest.builder()
                .customerName("Ololade")
                .customerId(savedCustomer.getCustomerId())
                .customerGender("transgender")
                .customerAge("28")
                .build();
     Customer customer =   customerService.updateCustomerProfile(updateCustomerProfileRequest);
      assertEquals("Ololade", customer.getCustomerName());

    }

//    @Test
//    void customerCanFindAccountById() {
//        FindAccountRequest findAccountRequest = FindAccountRequest
//                .builder()
//                .accoundId(savedAccount.getId())
//                .customerId(savedCustomer.getCustomerId())
//                .build();
//        Account foundAccount = customerService.findAccountById(findAccountRequest);
//        assertThat(foundAccount.getId()).isEqualTo(savedAccount.getId());
//        System.out.println(foundAccount);
//        System.out.println(savedAccount);
//    }

//    @Test
//    public void testThatCustomerCanFindAllAccounts() {
//        FindAllAccountRequest findAllAccountRequest = FindAllAccountRequest.builder()
//                .customerId(savedCustomer.getCustomerId())
//                .accountId(savedAccount.getId())
//                .build();
//        List<Account> foundAccounts = customerService.findAllAccounts(findAllAccountRequest);
//        assertEquals("Adesuyi", customerService.findAllAccounts(findAllAccountRequest).get(0).getAccountName());
//        assertEquals("female", customerService.findAllAccounts(findAllAccountRequest).get(0).getGender());
//        assertThat(foundAccounts.get(0).getId()).isEqualTo(savedAccount.getId());
//    }


    @Test
    public void testThatCustomerCanLogin() {
        LoginRest loginRest = new LoginRest();
//        loginRest.setPassword(savedCustomer.getPassword());
        loginRest.setEmail(savedCustomer.getCustomerEmail());
        var email = customerService.login(loginRest);
        assertEquals(200, email.getCode());
        assertEquals("Login successful", email.getMessage());
    }

    @Test
    public void testThatfindCustomerBYEmail() {
      Customer foundCustomer = customerService.findCustomerByEmail(savedCustomer.getCustomerEmail());
       assertEquals("Ololadedemilade@gmail.com", foundCustomer.getCustomerEmail());

    }

//


}