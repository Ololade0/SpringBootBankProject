package semicolon.africa.bankproject.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import semicolon.africa.bankproject.dao.model.Customer;
import semicolon.africa.bankproject.dao.repository.CustomerRepository;
import semicolon.africa.bankproject.dto.request.CustomerRegisterRequest;
import semicolon.africa.bankproject.dto.request.UpdateCustomerProfileRequest;

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

    @Override
    public long totalNumberOfCustomer() {
        return customerRepository.count();
    }

    @Override
    public void deleteAll() {
        customerRepository.deleteAll();

    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);

    }

    @Override
    public Customer updateCustomerProfile(UpdateCustomerProfileRequest updateCustomerProfileRequest) {
        Customer foundCustomer = customerRepository.findCustomerByCustomerId(updateCustomerProfileRequest.getCustomerId());
                if(updateCustomerProfileRequest.getCustomerName() != null){
                    foundCustomer.setCustomerName(updateCustomerProfileRequest.getCustomerName());
           }

                if(updateCustomerProfileRequest.getCustomerAge() != null){
                foundCustomer.setCustomerAge(updateCustomerProfileRequest.getCustomerAge());
        }

              if(updateCustomerProfileRequest.getCustomerGender() != null){
            foundCustomer.setCustomerGender(updateCustomerProfileRequest.getCustomerGender());
        }

          return  customerRepository.save(foundCustomer);
    }
}

