package ubb.postuniv.riddingaddict.service;

import org.springframework.stereotype.Service;
import ubb.postuniv.riddingaddict.model.pojo.Card;
import ubb.postuniv.riddingaddict.model.validator.CreditCardValidator;
import ubb.postuniv.riddingaddict.model.validator.Validator;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    private Validator<Card> cardValidator;

    public CreditCardServiceImpl() {

        cardValidator = CreditCardValidator.getInstance();
    }

    @Override
    public void validateCard(Card card) {

        cardValidator.validate(card);
    }

    @Override
    public String hideCreditCardNumber(String cardNumber) {

        String innerString = cardNumber.substring(4, 12);

        innerString = innerString.replaceAll("[0-9]", "*");

        cardNumber = cardNumber.substring(0, 4).concat(innerString).concat(cardNumber.substring(12));

        return cardNumber;
    }
}
