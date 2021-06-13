package ubb.postuniv.riddingaddict.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(description = "Details about the order")
public class OrderDTORequest {

    private Set<String> productCodes = new HashSet<>();
    private String username;
    private CardDTO cardDto;
}
