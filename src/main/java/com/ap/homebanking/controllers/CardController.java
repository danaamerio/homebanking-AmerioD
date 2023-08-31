/*
package com.ap.homebanking.controllers;

import com.ap.homebanking.Enum.TransactionType;
import com.ap.homebanking.dtos.CardDTO;
import com.ap.homebanking.models.Card;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.repositories.CardRepository;
import com.ap.homebanking.repositories.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@RestController

public class CardController {

    private CardRepository cardRepository;
    private ClientRepository clientRepository;

    @RequestMapping("/clients/current/cards")
    public ResponseEntity<Set<CardDTO>> filterCard(
            Authentication authentication,
            @RequestParam Color color,
            @RequestParam TransactionType type){

        String email =  authentication.getName();
        Client client = clientRepository.findByEmail (email);
        Set<Card> cards = client.getCards();

        Set<CardDTO> filteredCards = cards.stream()
                .filter(card => card.getColor().equals(color) && card.getType().equals(type))
                .map(CardDTO::new)
                .collect(Collectors.toSet());

        return ResponseEntity.ok(filteredCards);
    }

    @PostMapping("/clients/current/cards")
    public  ResponseEntity <Object> createCard(
            @RequestParam Color cardColor,
            @RequestParam TransactionType cardType,
            Authentication authentication){

        List<Card> someClassCards.size();

        Client authClient = clientRepository.findByEmail(authentication.getName());
        List<Card>clientCards = cardRepository.findByClient(authClient);

        if (numberOfCards>=3) {
            return new ResponseEntity<>("Error, max number of cards of this class surpassed", HttpStatus.NOT_ACCEPTABLE);
        }

        Random random = new Random();
        int cvv = random.nextInt(999) + 1;
        int numberCard = random.nextInt(9999) + 1;
        String randomCard = numberCard + " " + numberCard + " " + numberCard + " " + numberCard;
        LocalDate thruDate = LocalDate.now().plus(5, ChronoUnit.YEARS)
}
*/
