package semicolon.africa.bankproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import semicolon.africa.bankproject.dao.model.Account;
import semicolon.africa.bankproject.dao.model.Customer;
import semicolon.africa.bankproject.dto.request.*;
import semicolon.africa.bankproject.dto.response.*;
import semicolon.africa.bankproject.exception.AccountCannotBeFound;
import semicolon.africa.bankproject.exception.BankDoesNotExistException;
import semicolon.africa.bankproject.exception.BankNameAlreadyExistException;
import semicolon.africa.bankproject.exception.CustomerCannotBeFound;
import semicolon.africa.bankproject.services.AccountService;
import semicolon.africa.bankproject.services.BankService;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class BankController {
    @Autowired
    private BankService bankService;

    @Autowired
    private AccountService accountServices;

    @PostMapping("/banks")
    public ResponseEntity<?> registerBank(@RequestBody BankRegisterRequest bankRegisterRequest) {
        try {
            BankRegisterResponse bankRegisterResponse = bankService.registerBank(bankRegisterRequest);
            return new ResponseEntity<>(bankRegisterResponse, HttpStatus.CREATED);
        } catch (BankDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (BankNameAlreadyExistException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findBankById(@PathVariable String id) throws BankDoesNotExistException{
            return new ResponseEntity<>(bankService.findBankById(id), HttpStatus.ACCEPTED);

//        }
    }

    @GetMapping("/all-bank")
    public ResponseEntity<?> findAllBanks(@RequestBody FindAllBankRequest findAllBankRequest) {
        return new ResponseEntity<>(bankService.findAllBanks(findAllBankRequest), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBankById(@PathVariable String id) {
        try {
            String deletedBank = bankService.deleteById(id);
            return new ResponseEntity<>(deletedBank, HttpStatus.ACCEPTED);
        } catch (BankDoesNotExistException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<?> deleteAllBanks() {
        String deletedAllBanks = bankService.deleteAll();
        return new ResponseEntity<>(deletedAllBanks, HttpStatus.ACCEPTED);
    }

    @PutMapping("/bankprofile")
    public ResponseEntity<?> updateBankProfile(@RequestBody UpdateBankRequest updateBankRequest) {
        try {
            UpdateBankResponse updateBankProfile = bankService.updateBankProfile(updateBankRequest);
            return new ResponseEntity<>(updateBankProfile, HttpStatus.ACCEPTED);
        } catch (BankDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping("/customer")
    public ResponseEntity<?> saveCustomers(@RequestBody CustomerRegisterRequest customerRegisterRequest) {
        try {
            CustomerRegisterResponse customerRegisterResponse = bankService.saveCustomer(customerRegisterRequest);
            return new ResponseEntity<>(customerRegisterResponse, HttpStatus.CREATED);
        } catch (CustomerCannotBeFound e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/customerId")
    public ResponseEntity<?> FindCustomerById(@RequestBody FindCustomerRequest findCustomerRequest) {
        try {
            Customer foundCustomer = bankService.findCustomerId(findCustomerRequest);
            return new ResponseEntity<>(foundCustomer, HttpStatus.CREATED);
        } catch (CustomerCannotBeFound e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all-customer")
    public ResponseEntity<?> FindAllCustomers(@RequestBody FindAllCustomerRequest findAllCustomerRequest) {
        try {
            Page<Customer> customers = bankService.findAllCustomers(findAllCustomerRequest);
            return new ResponseEntity<>(customers, HttpStatus.CREATED);
        } catch (CustomerCannotBeFound e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/customers")
    public ResponseEntity<?> DeleteCustomerById(@RequestBody DeleteCustomerRequest deleteCustomerRequest) {
        try {
            String deletedCustomer = bankService.deleteCustomerById(deleteCustomerRequest);
            return new ResponseEntity<>(deletedCustomer, HttpStatus.CREATED);
        } catch (CustomerCannotBeFound e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/all-customers")
    public ResponseEntity<?> DeleteAllCustomers(@RequestBody DeleteAllCustomerRequest deleteAllCustomerRequest) {
        try {
            String deletedAllCustomer = bankService.deleteALLCustomers(deleteAllCustomerRequest);
            return new ResponseEntity<>(deletedAllCustomer, HttpStatus.CREATED);
        } catch (CustomerCannotBeFound e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/customerprofile")
    public ResponseEntity<?> updateCustomerProfile(@RequestBody UpdateCustomerProfileRequest updateCustomerProfileRequest) {
        try {
            UpdateCustomerProfileResponse updateCustomerProfileResponse = bankService.updateCustomerProfile(updateCustomerProfileRequest);
            return new ResponseEntity<>(updateCustomerProfileResponse, HttpStatus.ACCEPTED);
        } catch (CustomerCannotBeFound e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/account")
    public ResponseEntity<?> openAccountForCustomer(@RequestBody OpenAccountRequest openAccountRequest) {
        try {
            OpenAccountResponse openAccountResponse = bankService.openCustomerAccount(openAccountRequest);
            return new ResponseEntity<>(openAccountResponse, HttpStatus.CREATED);
        } catch (AccountCannotBeFound e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping("/accountId")
//    public ResponseEntity<?> FindAccountById(@RequestBody FindAccountRequest findAccountRequest) {
//        try {
//            Account foundAccount = bankService.findAccountById(findAccountRequest);
//            return new ResponseEntity<>(foundAccount, HttpStatus.CREATED);
//        } catch (AccountCannotBeFound e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }

//    @GetMapping("/all-account")
//    public ResponseEntity<?> FindAllAccounts(@RequestBody FindAllAccountRequest findAllAccountRequest) {
//        try {
//            List<Account> accounts = bankService.findAllAccounts(findAllAccountRequest);
//            return new ResponseEntity<>(accounts, HttpStatus.CREATED);
//        } catch (AccountCannotBeFound e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }

    @DeleteMapping("/delete-all-accounts")
    public ResponseEntity<?> deleteAllAccounts() {
        String deletedAllAccounts = bankService.deleteAllAccount();
        return new ResponseEntity<>(deletedAllAccounts, HttpStatus.ACCEPTED);
    }

    @PutMapping("/accountprofile")
    public ResponseEntity<?> updateAccountProfile(@RequestBody UpdateAccountRequest updateAccountRequest) {
        try {
            UpdateAccountResponse updateAccountResponse = bankService.updateAccountProfile(updateAccountRequest);
            return new ResponseEntity<>(updateAccountResponse, HttpStatus.ACCEPTED);
        } catch (AccountCannotBeFound e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

@PostMapping(value = "/account-deposit",
       produces ={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> accountCanDeposit(@RequestBody DepositFundRequest depositFundRequest) {
        try {
            BigDecimal openAccountResponse = accountServices.depositFundsIntoAccount(depositFundRequest);
            return new ResponseEntity<>(openAccountResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }


    }


//    @PostMapping(value = "/withdraw")
////            produces ={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
//    public ResponseEntity<?> accountCanWithdraw(@RequestBody WithdrawalFundRequest withdrawalFundRequest) {
//        try {
//            BigDecimal openAccountResponse = accountServices.WithdrawFundFromAccounts(withdrawalFundRequest);
//            return new ResponseEntity<>(openAccountResponse, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//
//        }
    }






