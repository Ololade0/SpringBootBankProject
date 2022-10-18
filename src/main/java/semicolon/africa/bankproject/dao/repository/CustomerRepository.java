package semicolon.africa.bankproject.dao.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import semicolon.africa.bankproject.dao.model.Customer;


public interface CustomerRepository extends MongoRepository<Customer, String> {

    Customer findCustomerByCustomerId(String id);
    Customer findCustomerByCustomerAccountNumber(String customerAccountNumber);

}