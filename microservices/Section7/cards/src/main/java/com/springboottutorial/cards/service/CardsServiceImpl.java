package com.springboottutorial.cards.service;

import com.springboottutorial.cards.Repository.CardsRepository;
import com.springboottutorial.cards.constants.CardsConstants;
import com.springboottutorial.cards.dto.CardsDto;
import com.springboottutorial.cards.entity.Cards;
import com.springboottutorial.cards.exceptions.CardsAlreadyExistsException;
import com.springboottutorial.cards.exceptions.ResourceNotFoundException;
import com.springboottutorial.cards.mapper.CardsMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl  implements ICardsService {

    CardsRepository cardsRepository;

    @Override
    public void createCards(String mobileNumber) {

        Optional<Cards> existingCards = cardsRepository.findByMobileNumber(mobileNumber);

        if(existingCards.isPresent()) {
            throw new CardsAlreadyExistsException("Card already exists for mobile number " + mobileNumber);
        } else {
            Cards cards = generateNewCard(mobileNumber);
            cardsRepository.save(cards);
        }


    }

    @Override
    public CardsDto getCards(String mobileNumber) {

        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
            () -> new ResourceNotFoundException("Card does not exist for mobile number " + mobileNumber)
        );

        return CardsMapper.mapToCardsDto(cards, new CardsDto());
        }

    @Override
    public boolean updateCards(CardsDto cardsDto) {

        boolean isCardUpdated = false;

        Cards existingCards = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
            () -> new ResourceNotFoundException("Card does not exist for the card number " + cardsDto.getCardNumber()));

        Cards updatedCards = CardsMapper.mapToCards(cardsDto, existingCards);

        cardsRepository.save(updatedCards);

        isCardUpdated = true;

        return isCardUpdated;

    }

    @Override
    public boolean deleteCards(String mobileNumber) {

        boolean isCardDeleted = false;

        Cards existingCards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card does not exist for mobile number " + mobileNumber));

        cardsRepository.deleteById(existingCards.getCardId());
        isCardDeleted = true;
        return isCardDeleted;
    }

    private Cards generateNewCard(String mobileNumber) {

        Cards cards = new Cards();

        long randomAccountNumber = (long) (new Random().nextInt(90000000) * 100000000L);

        cards.setMobileNumber(mobileNumber);
        cards.setCardNumber(String.valueOf(randomAccountNumber));
        cards.setCardType(CardsConstants.CREDIT_CARD_TYPE);
//        cards.setCardLimit(CardsConstants.CREDIT_LIMIT);
        cards.setTotalLimit(CardsConstants.TOTAL_LIMIT);
        cards.setAmountUsed(0);
        cards.setAvailableAmount(CardsConstants.TOTAL_LIMIT - cards.getAmountUsed());

        return cards;

    }

}
