package ubb.postuniv.riddingaddict.service;

import ubb.postuniv.riddingaddict.model.enums.ProductCategory;
import ubb.postuniv.riddingaddict.model.pojo.Product;

import java.util.List;

public interface ProductService {

    void addProduct(Product product);

    Product findOneProduct(Long id);

    List<Product> getAll();

    List<Product> getProductsOrderedByPriceDesc();

    List<Product> findProductByCategory(ProductCategory category);
}
