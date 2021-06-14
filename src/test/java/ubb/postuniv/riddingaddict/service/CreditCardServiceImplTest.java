package ubb.postuniv.riddingaddict.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ubb.postuniv.riddingaddict.exception.ShopException;
import ubb.postuniv.riddingaddict.model.enums.CardType;
import ubb.postuniv.riddingaddict.model.pojo.Card;
import ubb.postuniv.riddingaddict.model.validator.Validator;

import java.time.YearMonth;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CreditCardServiceImplTest {

    private static final String INVALID_CARD_NUMBER = "1234432";
    private static final String VALID_CARD_NUMBER = "1234432111223344";
    private static final String MASKED_CARD_NUMBER = "1234********3344";
    private static final String CARDHOLDER_NAME = "person1";
    private static final String INVALID_CARDHOLDER_NAME = "p1";
    private static final Integer CVV_CODE = 123;
    private static final Integer INVALID_CVV_CODE = 12;

    @Mock
    private Validator<Card> cardValidatorMock;

    @InjectMocks
    private CreditCardServiceImpl underTest;

    private Card card;
    private Card card1;
    private Card card2;
    private Card card3;
    private Card card4;

    @BeforeAll
    void setup() {

        card = new Card(CARDHOLDER_NAME, INVALID_CARD_NUMBER, CVV_CODE, YearMonth.of(2022, 11), CardType.VISA);

        card1 = new Card(CARDHOLDER_NAME, VALID_CARD_NUMBER, INVALID_CVV_CODE, YearMonth.of(2022, 11), CardType.VISA);

        card2 = new Card(INVALID_CARDHOLDER_NAME, VALID_CARD_NUMBER, CVV_CODE, YearMonth.of(2022, 11), CardType.VISA);

        card3 = new Card(CARDHOLDER_NAME, VALID_CARD_NUMBER, CVV_CODE, YearMonth.of(2020, 11), CardType.VISA);

        card4 = new Card(CARDHOLDER_NAME, VALID_CARD_NUMBER, CVV_CODE, YearMonth.of(2022, 11), CardType.VISA);

    }

    @Test
    void testValidateCardAndThrowExceptionIfCreditCardNumberIsNotValid() {

        //given
        doThrow(ShopException.class).when(cardValidatorMock).validate(card);

        //when
        //then
        assertThatThrownBy(() -> underTest.validateCard(card))
                .isInstanceOf(ShopException.class);
    }

    @Test
    void testValidateCardAndThrowExceptionIfCvvCodeIsNotValid() {

        //given
        doThrow(ShopException.class).when(cardValidatorMock).validate(card1);

        //when
        //then
        assertThatThrownBy(() -> underTest.validateCard(card1))
                .isInstanceOf(ShopException.class);

    }

    @Test
    void testValidateCardAndThrowExceptionIfCardholderNameIsNotValid() {

        //given
        doThrow(ShopException.class).when(cardValidatorMock).validate(card2);

        //when
        //then
        assertThatThrownBy(() -> underTest.validateCard(card2))
                .isInstanceOf(ShopException.class);

    }

    @Test
    void testValidateCardAndThrowExceptionIfTheCardIsExpired() {

        //given
        doThrow(ShopException.class).when(cardValidatorMock).validate(card3);

        //when
        //then
        assertThatThrownBy(() -> underTest.validateCard(card3))
                .isInstanceOf(ShopException.class);

    }


    @Test
    void testHideCreditCardNumber() {

        //when
        String hiddenNumber = underTest.hideCreditCardNumber(card4.getCardNumber());

        //then
        assertThat(MASKED_CARD_NUMBER).isEqualTo(hiddenNumber);

    }
}