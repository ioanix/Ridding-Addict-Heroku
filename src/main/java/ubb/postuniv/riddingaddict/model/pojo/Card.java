package ubb.postuniv.riddingaddict.model.pojo;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ubb.postuniv.riddingaddict.model.enums.CardType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.YearMonth;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Card {

    private String cardHolderName;
    private String cardNumber;
    private int cvvCode;

    //@Convert(converter = YearMonthDateAttributeConverter.class)
    private YearMonth expirationDate;

    @Enumerated(EnumType.STRING)
    private CardType cardType;

    public Card(String cardHolderName, String cardNumber, int cvvCode, YearMonth expirationDate, CardType cardType) {
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.cvvCode = cvvCode;
        this.expirationDate = expirationDate;
        this.cardType = cardType;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardHolderName='" + cardHolderName + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cvvCode=" + cvvCode +
                ", expirationDate=" + expirationDate +
                ", cardType=" + cardType +
                '}';
    }

}
