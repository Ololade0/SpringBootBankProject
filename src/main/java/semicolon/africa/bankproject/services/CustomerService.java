package semicolon.africa.bankproject.services;

import semicolon.africa.bankproject.dao.model.Customer;

public interface CustomerService {
    Customer saveNewCustomer(Customer newCustomer);
}