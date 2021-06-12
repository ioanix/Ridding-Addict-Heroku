package ubb.postuniv.riddingaddict.model.dto;

import lombok.*;
import ubb.postuniv.riddingaddict.model.pojo.Role;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AppUserDTOResponse {

    private String userCode;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private Set<Role> roles = new HashSet<>();
    private List<ProductDTOResponse> products = new ArrayList<>();
}
