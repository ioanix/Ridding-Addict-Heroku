package ubb.postuniv.riddingaddict.model.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "orders")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@NoArgsConstructor
public abstract class Order extends BaseEntity<Long> {

    @ManyToMany
    private List<Product> products = new ArrayList<>();

   @Transient
    private Set<Long> productIds = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    @Transient
    private Card card;

    @Column
    private double totalAmountPaid = 0;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date timestamp;


    @PrePersist
    private void onCreate() {

        timestamp = new Date();
    }
}
