package ubb.postuniv.riddingaddict.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ubb.postuniv.riddingaddict.model.dto.OrderDTO;
import ubb.postuniv.riddingaddict.model.dto.ProductDTOResponse;
import ubb.postuniv.riddingaddict.model.orderFactory.FactoryProvider;
import ubb.postuniv.riddingaddict.model.pojo.Order;
import ubb.postuniv.riddingaddict.model.pojo.Product;

import java.util.List;

@Component
public class OrderDTOMapper extends AbstractMapper<Order, OrderDTO> {

    @Autowired
    private Mapper<Product, ProductDTOResponse> productMapper;

    @Override
    public Order convertDtoToModel(OrderDTO orderDTO) {

        Order order = FactoryProvider.getFactory(orderDTO.getProducts().size()).getOrder();

        List<Product> products = productMapper.convertDtosToModels(orderDTO.getProducts());
        order.setProducts(products);

        return order;
    }

    @Override
    public OrderDTO convertModelToDto(Order order) {

        List<ProductDTOResponse> productsDTO = productMapper.convertModelsToDtos(order.getProducts());

        String customer = order.getAppUser().getFirstName() + " " + order.getAppUser().getLastName();

        OrderDTO orderDTO = new OrderDTO(productsDTO, customer, order.getTotalAmountPaid());

        return orderDTO;
    }
}
