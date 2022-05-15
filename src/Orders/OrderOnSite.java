package Orders;

import java.util.ArrayList;

public class OrderOnSite extends Order{

    public OrderOnSite(ArrayList orderList, int tableNo,int id) {
        super(orderList,false,id);
        super.tableNo = tableNo;
    }

}
