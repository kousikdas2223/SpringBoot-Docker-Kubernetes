package com.springboottutorial.cards.controller;

import com.springboottutorial.cards.constants.CardsConstants;
import com.springboottutorial.cards.dto.CardsContactInfoDto;
import com.springboottutorial.cards.dto.CardsDto;
import com.springboottutorial.cards.dto.ResponseDto;
import com.springboottutorial.cards.service.ICardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Cards", description = "The Cards API")
@RestController
@RequestMapping(path = "/api", produces ={ MediaType.APPLICATION_JSON_VALUE})
@Validated

public class CardsController {

    ICardsService cardsService;

    public CardsController(ICardsService cardsService) {
        this.cardsService = cardsService;
    }

//    @Value("${build.version}")
//    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    private CardsContactInfoDto cardsContactInfoDto;

    private static final Logger logger = LoggerFactory.getLogger(CardsController.class);


    @Operation(summary = "Create Cards", description = "Create Cards Record")
    @ApiResponse(responseCode = "201", description = "Created")
    @PostMapping("/createCards")
    public ResponseEntity<ResponseDto> createCards(@Valid @RequestParam
                                                       @Pattern(regexp = "[0-9]{10}", message = "Mobile number should be 10 digits")
                                                       String mobileNumber) {

        cardsService.createCards(mobileNumber);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));

    }

    @Operation(summary = "Get Cards", description = "Get Cards Record")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("/getCards")
    public ResponseEntity<CardsDto> getCards(@Valid
                                                 @RequestHeader("my_bank_correlation_id") String correlationId,
                                                 @RequestParam
                                                 @Pattern(regexp = "[0-9]{10}", message = "Mobile number should be 10 digits")
                                                 String mobileNumber) {

        logger.debug("fetchCardsrDetails method started");
        CardsDto cardsDto = cardsService.getCards(mobileNumber);
        logger.debug("fetchCardsrDetails method completed");
        if(cardsDto != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(cardsDto);
        }

        return null;
    }

    @Operation(summary = "Update Cards", description = "Update Cards Record")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "417", description = "Expectation Failed")
    })
    @PutMapping("/updateCards")
    public ResponseEntity<ResponseDto> updateCards(@Valid @RequestBody CardsDto cardsDto){

        System.out.println(cardsDto);

        boolean isUpdated = cardsService.updateCards(cardsDto);


        if(isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        }

        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_UPDATE));

    }

    @Operation(summary = "Delete Cards", description = "Delete Cards Record")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "417", description = "Expectation Failed")
    })
    @DeleteMapping("/deleteCards")
    public ResponseEntity<ResponseDto> deleteCards(@Valid @RequestParam
                                                       @Pattern(regexp = "[0-9]{10}", message = "Mobile number should be 10 digits")
                                                       String mobileNumber) {

        boolean isDeleted = cardsService.deleteCards(mobileNumber);

        if(isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        }

        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_DELETE));
    }

//    @Operation(
//            summary = "Cards Service Build Information",
//            description = "Get Build Information for Cards Service"
//    )
//    @ApiResponses(
//            {
//                    @ApiResponse(
//                            responseCode = "200",
//                            description = "OK"
//                    ),
//                    @ApiResponse(
//                            responseCode = "500",
//                            description = "Internal Server Error"
//                    )
//    })
//    @GetMapping("/cards-build-info")
//    public ResponseEntity<String> getBuildInfo(){
//        return ResponseEntity.
//                status(HttpStatus.OK).
//                body(buildVersion);
//    }

    @Operation(
            summary = "Cards Service JDK Information",
            description = "Get JDK Information for Cards Service"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error"
                    )
            })
    @GetMapping("/cards-java-version")
    public ResponseEntity<String> getJavaVersion(){
        return ResponseEntity.
                status(HttpStatus.OK).
                body(environment.getProperty("JAVA_HOME"));
    }

    @Operation(
            summary = "Cards Service Contact Information",
            description = "Get Contact Information for Cards Service"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error"
                    )
            })
    @GetMapping("/cards-contact-info")
    public ResponseEntity<CardsContactInfoDto> getcontactInfo(){
        return ResponseEntity.
                status(HttpStatus.OK).
                body(cardsContactInfoDto);
    }

}
