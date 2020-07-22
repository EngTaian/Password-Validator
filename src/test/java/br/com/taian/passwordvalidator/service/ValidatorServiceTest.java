package br.com.taian.passwordvalidator.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ValidatorServiceTest {

    @Autowired
    ValidatorService validatorService;


    @Test
    public void isValidAndShouldReturnTrueWithCorrectPassword(){
        boolean response = validatorService.isValid("AbTp9!fok");
        Assertions.assertThat(response).isTrue();
    }

    @Test
     public void isValidAndSouldReturnFalseWhenReceivingStringLengthLessThanNine(){
        boolean response = validatorService.isValid("abcdefgh ");
        Assertions.assertThat(response).isFalse();
    }

    @Test
    public void isValidAndShouldReturnFalseWhenReceivingNull(){
        boolean response = validatorService.isValid(null);
        Assertions.assertThat(response).isFalse();
    }

    @Test
    public void isValidAndShouldReturnFalseWhenReceivingEmptyString(){
        boolean response = validatorService.isValid("");
        Assertions.assertThat(response).isFalse();
    }

    @Test
    public void isValidAndShouldReturnFalseWhenReceivingRepectedChar(){
        boolean response = validatorService.isValid("AbTp9foçç");
        Assertions.assertThat(response).isFalse();
    }

    @Test
    public void isValidAndShouldReturnFalseWhenReceivingRepectedSpecialChar(){
        boolean response = validatorService.isValid("AbTp9!&&foo");
        Assertions.assertThat(response).isFalse();
    }
}
