package ubb.postuniv.riddingaddict.service;

import org.springframework.data.domain.PageRequest;
import ubb.postuniv.riddingaddict.model.enums.ProductCategory;
import ubb.postuniv.riddingaddict.model.pojo.Product;
import ubb.postuniv.riddingaddict.model.pojo.ProductCodeAndNameViewModel;

import java.util.List;

public interface ProductService {

    void addProduct(Product product);

    Product findOneProduct(String productCode);

    List<Product> getAll();

    List<Product> getProductsOrderedByPriceDesc(PageRequest pageRequest);

    List<Product> findProductByCategory(ProductCategory category);

    void deleteProduct(String productCode);

    void updateProduct(Product product, String productCode);

    List<ProductCodeAndNameViewModel> getProductCodesAndNames();
}
