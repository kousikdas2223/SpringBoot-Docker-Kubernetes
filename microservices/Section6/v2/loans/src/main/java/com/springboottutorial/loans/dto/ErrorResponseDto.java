package com.springboottutorial.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
    name = "Error Response Dto",
    description = "Error Response Dto that holds the error details for the API"
)
public class ErrorResponseDto {

    @Schema(
            name = "API Path",
            description = "API Path"
    )
    private String apiPath;


    @Schema(
            name="Error Code",
            description = "Error Code"
    )
    private HttpStatus errorCode;

    @Schema(
            name="Error Message",
            description = "Error Message"
    )
    private String errorMessage;

    @Schema(
            name="Date Time of the error",
            description = "Date Time")
    private LocalDateTime errorTime;
}