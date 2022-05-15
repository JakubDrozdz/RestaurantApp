package Orders;

import Menu.Dish;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Order {
    protected String dateOfOrder;
    protected int id;
    protected List<Integer> orderList;
    protected boolean forDelivery;
    protected String deliveryAddress;
    protected int tableNo;
    protected boolean isReady;
    public Order(ArrayList orderList,boolean forDelivery,int id) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/mm/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.dateOfOrder = dtf.format(now);
        this.orderList = orderList;
        this.forDelivery = forDelivery;
        this.id = id;
        this.isReady = false;
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
        orderList.forEach(e-> System.out.println(menu.get(e))
        );
        System.out.println("z dostawą: " + (forDelivery?("tak\n" + "Adres dostawy: " + deliveryAddress):("nie\n" + "Numer stolika: " + tableNo)));

    }
    public boolean isForDelivery() {
        return forDelivery;
    }

    public int getId() {
        return id;
    }
}
