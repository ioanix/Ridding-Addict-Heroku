package ubb.postuniv.riddingaddict.model.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDTORequest {

    private Set<String> productCodes = new HashSet<>();
    private String username;
    private CardDTO cardDto;
}
