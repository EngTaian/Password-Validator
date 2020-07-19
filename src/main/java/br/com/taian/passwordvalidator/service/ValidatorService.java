package br.com.taian.passwordvalidator.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Slf4j
@Service
public class ValidatorService {


    public boolean isValid(String password){

        /**
         * VERIFY IF PASSWORD IS EMPTY
         * */
        if(ObjectUtils.isEmpty(password)) {
            log.error("Password is null or empty");
            return false;
        }
        /**
         * REMOVING SPACES AND VERIFY IF OR THE LENGTH IS LESS THAN 9
         * */
        password = password.trim();
        if (password.length() < 9){
            log.error("The length is less than 9");
            return false;
        }

        return true;
    }


}
