package ubb.postuniv.riddingaddict.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import ubb.postuniv.riddingaddict.model.pojo.Role;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(description = "Details about the user")
public class AppUserDTOResponse {

    @ApiModelProperty(notes = "The unique code of the user")
    private String userCode;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private Set<Role> roles = new HashSet<>();
    private List<ProductDTOResponse> products = new ArrayList<>();
}
