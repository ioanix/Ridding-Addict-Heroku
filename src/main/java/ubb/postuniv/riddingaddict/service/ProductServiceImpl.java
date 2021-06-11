package ubb.postuniv.riddingaddict.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ubb.postuniv.riddingaddict.exception.ItemNotFoundException;
import ubb.postuniv.riddingaddict.model.enums.ProductCategory;
import ubb.postuniv.riddingaddict.model.pojo.Product;
import ubb.postuniv.riddingaddict.repository.ProductRepository;

import java.time.LocalDate;
import java.util.List;

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
                new ItemNotFoundException("The product id does not exist"));

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
}
