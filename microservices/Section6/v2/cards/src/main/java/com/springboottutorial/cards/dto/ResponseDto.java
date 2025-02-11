package com.springboottutorial.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter@Setter@AllArgsConstructor
@Schema(name = "Response Data transfer object", description = "Holds the response code and the message")
public class ResponseDto {

    @Schema(name = "Status", description = "Status of the response")
    private String status;

    @Schema(name = "Message", description = "Message of the response")
    private String message;
}
