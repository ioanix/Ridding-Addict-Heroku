package ubb.postuniv.riddingaddict.model.orderFactory;

public class FactoryProvider {

    private FactoryProvider() {}

    public static AbstractFactory getFactory(int quantity) {

        return  quantity > 2 ? new BigOrderFactory() : new SmallOrderFactory();

    }
}
