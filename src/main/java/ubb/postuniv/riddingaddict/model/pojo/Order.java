package ubb.postuniv.riddingaddict.model.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private Set<String> productCodes = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    @Transient
    private Card card;

    @Column
    private double totalAmountPaid = 0;

    @Column(nullable = false)
    private LocalDateTime timestamp;


    @PrePersist
    private void onCreate() {

        timestamp = LocalDateTime.now();
    }

    public Order(Set<String> productCodes, Card card) {
        this.productCodes = productCodes;
        this.card = card;
    }

    public Order(List<Product> products, AppUser appUser) {
        this.products = products;
        this.appUser = appUser;
    }
}
