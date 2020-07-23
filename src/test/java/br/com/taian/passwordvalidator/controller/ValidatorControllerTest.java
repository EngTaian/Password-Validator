package br.com.taian.passwordvalidator.controller;

import br.com.taian.passwordvalidator.PasswordValidatorApplication;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {PasswordValidatorApplication.class})
public class ValidatorControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void isValidAndShouldReturnTrueWhenReceivingCorrectFormOfPassword(){
        ResponseEntity<Boolean> response =testRestTemplate.getForEntity("/validator/v1.0?password=AbTp9!fok", Boolean.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isTrue();
    }

    @Test
    public void isValidAndShouldReturnTrueWhenReceivingCorrectFormOfPasswordWithMoreThanOneSpecialCharacter(){
        ResponseEntity<Boolean> response =testRestTemplate.getForEntity("/validator/v1.0?password=AbTp9!fok@", Boolean.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isTrue();
    }

    @Test
    public void isValidAndShouldReturnTrueWhenReceivingCorrectFormOfPasswordWithMoreThanOneNumber(){
        ResponseEntity<Boolean> response =testRestTemplate.getForEntity("/validator/v1.0?password=AbTp9!fok1", Boolean.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isTrue();
    }

    @Test
    public void isValidAndShouldReturnTrueWhenReceivingNull(){
        ResponseEntity<Boolean> response =testRestTemplate.getForEntity("/validator/v1.0?password", Boolean.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isFalse();
    }

    @Test
    public void isValidAndShouldReturnFalseWhenReceivingEmptyPassword(){
        ResponseEntity<Boolean> response =testRestTemplate.getForEntity("/validator/v1.0?password=", Boolean.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isFalse();
    }

    @Test
    public void isValidAndShouldReturnFalseWhenReceivingPasswordLengthLessThanNine(){
        ResponseEntity<Boolean> response =testRestTemplate.getForEntity("/validator/v1.0?password=AbTp9fok", Boolean.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isFalse();
    }

    @Test
    public void isValidAndShouldReturnFalseWhenReceivingPasswordWithSpaces(){
        ResponseEntity<Boolean> response = testRestTemplate.getForEntity("/validator/v1.0?password=AbTp9  fok", Boolean.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isFalse();
    }

    @Test
    public void isValidAndShouldReturnFalseWhenReceivingPasswordWithOutLowerCase(){
        ResponseEntity<Boolean> response = testRestTemplate.getForEntity("/validator/v1.0?password=ABTP9!FOK", Boolean.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isFalse();
    }

    @Test
    public void isValidAndShouldReturnFalseWhenReceivingPasswordWithOutUpperCase(){
        ResponseEntity<Boolean> response = testRestTemplate.getForEntity("/validator/v1.0?password=abtp9!fok", Boolean.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isFalse();
    }

    @Test
    public void isValidAndShouldReturnFalseWhenReceivingPasswordWithOutNumber(){
        ResponseEntity<Boolean> response = testRestTemplate.getForEntity("/validator/v1.0?password=abtp@!fok", Boolean.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isFalse();
    }

    @Test
    public void isValidAndShouldReturnFalseWhenReceivingPasswordWithOutSpecialCharacter(){
        ResponseEntity<Boolean> response = testRestTemplate.getForEntity("/validator/v1.0?password=AbTp92fok", Boolean.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isFalse();
    }

    @Test
    public void isValidAndShouldReturnFalseWhenReceivingPasswordNumbersOnly(){
        ResponseEntity<Boolean> response = testRestTemplate.getForEntity("/validator/v1.0?password=1234567890", Boolean.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isFalse();
    }

    @Test
    public void isValidAndShouldReturnFalseWhenReceivingPasswordLettersOnly(){
        ResponseEntity<Boolean> response = testRestTemplate.getForEntity("/validator/v1.0?password=AbcdefGHIJ", Boolean.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isFalse();
    }

    @Test
    public void isValidAndShouldReturnFalseWhenReceivingPasswordRepectedLetters(){
        ResponseEntity<Boolean> response = testRestTemplate.getForEntity("/validator/v1.0?password=AbTp9!foka", Boolean.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isFalse();
    }

    @Test
    public void isValidAndShouldReturnFalseWhenReceivingPasswordRepectedNumbers(){
        ResponseEntity<Boolean> response = testRestTemplate.getForEntity("/validator/v1.0?password=AbTp9!fok9", Boolean.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isFalse();
    }

    @Test
    public void isValidAndShouldReturnFalseWhenReceivingPasswordRepectedSpecialCharacters(){
        ResponseEntity<Boolean> response = testRestTemplate.getForEntity("/validator/v1.0?password=AbTp9!fok!", Boolean.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isFalse();
    }
}
