package ubb.postuniv.riddingaddict.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(description = "Details about the order")
public class OrderDTO {

    private List<ProductDTOResponse> products = new ArrayList<>();
    private String customer;
    private double totalAmountPaid = 0;
}
