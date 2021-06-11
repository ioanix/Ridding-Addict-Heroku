package ubb.postuniv.riddingaddict.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ubb.postuniv.riddingaddict.model.pojo.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
