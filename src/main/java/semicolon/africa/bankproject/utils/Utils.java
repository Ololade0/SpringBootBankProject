package semicolon.africa.bankproject.utils;

import com.fasterxml.jackson.databind.util.ArrayBuilders;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class Utils {
    private final String accountNumber = "1234567890";
    private final Random RANDOM = new SecureRandom();
    public String generateCustomerAccountNumber(int length){
        return generateRandomString(length);
    }

    public String generate(int length){
        return generateRandomString(length);
    }

    private String generateRandomString(int length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length ; i++) {
            stringBuilder.append(accountNumber.charAt(RANDOM.nextInt(accountNumber.length())));
        }
        return new String(stringBuilder);
    }
}
