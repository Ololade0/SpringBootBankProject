package semicolon.africa.bankproject.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import semicolon.africa.bankproject.dao.model.Customer;
import semicolon.africa.bankproject.dto.request.CustomerRegisterRequest;
import semicolon.africa.bankproject.dto.request.UpdateCustomerProfileRequest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CustomerServiceImplTest {
    @Autowired
    private CustomerService customerService;
    Customer savedCustomer;

    @BeforeEach
    void setUp() {
        CustomerRegisterRequest customerRegister = new CustomerRegisterRequest();
        customerRegister.setCustomerName("Adesuyi");
        customerRegister.setCustomerGender("female");
        customerRegister.setCustomerAge("55");
         savedCustomer =  customerService.saveNewCustomer(customerRegister);
    }

    @AfterEach
    void tearDown() {

        customerService.deleteAll();
    }

    @Test
    public void customerCanBeTested(){
        Customer customer = new Customer();
        customer.setCustomerName("Adesuyi");
        customer.setCustomerAge("12");
        customer.setCustomerGender("Female");
        assertEquals("Adesuyi", customer.getCustomerName());

    }
@Test
     public  void customerCanBeRegister(){
    CustomerRegisterRequest customerRegister = new CustomerRegisterRequest();
       customerRegister.setCustomerName("Adesuyi");
        customerRegister.setCustomerGender("female");
        customerRegister.setCustomerAge("55");
        Customer customer =  customerService.saveNewCustomer(customerRegister);
       assertThat(customer).isNotNull();
       assertEquals(2, customerService.totalNumberOfCustomer());

}
@Test
    public void findCustomerById(){
       Customer foundCustomer = customerService.findCustomerById(savedCustomer.getCustomerId());
       assertThat(foundCustomer.getCustomerId()).isGreaterThan(0);
        assertThat(foundCustomer).isNotNull();
    assertThat(foundCustomer.getCustomerId()).isEqualTo(savedCustomer.getCustomerId());

}

    @Test
    public void findAllCustomer(){
        customerService.findAllCustomers();
        assertEquals("Adesuyi", customerService.findAllCustomers().get(0).getCustomerName());
        assertEquals("55",customerService.findAllCustomers().get(0).getCustomerAge());
    }
    @Test
    public void deleteCustomerById(){
        customerService.deleteCustomer(savedCustomer.getCustomerId());
        assertEquals(0, customerService.totalNumberOfCustomer());

    }

    @Test
    public void findAllCustomerCanBeDeleted(){
        customerService.deleteAll();
        assertEquals(0, customerService.totalNumberOfCustomer());
    }
    @Test
    public void findAllCustomerProfileCanBeUpdated(){
        UpdateCustomerProfileRequest updateCustomerProfileRequest = UpdateCustomerProfileRequest.builder()
                .customerName("Ololade")
                .customerGender("transgender")
                .customerAge("28")
                .build();
        updateCustomerProfileRequest.setCustomerId(savedCustomer.getCustomerId());
        customerService.updateCustomerProfile(updateCustomerProfileRequest);
        assertEquals("28",customerService.findAllCustomers().get(0).getCustomerAge());
        assertEquals("Ololade",customerService.findAllCustomers().get(0).getCustomerName());
        assertEquals("transgender",customerService.findAllCustomers().get(0).getCustomerGender());
    }
}