package ubb.postuniv.riddingaddict.model.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ubb.postuniv.riddingaddict.model.enums.BikeType;
import ubb.postuniv.riddingaddict.model.enums.ProductCategory;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Bike extends Product {

    private BikeType bikeType;

    public Bike(String name, double price, int quantity, String shortDescription, ProductCategory category, BikeType bikeType) {
        super(name, price, quantity, shortDescription, category);
        this.bikeType = bikeType;
    }
}
