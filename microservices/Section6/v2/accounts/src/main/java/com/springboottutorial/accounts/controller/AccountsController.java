package com.springboottutorial.accounts.controller;

import com.springboottutorial.accounts.constants.Accountsconstants;
import com.springboottutorial.accounts.dto.AccountsContactInfoDto;
import com.springboottutorial.accounts.dto.CustomerDto;
import com.springboottutorial.accounts.dto.ErrorResponseDto;
import com.springboottutorial.accounts.dto.ResponseDto;
import com.springboottutorial.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST ApIs for the Accounts in Sample Bank",
        description = "This API has seperate end points for create, fetch, update and delete"
)
@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})

@Validated
public class AccountsController {

    private final IAccountsService iAccountsService;

    public AccountsController(IAccountsService iAccountsService) {
        this.iAccountsService = iAccountsService;
    }

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment env;

    @Autowired
    private AccountsContactInfoDto accountsContactInfoDto;

    @Operation(
            summary="Create Account REST API",
            description="This API is used to create a new account and customer records"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Account created successfully"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){

        iAccountsService.createAccount(customerDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(Accountsconstants.STATUS_201, Accountsconstants.MESSAGE_201));

    }


    @Operation(
            summary="Fetch Customer and Account Data REST API",
            description="This API is used to fetch data for account and customer based on mobile number"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Record retrieved successfully"
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchCustomerDetails(@RequestParam
                                                                @Pattern(regexp = "[0-9]{10}", message = "Mobile number should be 10 digits")
                                                                String mobileNumber){
        CustomerDto customerDto = iAccountsService.findCustomerByMobileNumber(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerDto);
    }

    @Operation(
            summary="Update Customer and Account REST API",
            description="This API is used to update an existing account and customer record"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Data updated successfully"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Exception - Failed to update"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content
                            (
                                    schema = @Schema(
                                            implementation = ErrorResponseDto.class
                                    )
                            )
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto){

        boolean isUpdated = iAccountsService.updateCustomer(customerDto);

        if(isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(Accountsconstants.STATUS_200, Accountsconstants.MESSAGE_200));

        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(Accountsconstants.STATUS_417, Accountsconstants.MESSAGE_417_UPDATE));

        }
    }

    @Operation(
            summary="Delete Customer and Account REST API",
            description="This API is used to delete an existing customer record and the corresponding account record"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Data deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Exception - Failed to delete"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error"
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCustomer(@RequestParam
                                                          @Pattern(regexp = "[0-9]{10}", message = "Mobile number should be 10 digits")
                                                          String mobileNumber){

        boolean isDeleted = iAccountsService.deleteCustomer(mobileNumber);

        if(isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(Accountsconstants.STATUS_200, Accountsconstants.MESSAGE_200));

        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(Accountsconstants.STATUS_417, Accountsconstants.MESSAGE_417_DELETE));

        }
    }
    @Operation(
            summary="Fetch Build Information",
            description="This API is used to fetch information related to the buildr"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Data deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error"
            )
    })
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo(){
        return ResponseEntity.
                status(HttpStatus.OK).
                body(buildVersion);
    }

    @Operation(
            summary="Fetch Build Information",
            description="This API is used to fetch information related to the buildr"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Data deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error"
            )
    })
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion(){
        return ResponseEntity.
                status(HttpStatus.OK).
                body(env.getProperty("JAVA_HOME"));
    }

    @Operation(
            summary="Fetch Contact Information",
            description="This API is used to fetch contact details for the API"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Data deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error"
            )
    })
    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfoDto> getContactInfo(){
        return ResponseEntity.
                status(HttpStatus.OK).
                body(accountsContactInfoDto);
    }

}

