package ubb.postuniv.riddingaddict.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ubb.postuniv.riddingaddict.model.enums.ProductCategory;
import ubb.postuniv.riddingaddict.model.pojo.Product;

import javax.transaction.Transactional;
import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {

    @Transactional
    @Modifying
    @Query("update Product p set p.quantity = ?1 where p.id = ?2")
    void updateQuantity(int quantity, Long id);

    List<Product> findAllByOrderByPriceDesc();

    List<Product> findByCategory(ProductCategory category);

}
