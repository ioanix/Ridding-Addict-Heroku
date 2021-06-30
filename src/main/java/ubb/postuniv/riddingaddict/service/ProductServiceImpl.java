package ubb.postuniv.riddingaddict.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ubb.postuniv.riddingaddict.exception.ItemNotFoundException;
import ubb.postuniv.riddingaddict.model.enums.ProductCategory;
import ubb.postuniv.riddingaddict.model.pojo.Accessory;
import ubb.postuniv.riddingaddict.model.pojo.Bike;
import ubb.postuniv.riddingaddict.model.pojo.Product;
import ubb.postuniv.riddingaddict.model.pojo.ProductCodeAndNameViewModel;
import ubb.postuniv.riddingaddict.repository.ProductRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void addProduct(Product product) {

        product.setDateAdded(LocalDate.now());
        productRepository.save(product);
    }

    @Override
    public Product findOneProduct(String productCode) {

        Product product = productRepository.findByProductCode(productCode).orElseThrow(() ->
                new ItemNotFoundException("The product with code " + productCode + " does not exist"));

        return product;
    }

    @Override
    public List<Product> getAll() {

        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsOrderedByPriceDesc() {

        return productRepository.findAllByOrderByPriceDesc();
    }

    @Override
    public List<Product> findProductByCategory(ProductCategory category) {

        return productRepository.findByCategory(category);
    }

    @Override
    public void deleteProduct(String productCode) {

        Optional<Product> product = productRepository.findByProductCode(productCode);

        productRepository.delete(product.orElseThrow(() ->
                new ItemNotFoundException("The product with code " + productCode + " does not exist")));
    }

    @Override
    public void updateProduct(Product product, String productCode) {

        Product existingProduct = productRepository.findByProductCode(productCode).orElseThrow(() ->
                new ItemNotFoundException("The product with code " + productCode + " does not exist"));

        if(ProductCategory.BIKE.equals(product.getCategory())) {

            Bike bike = (Bike) product;
            Bike existingBike = (Bike) existingProduct;

            existingBike.setName(bike.getName());
            existingBike.setPrice(bike.getPrice());
            existingBike.setShortDescription(bike.getShortDescription());
            existingBike.setQuantity(bike.getQuantity());
            existingBike.setBikeType(bike.getBikeType());
        }

        if(ProductCategory.ACCESSORY.equals(product.getCategory())) {

            Accessory accessory = (Accessory) product;
            Accessory existingAccessory = (Accessory) existingProduct;

            existingAccessory.setName(accessory.getName());
            existingAccessory.setPrice(accessory.getPrice());
            existingAccessory.setShortDescription(accessory.getShortDescription());
            existingAccessory.setQuantity(accessory.getQuantity());
            existingAccessory.setAccessoryType(accessory.getAccessoryType());
        }

        productRepository.save(existingProduct);
    }

    @Override
    public List<ProductCodeAndNameViewModel> getProductCodesAndNames() {

        List<Product> products = getAll();
        List<ProductCodeAndNameViewModel> productCodeAndNameList = new ArrayList<>();

        products.forEach(product -> {
            ProductCodeAndNameViewModel p = new ProductCodeAndNameViewModel(product.getProductCode(), product.getName());
            productCodeAndNameList.add(p);
        });

        return productCodeAndNameList;
    }
}
