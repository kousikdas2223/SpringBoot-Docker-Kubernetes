package com.springboottutorial.loans.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
@Schema(
        name="Response Data Object",
        description = "This object holds the response data"
)
public class ResponseDto {

    @Schema(
            name="Status Code",
            description = "Status codes are as follows: 200 - OK, 201 - Created, 400 - Bad Request, 417 - Exception,  500 - Internal Server Error"
    )

    private String statusCode;

    @Schema(
            name="Status Message",
            description = "This field holds the status message"
    )
    private String statusMessage;

}
