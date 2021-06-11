package ubb.postuniv.riddingaddict.model.validator;

import ubb.postuniv.riddingaddict.exception.ShopException;
import ubb.postuniv.riddingaddict.model.enums.BikeType;

import java.util.Arrays;

public class BikeCategoryValidator {

    public static void validate(String category) {

        String message = "";

        boolean anyMatch = Arrays.stream(BikeType.values())
                .anyMatch(bikeType -> bikeType.getBikeType().equalsIgnoreCase(category));

        message += !anyMatch ? "The category you entered is not valid. Please enter: e.g. citybike, mountainbike, electricbike" : "";

        if (!message.equals("")) {

            throw new ShopException(message);
        }
    }
}
