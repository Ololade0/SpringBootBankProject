package semicolon.africa.bankproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import semicolon.africa.bankproject.dao.model.Account;
import semicolon.africa.bankproject.dao.model.Customer;
import semicolon.africa.bankproject.dao.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
        @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Customer saveNewCustomers(Customer newCustomer) {
        return customerRepository.save(newCustomer);
    }

//    @Override
//    public Customer saveNewCustomers(Customer newCustomer) {
//        return customerRepository.save(newCustomer);
//
//   }
//
//    @Override
//    public long totalNUmbersOfCustomers() {
//        return customerRepository.count();
//    }
//
//    @Override
//    public Customer saveNewCustomer(Customer newCustomer) {
//        return customerRepository.save(newCustomer);
//    }


    }

