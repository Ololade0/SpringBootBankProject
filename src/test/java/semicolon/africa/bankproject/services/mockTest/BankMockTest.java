package semicolon.africa.bankproject.services.mockTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import semicolon.africa.bankproject.dao.model.Bank;
import semicolon.africa.bankproject.dao.repository.BankRepository;
import semicolon.africa.bankproject.dto.request.BankRegisterRequest;
import semicolon.africa.bankproject.dto.response.BankRegisterResponse;
import semicolon.africa.bankproject.services.AccountService;
import semicolon.africa.bankproject.services.BankService;
import semicolon.africa.bankproject.services.BankServiceImpl;
import semicolon.africa.bankproject.services.CustomerService;



import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BankMockTest {

    @Mock
    private BankRepository bankRepository;
    @Mock
    private CustomerService customerService;
    @Mock
    private AccountService accountService;

    private BankService bankService;

    @BeforeEach
    void setUp() {
        bankService = new BankServiceImpl(bankRepository,customerService,accountService);

    }

    @Test
    public void testCanBeRegister(){
        BankRegisterRequest bankRegisterRequest = new BankRegisterRequest();
        bankRegisterRequest.setBankName("Access Bank");
        bankRegisterRequest.setBanklocation("Ikeja");

        Bank bankToReturn = Bank.builder()
                .id("1234")
                .bankLocation(bankRegisterRequest.getBanklocation())
                .bankName(bankRegisterRequest.getBankName())
                .build();

        when(bankRepository.save(any(Bank.class))).thenReturn(bankToReturn);
        BankRegisterResponse response = bankService.registerBank(bankRegisterRequest);
        System.out.println(response);
        assertThat(response.getBankLocation()).isEqualTo("Ikeja");
        assertThat(response.getMessage()).isEqualTo("Bank successfully registered");
    }

}
