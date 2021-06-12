package ubb.postuniv.riddingaddict.model.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDTOResponse {

    private List<ProductDTOResponse> products = new ArrayList<>();
    private String customer;
    private CardDTO cardDto;
    private double totalAmountPaid = 0;
}
