package Orders;

import java.util.ArrayList;

public class OrderOnSite extends Order{
    private int tableNo;

    public OrderOnSite(ArrayList orderList, int tableNo,int id) {
        super(orderList,false,id);
        this.tableNo = tableNo;
    }
}
