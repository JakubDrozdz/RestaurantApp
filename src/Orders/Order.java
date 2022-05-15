package Orders;

import Menu.Dish;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Order {
    private String dateOfOrder;
    private int id;
    private List<Integer> orderList;
    private boolean forDelivery;
    public Order(ArrayList orderList,boolean forDelivery,int id) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/mm/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.dateOfOrder = dtf.format(now);
        this.orderList = orderList;
        this.forDelivery = forDelivery;
        this.id = id;
    }

    @Override
    public String toString() {
        return (id+". data zamówienia: " + dateOfOrder +
                ", zamówione pozycje z menu: " + orderList +
                ", z dostawą: " + (forDelivery?"tak":"nie"));
    }
    public void toString(HashMap<Integer, Dish> menu) {
        System.out.println(
        "Data zamówienia: " + dateOfOrder + ", zamówione pozycje z menu:");
        orderList.forEach(e-> System.out.println(menu.get(e)));
        System.out.println("z dostawą: " + (forDelivery?"tak":"nie"));
    }
    public boolean isForDelivery() {
        return forDelivery;
    }

    public int getId() {
        return id;
    }
}
