package ubb.postuniv.riddingaddict.model.orderFactory;


import ubb.postuniv.riddingaddict.model.pojo.BigOrder;
import ubb.postuniv.riddingaddict.model.pojo.Order;

public class BigOrderFactory implements AbstractFactory {

    private static final double DISCOUNT = 0.1;

    @Override
    public Order getOrder() {

        BigOrder bigOrder = new BigOrder();
        bigOrder.setDiscount(DISCOUNT);

        return bigOrder;
    }
}
