package ubb.postuniv.riddingaddict.model.orderFactory;


import ubb.postuniv.riddingaddict.model.pojo.Order;
import ubb.postuniv.riddingaddict.model.pojo.SmallOrder;

public class SmallOrderFactory implements AbstractFactory {

    @Override
    public Order getOrder() {

        return new SmallOrder();
    }
}
