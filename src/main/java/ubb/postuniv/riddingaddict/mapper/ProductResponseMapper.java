package ubb.postuniv.riddingaddict.mapper;

import org.springframework.stereotype.Component;
import ubb.postuniv.riddingaddict.model.dto.ProductDTOResponse;
import ubb.postuniv.riddingaddict.model.enums.ProductCategory;
import ubb.postuniv.riddingaddict.model.pojo.Accessory;
import ubb.postuniv.riddingaddict.model.pojo.Bike;
import ubb.postuniv.riddingaddict.model.pojo.Product;
import ubb.postuniv.riddingaddict.model.pojo.ProductFactory;

@Component
public class ProductResponseMapper extends AbstractMapper<Product, ProductDTOResponse> {

    @Override
    public Product convertDtoToModel(ProductDTOResponse productDTOResponse) {

        return ProductFactory.getProductResponse(productDTOResponse.getProductCode(),
                productDTOResponse.getCategory(),
                productDTOResponse.getName(),
                productDTOResponse.getPrice(),
                productDTOResponse.getQuantity(),
                productDTOResponse.getShortDescription(),
                productDTOResponse.getDateAdded(),
                productDTOResponse.getBikeType(),
                productDTOResponse.getAccessoryType());
    }

    @Override
    public ProductDTOResponse convertModelToDto(Product product) {

        ProductDTOResponse productDTOResponse = null;

        if (String.valueOf(ProductCategory.BIKE).equals(String.valueOf(product.getCategory()))) {

            Bike bike = (Bike) product;

            productDTOResponse = new ProductDTOResponse.ProductDTOResponseBuilder(bike.getProductCode(), bike.getName(), bike.getPrice(), bike.getQuantity(), bike.getShortDescription(), bike.getCategory(), bike.getDateAdded())
                    .bikeType(bike.getBikeType())
                    .build();
        }

        if (String.valueOf(ProductCategory.ACCESSORY).equals(String.valueOf(product.getCategory()))) {

            Accessory accessory = (Accessory) product;

            productDTOResponse = new ProductDTOResponse.ProductDTOResponseBuilder(accessory.getProductCode(), accessory.getName(), accessory.getPrice(), accessory.getQuantity(), accessory.getShortDescription(), accessory.getCategory(), accessory.getDateAdded())
                    .accessoryType(accessory.getAccessoryType())
                    .build();
        }

        return productDTOResponse;
    }
}
