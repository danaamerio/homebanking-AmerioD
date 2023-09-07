package com.ap.homebanking.services.implement.card;

import com.ap.homebanking.Enum.CardColor;
import com.ap.homebanking.Enum.CardType;
import com.ap.homebanking.dtos.CardDTO;
import com.ap.homebanking.models.Card;
import com.ap.homebanking.models.Client;

import java.util.List;
import java.util.Set;

public interface CardService {
    Set<CardDTO> filterCards(Client client, CardColor color, CardType type);
    String createCard(Client client, CardColor cardColor, CardType cardType);

    void save(Card newCard);

    List<Card> findByClient(Client authClient);

    List<Card> findByClientAndColorAndType(Client authClient, CardColor cardColor, CardType cardType);
}
