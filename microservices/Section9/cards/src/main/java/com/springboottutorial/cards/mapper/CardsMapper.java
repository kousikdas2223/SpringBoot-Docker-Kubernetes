package com.springboottutorial.cards.mapper;

import com.springboottutorial.cards.dto.CardsDto;
import com.springboottutorial.cards.entity.Cards;

public class CardsMapper {

    public static Cards mapToCards(CardsDto cardsDto, Cards cards) {

        cards.setMobileNumber(cardsDto.getMobileNumber());

        cards.setCardNumber(cardsDto.getCardNumber());

        cards.setCardType(cardsDto.getCardType());

//        cards.setCardLimit(cardsDto.getCardLimit());

        cards.setTotalLimit(cardsDto.getTotalLimit());

        cards.setAmountUsed(cardsDto.getAmountUsed());

        cards.setAvailableAmount(cardsDto.getAvailableAmount());

        return cards;
    }

    public static CardsDto mapToCardsDto(Cards cards, CardsDto cardsDto) {

        cardsDto.setMobileNumber(cards.getMobileNumber());

        cardsDto.setCardNumber(cards.getCardNumber());

        cardsDto.setCardType(cards.getCardType());

//        cardsDto.setCardLimit(cards.getCardLimit());

        cardsDto.setTotalLimit(cards.getTotalLimit());

        cardsDto.setAmountUsed(cards.getAmountUsed());

        cardsDto.setAvailableAmount(cards.getAvailableAmount());

        return cardsDto;
    }
}
