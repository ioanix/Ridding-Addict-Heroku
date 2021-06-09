package ubb.postuniv.riddingaddict.model.enums;

import lombok.Getter;

@Getter
public enum ProductCategory {

    BIKE("Bike"),
    ACCESSORY("Accessory");

    private String productCategory;

    ProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
}
