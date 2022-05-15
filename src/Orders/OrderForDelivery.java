package Orders;

import java.util.ArrayList;

public class OrderForDelivery extends Order{

    public OrderForDelivery(ArrayList orderList,String deliveryAddress,int id) {
        super(orderList,true,id);
        super.deliveryAddress = deliveryAddress;
    }
}
