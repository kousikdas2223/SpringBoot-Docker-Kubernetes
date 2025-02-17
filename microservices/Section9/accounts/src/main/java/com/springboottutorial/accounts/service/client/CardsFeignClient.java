package com.springboottutorial.accounts.service.client;

import com.springboottutorial.accounts.dto.CardsDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cards")
public interface CardsFeignClient {

    @GetMapping(value="/api/getCards")
    public ResponseEntity<CardsDto> fetchCardsDetails(@Valid @RequestParam String mobileNumber);

}
