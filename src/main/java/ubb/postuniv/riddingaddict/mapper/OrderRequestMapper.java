package ubb.postuniv.riddingaddict.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ubb.postuniv.riddingaddict.model.dto.CardDTO;
import ubb.postuniv.riddingaddict.model.dto.OrderDTORequest;
import ubb.postuniv.riddingaddict.model.orderFactory.FactoryProvider;
import ubb.postuniv.riddingaddict.model.pojo.AppUser;
import ubb.postuniv.riddingaddict.model.pojo.Card;
import ubb.postuniv.riddingaddict.model.pojo.Order;

@Component
public class OrderRequestMapper extends AbstractMapper<Order, OrderDTORequest> {

    @Autowired
    private Mapper<Card, CardDTO> cardMapper;

    @Override
    public Order convertDtoToModel(OrderDTORequest orderDTORequest) {

        Order order = FactoryProvider.getFactory(orderDTORequest.getProductCodes().size()).getOrder();
        order.setProductCodes(orderDTORequest.getProductCodes());
        order.setCard(cardMapper.convertDtoToModel(orderDTORequest.getCardDto()));

        AppUser appUser = new AppUser();
        appUser.setUsername(orderDTORequest.getUsername());

        order.setAppUser(appUser);

        return order;
    }

    @Override
    public OrderDTORequest convertModelToDto(Order order) {

        return new OrderDTORequest(order.getProductCodes(), order.getAppUser().getUsername(), cardMapper.convertModelToDto(order.getCard()));
    }
}
