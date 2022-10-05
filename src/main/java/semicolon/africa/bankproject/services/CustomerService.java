package semicolon.africa.bankproject.services;

import semicolon.africa.bankproject.dao.model.Customer;
import semicolon.africa.bankproject.dto.request.CustomerRegisterRequest;
import semicolon.africa.bankproject.dto.request.UpdateCustomerProfileRequest;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer saveNewCustomer(CustomerRegisterRequest customerRegisterRequest);



    Optional<Customer> findById(Long customerId);

    Customer findCustomerById(Long customerId);

    List<Customer> findAllCustomers();

    long totalNumberOfCustomer();

    void deleteAll();

    void deleteCustomer(Long customerId);

    Customer updateCustomerProfile(UpdateCustomerProfileRequest updateCustomerProfileRequest);
}