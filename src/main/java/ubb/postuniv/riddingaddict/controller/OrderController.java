package ubb.postuniv.riddingaddict.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ubb.postuniv.riddingaddict.mapper.Mapper;
import ubb.postuniv.riddingaddict.model.dto.CardDTO;
import ubb.postuniv.riddingaddict.model.dto.OrderDTORequest;
import ubb.postuniv.riddingaddict.model.dto.OrderDTOResponse;
import ubb.postuniv.riddingaddict.model.pojo.Card;
import ubb.postuniv.riddingaddict.model.pojo.Order;
import ubb.postuniv.riddingaddict.service.CreditCardService;
import ubb.postuniv.riddingaddict.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api")
@Log4j2
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CreditCardService cardService;

    @Autowired
    private Mapper<Order, OrderDTOResponse> orderResponseMapper;

    @Autowired
    private Mapper<Order, OrderDTORequest> orderRequestMapper;

    @Autowired
    private Mapper<Card, CardDTO> cardMapper;


    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTOResponse>> getAllOrders() {

        List<Order> orders = orderService.getAll();
        log.info("orders = {}", orders);

        return new ResponseEntity<>(orderResponseMapper.convertModelsToDtos(orders), HttpStatus.OK);
    }

    @PostMapping(value = "/orders/pay")
    public ResponseEntity<OrderDTOResponse> finalizePurchase(@RequestBody OrderDTORequest orderDtoRequest) {

        cardService.validateCard(cardMapper.convertDtoToModel(orderDtoRequest.getCardDto()));

        String hiddenNumber = cardService.hideCreditCardNumber(orderDtoRequest.getCardDto().getCardNumber());
        orderDtoRequest.getCardDto().setCardNumber(hiddenNumber);

        log.info("orderDto = {}", orderDtoRequest);

        Order order = orderService.finalizePurchase(orderRequestMapper.convertDtoToModel(orderDtoRequest));

        return new ResponseEntity<>(orderResponseMapper.convertModelToDto(order), HttpStatus.OK);
    }
}
