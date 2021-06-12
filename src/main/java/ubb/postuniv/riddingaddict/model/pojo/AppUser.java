package ubb.postuniv.riddingaddict.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class AppUser extends BaseEntity<Long> {

    @Column(unique = true)
    private String userCode;

    private String firstName;
    private String lastName;

    private String username;
    private String email;
    private String password;

    @Transient
    private List<Card> creditCards = new ArrayList<>();

    @Transient
    List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    List<Order> orders = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();

    public AppUser(String firstName, String lastName, String username, String email, String password, Set<Role> roles) {
        this.userCode = String.valueOf(UUID.randomUUID());
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public AppUser(String userCode, String firstName, String lastName, String username, String email, List<Product> products, Set<Role> roles) {
        this.userCode = userCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.products = products;
        this.roles = roles;
    }
}
