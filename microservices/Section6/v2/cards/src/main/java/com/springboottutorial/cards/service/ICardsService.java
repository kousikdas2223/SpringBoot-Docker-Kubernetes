package com.springboottutorial.cards.service;

import com.springboottutorial.cards.dto.CardsDto;

public interface ICardsService {

    public void createCards(String mobileNumber);

    public CardsDto getCards(String mobileNumber);

    public boolean updateCards(CardsDto cardsDto);

    public boolean deleteCards(String mobileNumber);

}
