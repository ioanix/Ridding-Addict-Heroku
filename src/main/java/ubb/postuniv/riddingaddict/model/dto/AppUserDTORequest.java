package ubb.postuniv.riddingaddict.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.*;
import ubb.postuniv.riddingaddict.model.pojo.Role;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(description = "Details about the user")
public class AppUserDTORequest {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private Role role;

    //private List<CardDTO> creditCards = new ArrayList<>();

    public AppUserDTORequest(String firstName, String lastName, String username, String email, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
