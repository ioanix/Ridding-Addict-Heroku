package ubb.postuniv.riddingaddict.model.validator;

import org.springframework.stereotype.Component;
import ubb.postuniv.riddingaddict.exception.ShopException;
import ubb.postuniv.riddingaddict.model.pojo.Card;

import java.time.YearMonth;

@Component
public class CreditCardValidator implements Validator<Card> {

    private CreditCardValidator() {

    }

    private static class CreditCardValidatorHelper {

        private static final CreditCardValidator INSTANCE = new CreditCardValidator();
    }

    public static CreditCardValidator getInstance() {

        return CreditCardValidatorHelper.INSTANCE;
    }

    public void validate(Card card) {

        StringBuilder message = new StringBuilder();

        int[] numbers = new int[card.getCardNumber().length()];  //Luhn algorithm for credit card validation

        for (int i = 0; i < card.getCardNumber().length(); i++) {

            numbers[i] = Integer.parseInt(card.getCardNumber().substring(i, i + 1));

        }
        for (int i = numbers.length - 2; i >= 0; i = i - 2) {
            int j = numbers[i];
            j = j * 2;
            if (j > 9) {
                j = j % 10 + 1;
            }
            numbers[i] = j;
        }

        int sum = 0;

        for (int number : numbers) {

            sum += number;
        }

        if(sum % 10 != 0) {

            message.append("The credit card number is not valid.");
        }

        if(String.valueOf(card.getCvvCode()).length() != 3 || Character.isLetter(card.getCvvCode())) {

            message.append("The cvv code must contain 3 digits.");
        }

        if(card.getCardHolderName().isEmpty() || card.getCardHolderName().length() < 3) {

            message.append("The name that you entered is invalid.");
        }

        if(YearMonth.now().compareTo(card.getExpirationDate()) > 0) {

            message.append("The card is expired.");
        }

        if(message.length() != 0) {

            throw new ShopException(message.toString());
        }
    }
}
