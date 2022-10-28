package semicolon.africa.bankproject.dao.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import semicolon.africa.bankproject.dao.model.Customer;

import java.util.Optional;


public interface CustomerRepository extends MongoRepository<Customer, String> {

    Customer findCustomerByCustomerId(String id);
    Optional<Customer> findCustomerByCustomerEmail(String email);
    Customer findCustomerByCustomerAccountNumber(String customerAccountNumber);

}