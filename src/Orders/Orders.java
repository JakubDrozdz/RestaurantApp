package Orders;

import Menu.Dish;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Orders {
    private ArrayList<Order> ordersList = new ArrayList<>();
    //private Integer i;
    public Orders() {
        //this.i=1;
    }
    public void addOrder(Order o){
        ordersList.add(o);
    }
    public void showOrders(){
        ordersList.forEach((e)-> System.out.println(e));
    }
    public void showOrderDetails(int id, HashMap<Integer, Dish> menu){
        if(id >= 0 && id < ordersList.size())
            ordersList.get(id).toString(menu);
        else
            System.out.println("Nie ma takiego zamówienia");
    }
    public void sortedOrdersList(){
        ordersList.stream().sorted(Comparator.comparing(Order::isForDelivery)).forEach(e-> System.out.println(e));
    }
    public ArrayList sortedOrdersListBgc(){
        List<Order> list = ordersList.stream().sorted(Comparator.comparing(Order::isForDelivery)).toList();
        ArrayList<Order> aList = new ArrayList<>(list);
        return aList;
    }
    public int getSize(){
        return ordersList.size();
    }
    public Order getOrder(int id){
        return ordersList.get(id);
    }
    public ArrayList getList(){
        return ordersList;
    }
    public void clear(){
        ordersList.clear();
    }
    public  void delete(int id){
        for (int i = 0; i < ordersList.size(); i++) {
            if(ordersList.get(i).getId() == id){
                ordersList.remove(i);
            }
        }
    }
}
