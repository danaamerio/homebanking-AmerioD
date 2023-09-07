package com.ap.homebanking.controllers;

import com.ap.homebanking.Enum.CardColor;
import com.ap.homebanking.Enum.CardType;
import com.ap.homebanking.dtos.CardDTO;
import com.ap.homebanking.models.Card;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.repositories.CardRepository;
import com.ap.homebanking.repositories.ClientRepository;
import com.ap.homebanking.services.implement.card.CardService;
import com.ap.homebanking.services.implement.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CardController {

    //@Autowired
    //private ClientRepository clientRepository;
    //@Autowired
    //private CardRepository cardRepository;
    @Autowired
    private CardService cardService;
    @Autowired
    private ClientService clientService;

    @GetMapping("/clients/current/cards")
    public ResponseEntity<Set<CardDTO>> filterCards(
            Authentication authentication,
             @RequestParam CardColor color,
             @RequestParam CardType type) {
        String email = authentication.getName();
        Client client = clientService.findByEmail(email);
        Set<Card> cards = client.getCards();

        Set<CardDTO> filteredCards = cards.stream()
                .filter(card -> card.getColor().equals(color) && card.getType().equals(type))
                .map(CardDTO::new)
                .collect(Collectors.toSet());

        return ResponseEntity.ok(filteredCards);
    }

    @PostMapping("/clients/current/cards")
    public ResponseEntity<Object> createCard(
            @RequestParam CardColor cardColor,
            @RequestParam CardType cardType,
            Authentication authentication) {

        try {
            Client authClient = clientService.findByEmail(authentication.getName());

            List<Card> sameTypeAndColorCards = cardService.findByClientAndColorAndType(authClient, cardColor, cardType);

            if (!sameTypeAndColorCards.isEmpty()) {
                return new ResponseEntity<>("Error, a card of this type and color already exists", HttpStatus.FORBIDDEN);
            }

            List<Card> clientCards = cardService.findByClient(authClient);

            if (clientCards.size() >= 6) {
                return new ResponseEntity<>("Error, max number of cards surpassed", HttpStatus.FORBIDDEN);
            }

            // Generar números aleatorios para CVV y número de tarjeta
            Random random = new Random();
            int cvv = 100 + random.nextInt(900); // CVV de 3 dígitos
            String randomCard = generateRandomCardNumber(); // Genera un número de tarjeta aleatorio
            LocalDate thruDate = LocalDate.now().plus(5, ChronoUnit.YEARS);
            String cardHolder = authClient.getFirstName() + " " + authClient.getLastName();

            Card newCard = new Card(cardHolder, cardType, cardColor, randomCard, String.valueOf(cvv), LocalDate.now(), thruDate);
            newCard.setClient(authClient);
            cardService.save(newCard);

            return ResponseEntity.ok("Card created successfully");
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating card", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para generar un número de tarjeta aleatorio (simplificado)
    private String generateRandomCardNumber() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder("4"); // Comienza con "4" para simular una tarjeta Visa
        for (int i = 1; i < 16; i++) {
            cardNumber.append(random.nextInt(10)); // Agrega números aleatorios del 0 al 9
        }
        return cardNumber.toString();
    }
}




