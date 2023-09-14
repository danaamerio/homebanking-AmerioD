package com.ap.homebanking.services.implement;

import com.ap.homebanking.Enum.CardColor;
import com.ap.homebanking.Enum.CardType;
import com.ap.homebanking.dtos.CardDTO;
import com.ap.homebanking.models.Card;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.repositories.CardRepository;
import com.ap.homebanking.services.implement.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CardServiceImplement implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public Set<CardDTO> filterCards(Client client, CardColor color, CardType type) {
        Set<Card> cards = client.getCards();

        Set<CardDTO> filteredCards = cards.stream()
                .filter(card -> card.getColor().equals(color) && card.getType().equals(type))
                .map(CardDTO::new)
                .collect(Collectors.toSet());

        return filteredCards;
    }

    @Override
    public String createCard(Client client, CardColor cardColor, CardType cardType) {
        List<Card> sameTypeAndColorCards = cardRepository.findByClientAndColorAndType(client, cardColor, cardType);

        if (!sameTypeAndColorCards.isEmpty()) {
            return "Error, a card of this type and color already exists";
        }

        List<Card> clientCards = cardRepository.findByClient(client);

        if (clientCards.size() >= 6) {
            return "Error, max number of cards surpassed";
        }

        // Generar números aleatorios para CVV y número de tarjeta
        Random random = new Random();
        int cvv = 100 + random.nextInt(900); // CVV de 3 dígitos
        String randomCard = generateRandomCardNumber(); // Genera un número de tarjeta aleatorio
        LocalDate thruDate = LocalDate.now().plus(5, ChronoUnit.YEARS);
        String cardHolder = client.getFirstName() + " " + client.getLastName();

        Card newCard = new Card(cardHolder, cardType, cardColor, randomCard, String.valueOf(cvv), LocalDate.now(), thruDate);
        newCard.setClient(client);
        cardRepository.save(newCard);

        return "Card created successfully";
    }

    @Override
    public void save(Card newCard) {

    }

    @Override
    public List<Card> findByClient(Client authClient) {
        return null;
    }

    @Override
    public List<Card> findByClientAndColorAndType(Client authClient, CardColor cardColor, CardType cardType) {
        return null;
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
