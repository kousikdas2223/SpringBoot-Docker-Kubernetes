package com.springboottutorial.accounts.controller;

import com.springboottutorial.accounts.dto.CustomerDetailsDto;
import com.springboottutorial.accounts.dto.ErrorResponseDto;
import com.springboottutorial.accounts.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "CRUD REST ApIs for the Cards in MyBank",
        description = "This API has seperate end points for create, fetch, update and delete"
)
@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class CustomerController {

    private final ICustomerService iCustomerService;

    @Operation(
            summary="Fetch Customer Details",
            description="This API is used to fetchall the details related to a customer based on the mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Data fetched successfully"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Exception - Failed to fetch data"
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
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@Valid @RequestParam
                                                                   @Pattern(regexp = "[0-9]{10}", message = "Mobile number should be 10 digits")
                                                                   String mobileNumber) {

        CustomerDetailsDto customerDetailsDto = iCustomerService.fetchCustomerDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);

    }
}
