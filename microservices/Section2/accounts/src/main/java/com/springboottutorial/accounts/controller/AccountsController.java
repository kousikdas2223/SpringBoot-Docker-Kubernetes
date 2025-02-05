package com.springboottutorial.accounts.controller;

import com.springboottutorial.accounts.constants.Accountsconstants;
import com.springboottutorial.accounts.dto.CustomerDto;
import com.springboottutorial.accounts.dto.ResponseDto;
import com.springboottutorial.accounts.service.IAccountsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountsController {

    private IAccountsService iAccountsService;


    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){

        System.out.println("################# Reached the controller #################");
        iAccountsService.createAccount(customerDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(Accountsconstants.STATUS_201, Accountsconstants.MESSAGE_201));

    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchCustomerDetails(@RequestParam
                                                                @Pattern(regexp = "[0-9]{10}", message = "Mobile number should be 10 digits")
                                                                String mobileNumber){
        CustomerDto customerDto = iAccountsService.findCustomerByMobileNumber(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto){

        boolean isUpdated = iAccountsService.updateCustomer(customerDto);

        if(isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(Accountsconstants.STATUS_200, Accountsconstants.MESSAGE_200));

        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(Accountsconstants.STATUS_500, Accountsconstants.MESSAGE_500));

        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCustomer(@RequestParam
                                                          @Pattern(regexp = "[0-9]{10}", message = "Mobile number should be 10 digits")
                                                          String mobileNumber){

        boolean isDeleted = iAccountsService.deleteCustomer(mobileNumber);

        if(isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(Accountsconstants.STATUS_200, Accountsconstants.MESSAGE_200));

        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(Accountsconstants.STATUS_500, Accountsconstants.MESSAGE_500));

        }
    }

}

