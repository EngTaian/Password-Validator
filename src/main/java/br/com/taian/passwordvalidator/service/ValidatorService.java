package br.com.taian.passwordvalidator.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class ValidatorService {

    private static final String CHARACTER_PATTERN = "(?=.*[!@#$%^&*()-+])(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])";

    /**
     * THIS IS THE MAIN FUNCTION OF THE VALIDATE PASSWORD
     * */
    public boolean isValid(String password){

        /**
         * VERIFY IF PASSWORD IS EMPTY OR NULL
         * */
        if(ObjectUtils.isEmpty(password)) {
            log.error("Password is null or empty");
            return false;
        }

        /**
         * REMOVING SPACES AND VERIFY IF OR THE LENGTH IS LESS THAN 9
         * */
        password = password.replace(" ", "");
        if (password.length() < 9){
            log.error("The length is less than 9");
            return false;
        }

        /**
         * VERIFY REPECTED CHARACTERS
         * */
        if(repeatedValues(password)){
            log.error("Password has repeated characters");
            return false;
        }

        /**
         * VERIFY PATTERN OF STRING
         * */
        if(!validCharacters(password)){
            log.error("Password not respect the pattern");
            return false;
        }
        return true;
    }

    /**
     * THIS FUNCTION VALID REPECTIVE CHARACTERS IN THE PASSWORD
     * */
    private boolean repeatedValues(String password){
       char[] passwordChar = password.toLowerCase().toCharArray();
       boolean result = false;
       Arrays.sort(passwordChar);
        for(int i = 0; i< passwordChar.length-1; i++){
            if(passwordChar[i] == passwordChar[i+1]){
                result = true;
                break;
            }
        }
       return  result;
    }

    /**
     * THIS FUNCTION VALID THE PATTERN OF THE PASSWORD
     * */
    private boolean validCharacters(String password){
        Pattern pattern = Pattern.compile(CHARACTER_PATTERN);
        Matcher matcher = pattern.matcher(password);
        if(!matcher.find()) {
            return false;
        }
        return true;
    }
}
