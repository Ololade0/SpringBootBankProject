package semicolon.africa.bankproject.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import semicolon.africa.bankproject.dao.model.Customer;
import semicolon.africa.bankproject.dao.repository.CustomerRepository;
import semicolon.africa.bankproject.dto.request.CustomerRegisterRequest;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer saveNewCustomer(CustomerRegisterRequest customerRegister) {
        Customer newCustomer = Customer.builder().
                customerName(customerRegister.getCustomerName())
                .customerGender(customerRegister.getCustomerGender())
                .customerAge(customerRegister.getCustomerAge())
                .build();
        return customerRepository.save(newCustomer);

    }


    @Override
    public Optional<Customer> findById(Long customerId) {
        return Optional.ofNullable(customerRepository.findCustomerByCustomerId(customerId));
    }

    @Override
    public Customer findCustomerById(Long customerId) {
        return customerRepository.findCustomerByCustomerId(customerId);

    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }
}

