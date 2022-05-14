package Orders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Order {
    private String dateOfOrder;
    private List<Integer> orderList;
    public Order(ArrayList orderList) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/mm/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.dateOfOrder = dtf.format(now);
        this.orderList = orderList;
    }

    public void validate(Order o){
        o.orderList.forEach((e)->{
        });
    }
}
