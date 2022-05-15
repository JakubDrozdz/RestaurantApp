package Orders;

import java.util.ArrayList;

public class OrderOnSite extends Order{
    private int tableNo;

    public OrderOnSite(ArrayList orderList, int tableNo) {
        super(orderList,false);
        this.tableNo = tableNo;
    }
}
