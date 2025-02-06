package com.springboottutorial.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data @AllArgsConstructor
@Schema(
        name="ErrorResponseDto",
        description = "This object holds the details of the error generated from the opretaions"
)
public class ErrorResponseDto {

    @Schema(
            name="API Path",
            description = "Holds the api path value"
    )
    private String apiPath;

    @Schema(
            name="Error Code",
            description = "Holds the error code value"
    )
    private HttpStatus errorCode;

    @Schema(
            name="Error Message",
            description = "Holds the error message"
    )
    private String errorMessage;

    @Schema(
            name="Date Time",
            description = "Holds the date time when the error was generated"
    )
    private LocalDateTime errorTime;

}
