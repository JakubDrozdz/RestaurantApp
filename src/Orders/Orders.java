package Orders;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Orders {
    private List<Order> ordersList = new ArrayList<>();
    private Integer i;
    public Orders() {
        this.i=1;
    }
    public void addOrder(Order o){
        ordersList.add(o);
    }
    public void showOrders(){
        ordersList.forEach((e)-> System.out.println((i++)+". "+e));
    }
    public void showOrderDetails(int id){
        System.out.println(ordersList.get(id));
    }
    public void sortedOrdersList(){
        ordersList.stream().sorted(Comparator.comparing(Order::isForDelivery)).forEach(e-> System.out.println(e));
    }
}
