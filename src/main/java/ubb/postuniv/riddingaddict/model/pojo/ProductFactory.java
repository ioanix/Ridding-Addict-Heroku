package ubb.postuniv.riddingaddict.model.pojo;


import ubb.postuniv.riddingaddict.exception.ItemNotFoundException;
import ubb.postuniv.riddingaddict.model.enums.AccessoryType;
import ubb.postuniv.riddingaddict.model.enums.BikeType;
import ubb.postuniv.riddingaddict.model.enums.ProductCategory;

public class ProductFactory {

    public static Product getProduct(ProductCategory category, String name, double price, int quantity, String shortDescription, BikeType bikeType, AccessoryType accessoryType) {

        switch (category) {

            case BIKE:
                return new Bike(name, price, quantity, shortDescription, category, bikeType);

            case ACCESSORY:
                return new Accessory(name, price, quantity, shortDescription, category, accessoryType);

            default:
                throw new ItemNotFoundException("Product category not found");
        }
    }
}
