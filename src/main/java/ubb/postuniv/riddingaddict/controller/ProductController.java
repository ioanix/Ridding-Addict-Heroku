package ubb.postuniv.riddingaddict.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ubb.postuniv.riddingaddict.mapper.Mapper;
import ubb.postuniv.riddingaddict.model.dto.ProductDTO;
import ubb.postuniv.riddingaddict.model.enums.ProductCategory;
import ubb.postuniv.riddingaddict.model.pojo.Product;
import ubb.postuniv.riddingaddict.model.validator.Validator;
import ubb.postuniv.riddingaddict.service.ProductService;

import java.util.List;

@RestController
@Log4j2
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private Mapper<Product, ProductDTO> productMapper;

    @Autowired
    private Validator<String> categoryValidator;


    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {

        List<Product> products = productService.getAll();
        log.info("getAllProducts = {}", products);

        return new ResponseEntity<>(productMapper.convertModelsToDtos(products), HttpStatus.OK);
    }

    @GetMapping("/products/descByPrice")
    public ResponseEntity<List<ProductDTO>> showProductsOrderedByPriceDesc() {

        List<Product> products = productService.getProductsOrderedByPriceDesc();
        log.info("getAllProductsOrderedByPriceDesc = {}", products);

        return new ResponseEntity<>(productMapper.convertModelsToDtos(products), HttpStatus.OK);
    }

    @GetMapping("/products/categories/{category}")
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@PathVariable String category) {

        categoryValidator.validate(category);

        List<Product> productsByCategory = productService.findProductByCategory(ProductCategory.valueOf(category.toUpperCase()));
        log.info("getProductsByCategory = {}", productsByCategory);

        return new ResponseEntity<>(productMapper.convertModelsToDtos(productsByCategory), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getOneProduct(@PathVariable Long id) {

        Product product = productService.findOneProduct(id);
        log.info("productDto = {}", product);

        return new ResponseEntity<>(productMapper.convertModelToDto(product), HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDto) {

        log.info("productDto = {}", productDto);
        productService.addProduct(productMapper.convertDtoToModel(productDto));

        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }
}
