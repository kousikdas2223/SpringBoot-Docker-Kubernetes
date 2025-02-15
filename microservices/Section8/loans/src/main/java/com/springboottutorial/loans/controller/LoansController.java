package com.springboottutorial.loans.controller;

import com.springboottutorial.loans.constants.LoansConstants;
import com.springboottutorial.loans.dto.LoansAPIContactInfo;
import com.springboottutorial.loans.dto.LoansDto;
import com.springboottutorial.loans.dto.ResponseDto;
import com.springboottutorial.loans.service.ILoansService;
import io.swagger.v3.oas.annotations.Operation;
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
        name="Loans API",
        description = "Interface for Loans API to perform CRUD operations on Loan table"
)
@RestController
@RequestMapping(path= "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class LoansController {

    private ILoansService loansService;
    @Autowired
    private LoansAPIContactInfo loansAPIContactInfo;

    public LoansController(ILoansService loansService) {
        this.loansService = loansService;
    }

//    @Value("${build.version}")
//    private String buildInformation;

    @Autowired
    private Environment env;

    @Operation(
            summary="Create Loan",
            description = "This end point will create a loan record with a mobile number"
    )
    @ApiResponse(
            description = "Record is successfully created",
            responseCode = "200"
    )
    @PostMapping("/createLoan")
    public ResponseEntity<ResponseDto> createLoans(@Valid @RequestParam
                                @Pattern(regexp = "[0-9]{10}", message = "Mobile number should be 10 digits")
                                String mobileNumber) {
        loansService.createLoans(mobileNumber);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));

    }

    @Operation(
            summary="Fetch Loan",
            description = "This end point will fetch loan details based on a mobile number"
    )
    @ApiResponse(
            description = "Record is successfully fetched",
            responseCode = "200"
    )
    @GetMapping("/fetchLoan")
    public ResponseEntity<LoansDto> fetchLoansDetails(@Valid @RequestParam
                                                          @Pattern(regexp = "[0-9]{10}", message = "Mobile number should be 10 digits")
                                                          String mobileNumber) {

        LoansDto loansDetails = loansService.fetchLoansDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(loansDetails);
    }

    @Operation(
            summary="Update Loan",
            description = "This end point will update a loan record"
    )
    @ApiResponses({
            @ApiResponse(
                    description = "Record is successfully updated",
                    responseCode = "200"
            ),
            @ApiResponse(
                    description = "Loan record update process failed",
                    responseCode = "417"
            )

    })
    @PutMapping("/updateLoan")
    public ResponseEntity<ResponseDto> updateLoanDetails(@Valid @RequestBody LoansDto loansDto) {

        boolean isUpdated = loansService.updateLoanDetails(loansDto);

        if(isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary="Delete Loan",
            description = "This end point will delete a loan record"
    )
    @ApiResponses({
            @ApiResponse(
                    description = "Record is successfully deleted",
                    responseCode = "200"
            ),
            @ApiResponse(
                    description = "Loan record deletion process failed",
                    responseCode = "417"
            )

    })
    @DeleteMapping("/deleteLoan")
    public ResponseEntity<ResponseDto> deleteLoanDetails(@Valid @RequestParam
                                     @Pattern(regexp = "[0-9]{10}", message = "Mobile number should be 10 digits")
                                     String mobileNumber) {
        boolean isDeleted = loansService.deleteLoanDetails(mobileNumber);

        if(isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_DELETE));
        }

    }

//    @Operation(
//            summary="Loans API build  Information",
//            description = "This end point will provide build number for loans API"
//    )
//    @ApiResponses({
//            @ApiResponse(
//                    description = "Record is successfully fetched",
//                    responseCode = "200"
//            ),
//            @ApiResponse(
//                    description = "Internal Server Error",
//                    responseCode = "500"
//            )
//
//    })
//    @GetMapping("/loans-build-info")
//    public ResponseEntity<String> getBuildInfo() {
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(buildInformation);
//    }


    @Operation(
            summary="Loans API JDK Information",
            description = "This end point will provide jdk information for loans API"
    )
    @ApiResponses({
            @ApiResponse(
                    description = "Record is successfully fetched",
                    responseCode = "200"
            ),
            @ApiResponse(
                    description = "Internal Server Error",
                    responseCode = "500"
            )

    })
    @GetMapping("/loans-java-version-info")
    public ResponseEntity<String> getJdkInformation() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(env.getProperty("JAVA_HOME"));
    }

    @Operation(
            summary="Loans Contact Information",
            description = "This end point will provide contact information for loans API"
    )
    @ApiResponses({
            @ApiResponse(
                    description = "Record is successfully fetched",
                    responseCode = "200"
            ),
            @ApiResponse(
                    description = "Internal Server Error",
                    responseCode = "500"
            )
    })
    @GetMapping("/loans-contact-info")
    public ResponseEntity<LoansAPIContactInfo> getContactInformation() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(loansAPIContactInfo);
    }

}
