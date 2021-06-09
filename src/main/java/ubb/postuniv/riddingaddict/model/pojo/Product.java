package ubb.postuniv.riddingaddict.model.pojo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ubb.postuniv.riddingaddict.model.enums.ProductCategory;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class Product extends BaseEntity<Long> {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int quantity;

    private String shortDescription;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductCategory category;

}
