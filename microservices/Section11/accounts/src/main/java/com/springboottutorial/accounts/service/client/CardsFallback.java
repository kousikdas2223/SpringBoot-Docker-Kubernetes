package com.springboottutorial.accounts.service.client;

import com.springboottutorial.accounts.dto.CardsDto;
import com.springboottutorial.accounts.dto.LoansDto;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallback implements CardsFeignClient{

    @Override
    public ResponseEntity<CardsDto> fetchCardsDetails(String mobileNumber, String correlationId) {
        return null;
    }
}
