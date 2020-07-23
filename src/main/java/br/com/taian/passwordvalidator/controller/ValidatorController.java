package br.com.taian.passwordvalidator.controller;

import br.com.taian.passwordvalidator.service.ValidatorService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value="Validator Controller",tags = {"password-validator"})
@RestController
@RequestMapping("/validator")
public class ValidatorController {

    @Autowired
    ValidatorService validatorService;

    @GetMapping("/v1.0")
    public ResponseEntity<Boolean> isValid(@RequestParam String password){
        Boolean response = validatorService.isValid(password);
        return ResponseEntity.ok(response);
    }

}
