package ubb.postuniv.riddingaddict.model.dto;

import lombok.*;
import ubb.postuniv.riddingaddict.model.enums.CardType;

import java.time.YearMonth;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CardDTO {

    private String cardHolderName;
    private String cardNumber;
    private int cvvCode;
    private YearMonth expirationDate;
    private CardType cardType;
}
