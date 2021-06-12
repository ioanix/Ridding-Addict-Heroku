package ubb.postuniv.riddingaddict.mapper;

import org.springframework.stereotype.Component;
import ubb.postuniv.riddingaddict.model.dto.CardDTO;
import ubb.postuniv.riddingaddict.model.pojo.Card;

@Component
public class CardMapper extends AbstractMapper<Card, CardDTO> {

    @Override
    public Card convertDtoToModel(CardDTO cardDTO) {

        return new Card(cardDTO.getCardHolderName(), cardDTO.getCardNumber(), cardDTO.getCvvCode(), cardDTO.getExpirationDate(), cardDTO.getCardType());
    }

    @Override
    public CardDTO convertModelToDto(Card card) {

        return new CardDTO(card.getCardHolderName(), card.getCardNumber(), card.getCvvCode(), card.getExpirationDate(), card.getCardType());
    }
}
