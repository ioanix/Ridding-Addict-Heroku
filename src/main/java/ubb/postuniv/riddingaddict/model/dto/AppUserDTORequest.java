package ubb.postuniv.riddingaddict.model.dto;

import lombok.*;
import ubb.postuniv.riddingaddict.model.pojo.Role;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AppUserDTORequest {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private Set<Role> roles = new HashSet<>();

    private List<CardDTO> creditCards = new ArrayList<>();

    public AppUserDTORequest(String firstName, String lastName, String username, String email, String password, Set<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}