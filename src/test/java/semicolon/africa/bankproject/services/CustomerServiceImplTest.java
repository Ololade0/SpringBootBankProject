package semicolon.africa.bankproject.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import semicolon.africa.bankproject.dao.model.Customer;
import semicolon.africa.bankproject.dto.request.CustomerRegisterRequest;

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
    System.out.println( customerService.saveNewCustomer(customerRegister));
       Customer customer =  customerService.saveNewCustomer(customerRegister);
       assertThat(customer).isNotNull();

}
@Test
    public void findCustomerById(){
       Customer foundCustomer = customerService.findCustomerById(savedCustomer.getCustomerId());
        assertThat(savedCustomer.getCustomerId()).isGreaterThan(0);
        assertThat(savedCustomer).isNotNull();
    assertThat(foundCustomer.getCustomerId()).isEqualTo(savedCustomer.getCustomerId());
    System.out.println( customerService.findCustomerById(savedCustomer.getCustomerId()));
}

    @Test
    public void findAllCustomer(){
        customerService.findAllCustomers();
        assertEquals("Adesuyi", customerService.findAllCustomers().get(0).getCustomerName());
        assertEquals("55",customerService.findAllCustomers().get(0).getCustomerAge());
    }


}