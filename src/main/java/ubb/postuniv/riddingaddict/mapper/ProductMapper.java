package ubb.postuniv.riddingaddict.mapper;

import org.springframework.stereotype.Component;
import ubb.postuniv.riddingaddict.model.dto.ProductDTO;
import ubb.postuniv.riddingaddict.model.enums.ProductCategory;
import ubb.postuniv.riddingaddict.model.pojo.Accessory;
import ubb.postuniv.riddingaddict.model.pojo.Bike;
import ubb.postuniv.riddingaddict.model.pojo.Product;
import ubb.postuniv.riddingaddict.model.pojo.ProductFactory;

@Component
public class ProductMapper extends AbstractMapper<Product, ProductDTO> {

    @Override
    public Product convertDtoToModel(ProductDTO productDto) {

        return ProductFactory.getProduct(productDto.getCategory(),
                productDto.getName(),
                productDto.getPrice(),
                productDto.getQuantity(),
                productDto.getShortDescription(),
                productDto.getBikeType(),
                productDto.getAccessoryType());
    }

    @Override
    public ProductDTO convertModelToDto(Product product) {

        ProductDTO productDto = null;

        if (String.valueOf(ProductCategory.BIKE).equals(String.valueOf(product.getCategory()))) {

            Bike bike = (Bike) product;

            productDto = new ProductDTO.ProductDTOBuilder(bike.getName(), bike.getPrice(), bike.getQuantity(), bike.getShortDescription(), bike.getCategory())
                    .bikeType(bike.getBikeType())
                    .build();
        }

        if (String.valueOf(ProductCategory.ACCESSORY).equals(String.valueOf(product.getCategory()))) {

            Accessory accessory = (Accessory) product;

            productDto = new ProductDTO.ProductDTOBuilder(accessory.getName(), accessory.getPrice(), accessory.getQuantity(), accessory.getShortDescription(), accessory.getCategory())
                    .accessoryType(accessory.getAccessoryType())
                    .build();
        }

        return productDto;
    }
}
