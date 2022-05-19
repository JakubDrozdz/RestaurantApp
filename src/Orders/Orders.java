package Orders;

import Menu.Dish;

import java.util.*;

public class Orders {
    private ArrayList<Order> ordersList = new ArrayList<>();
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
        List<Order> list = ordersList.stream().sorted(Comparator.comparing((Order::isForDelivery))).toList().stream().sorted(Comparator.comparing(Order::isLapsed)).toList();
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
    public  void delete(int id){
        for (int i = 0; i < ordersList.size(); i++) {
            if(ordersList.get(i).getId() == id){
                ordersList.remove(i);
            }
        }
    }
    public int getMenuPositionsNo(int id){
        return ordersList.get(id).getMenuPositionsNo();
    }
}
