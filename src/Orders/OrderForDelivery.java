package Orders;

import java.util.ArrayList;

public class OrderForDelivery extends Order{
    private String deliveryAddress;

    public OrderForDelivery(ArrayList orderList,String deliveryAddress,int id) {
        super(orderList,true,id);
        this.deliveryAddress = deliveryAddress;
    }
}
