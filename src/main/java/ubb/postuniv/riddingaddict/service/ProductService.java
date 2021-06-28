package ubb.postuniv.riddingaddict.service;

import org.springframework.http.ResponseEntity;
import ubb.postuniv.riddingaddict.model.enums.ProductCategory;
import ubb.postuniv.riddingaddict.model.pojo.Product;

import java.util.List;

public interface ProductService {

    void addProduct(Product product);

    Product findOneProduct(String productCode);

    List<Product> getAll();

    List<Product> getProductsOrderedByPriceDesc();

    List<Product> findProductByCategory(ProductCategory category);

    void deleteProduct(String productCode);

    void updateProduct(Product product, String productCode);

    List<String> getProductCodes();
}
