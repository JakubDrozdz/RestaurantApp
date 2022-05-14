package Orders;

import java.util.ArrayList;

public class OrderForDelivery extends Order{
    private String deliveryAddress;

    public OrderForDelivery(ArrayList orderList,String deliveryAddress) {
        super(orderList);
        this.deliveryAddress = deliveryAddress;
    }
}
