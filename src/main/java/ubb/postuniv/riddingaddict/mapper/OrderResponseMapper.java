package ubb.postuniv.riddingaddict.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ubb.postuniv.riddingaddict.model.dto.CardDTO;
import ubb.postuniv.riddingaddict.model.dto.OrderDTOResponse;
import ubb.postuniv.riddingaddict.model.dto.ProductDTOResponse;
import ubb.postuniv.riddingaddict.model.orderFactory.FactoryProvider;
import ubb.postuniv.riddingaddict.model.pojo.Card;
import ubb.postuniv.riddingaddict.model.pojo.Order;
import ubb.postuniv.riddingaddict.model.pojo.Product;

import java.util.List;

@Component
public class OrderResponseMapper extends AbstractMapper<Order, OrderDTOResponse> {

    @Autowired
    private Mapper<Card, CardDTO> cardMapper;

    @Autowired
    private Mapper<Product, ProductDTOResponse> productMapper;

    @Override
    public Order convertDtoToModel(OrderDTOResponse orderDTOResponse) {

        Order order = FactoryProvider.getFactory(orderDTOResponse.getProducts().size()).getOrder();

        List<Product> products = productMapper.convertDtosToModels(orderDTOResponse.getProducts());
        order.setProducts(products);

        order.setCard(cardMapper.convertDtoToModel(orderDTOResponse.getCardDto()));

        return order;
    }

    @Override
    public OrderDTOResponse convertModelToDto(Order order) {

        List<ProductDTOResponse> productsDTO = productMapper.convertModelsToDtos(order.getProducts());

        String customer = order.getAppUser().getFirstName() + " " + order.getAppUser().getLastName();

        CardDTO cardDTO = cardMapper.convertModelToDto(order.getCard());

        OrderDTOResponse orderDTOResponse = new OrderDTOResponse(productsDTO, customer, cardDTO, order.getTotalAmountPaid());

        return orderDTOResponse;
    }
}
