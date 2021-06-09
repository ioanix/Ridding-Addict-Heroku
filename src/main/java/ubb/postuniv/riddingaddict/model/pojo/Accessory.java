package ubb.postuniv.riddingaddict.model.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ubb.postuniv.riddingaddict.model.enums.AccessoryType;
import ubb.postuniv.riddingaddict.model.enums.ProductCategory;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Accessory extends Product {

    private AccessoryType accessoryType;

    public Accessory(String name, double price, int quantity, String shortDescription, ProductCategory category, AccessoryType accessoryType) {
        super(name, price, quantity, shortDescription, category);
        this.accessoryType = accessoryType;
    }
}
