package Orders;

import java.util.ArrayList;
import java.util.List;

public class Orders {
    private List<Order> ordersList = new ArrayList<>();
    public Orders() {
    }
    public void addOrder(Order o){
        ordersList.add(o);
    }
}
