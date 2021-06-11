package ubb.postuniv.riddingaddict.model.validator;

import org.springframework.stereotype.Component;
import ubb.postuniv.riddingaddict.exception.ShopException;
import ubb.postuniv.riddingaddict.model.enums.ProductCategory;

import java.util.Arrays;

@Component("productCategoryValidator")
public class ProductCategoryValidator implements Validator<String> {

    @Override
    public void validate(String category) {

        boolean anyMatch = Arrays.stream(ProductCategory.values())
                .anyMatch(category1 -> category1.toString().equalsIgnoreCase(category.toUpperCase()));

        if(!anyMatch) {

            throw new ShopException("The category you entered is not valid. Please enter: e.g. bike, accessory");
        }
    }
}
