package semicolon.africa.bankproject.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import semicolon.africa.bankproject.dao.model.Bank;
import semicolon.africa.bankproject.dao.model.Customer;
import semicolon.africa.bankproject.dao.repository.BankRepository;
import semicolon.africa.bankproject.dto.request.BankRegisterRequest;
import semicolon.africa.bankproject.dto.request.CustomerRegisterRequest;
import semicolon.africa.bankproject.dto.response.BankRegisterResponse;
import semicolon.africa.bankproject.dto.response.CustomerRegisterResponse;



@Service
@AllArgsConstructor
//@Transactional
public class BankServiceImpl implements BankService{
    @Autowired
    private final BankRepository bankRepository;
    @Autowired
    private final CustomerService customerService;


    @Override
    public BankRegisterResponse registerBank(BankRegisterRequest bankRegisterRequest) {
        Bank bank = new Bank();
              bank.setBankLocation(bankRegisterRequest.getBanklocation());
              bank.setBankName(bankRegisterRequest.getBankName());
       Bank savedBank = bankRepository.save(bank);
       BankRegisterResponse bankRegisterResponse = new BankRegisterResponse();
       bankRegisterResponse.setMessage("Bank successfully registered");
       bankRegisterResponse.setBankId(savedBank.getId());
        return bankRegisterResponse;
    }

    @Override
    public BankRegisterResponse addNewBank(BankRegisterRequest bankRegisterRequest1) {
                Bank newBank = Bank.builder().bankName(bankRegisterRequest1.getBankName())
                .bankLocation(bankRegisterRequest1.getBanklocation())
                .build();
        Bank savedBank1= bankRepository.save(newBank);
        BankRegisterResponse bankRegisterResponse = new BankRegisterResponse();
        bankRegisterResponse.setMessage("Another newBank successfully registered");
        bankRegisterResponse.setBankId(savedBank1.getId());
        return bankRegisterResponse;

    }

    @Override
    public CustomerRegisterResponse registerCustomer(CustomerRegisterRequest customerRegister) {
        Customer newCustomer = Customer.builder().
                customerName(customerRegister.getCustomerName())
                .customerGender(customerRegister.getCustomerGender())
                .customerAge(customerRegister.getCustomerAge())
                .bankId(customerRegister.getBankId())
                .build();
        Bank foundBank = bankRepository.findBankById(customerRegister.getBankId());
        if(foundBank != null) {
            foundBank.getCustomers().add(newCustomer);
            customerService.saveNewCustomer(newCustomer);
            bankRepository.save(foundBank);
        }
        CustomerRegisterResponse customerRegisterResponse = new CustomerRegisterResponse();
        customerRegisterResponse.setMessage("CUstomer successfully registered");
       // customerRegisterResponse.setCustomerId(bank.getCustomerId());
        return customerRegisterResponse;
    }

   }

