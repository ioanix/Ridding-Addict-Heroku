package ubb.postuniv.riddingaddict.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

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
}
