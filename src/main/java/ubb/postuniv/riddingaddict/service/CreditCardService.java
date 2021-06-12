package ubb.postuniv.riddingaddict.service;

import ubb.postuniv.riddingaddict.model.pojo.Card;

public interface CreditCardService {

    void validateCard(Card card);

    String hideCreditCardNumber(String cardNumber);
}
