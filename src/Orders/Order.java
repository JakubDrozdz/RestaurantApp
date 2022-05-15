package Orders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Order {
    private String dateOfOrder;
    private List<Integer> orderList;
    private boolean forDelivery;
    public Order(ArrayList orderList,boolean forDelivery) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/mm/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.dateOfOrder = dtf.format(now);
        this.orderList = orderList;
        this.forDelivery = forDelivery;
    }

    @Override
    public String toString() {
        return "Order{" +
                "dateOfOrder='" + dateOfOrder + '\'' +
                ", orderList=" + orderList +
                ", forDelivery=" + forDelivery +
                '}';
    }

    public boolean isForDelivery() {
        return forDelivery;
    }
}
