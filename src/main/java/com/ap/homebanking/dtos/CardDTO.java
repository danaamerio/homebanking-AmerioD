package com.ap.homebanking.dtos;

import com.ap.homebanking.Enum.CardColor;
import com.ap.homebanking.Enum.CardType;
import com.ap.homebanking.models.Card;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class CardDTO {
    private Long id;
    private String cardholder;
    private CardType type;
    private CardColor color;
    private String number;
    private  String cvv;
    private LocalDate thruDate;
    private LocalDate fromDate;

    public CardDTO (Card card){
        this.cardholder = card.getCardholder();
        this.type = card.getType();
        this.color = card.getColor();
        this.number = card.getNumber();
        this.cvv = card.getCvv();
        this.thruDate = card.getThruDate();
        this.fromDate = card.getFromDate();
    }

    public static List<CardDTO> mapToDTOList(List<Card> cards) {
        return cards.stream()
                .map(CardDTO::new)
                .collect(toList());
    }
}
