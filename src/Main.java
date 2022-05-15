import Employees.Chef;
import Employees.Delivery;
import Employees.Employee;
import Employees.Waiteer;
import Menu.*;
import Orders.Order;
import Orders.OrderForDelivery;
import Orders.OrderOnSite;
import Orders.Orders;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {
    private static int orderId = 1;
    private static final Container<Dish> menu= new Container<>("menu");
    //private static toDelete_Menu menu = new toDelete_Menu();
    private static final Container<Employee> employees= new Container<>("employees");
    private static final Scanner scan = new Scanner(System.in);
    private static final Orders ordersList = new Orders();
    public static void main(String[] args) {
        startRestaurant();
    }
    private static void printActionList(){
        System.out.println("Wybierz odpowiednią cyfrę w celu wykonania konkretnej operacji:");
        System.out.println("1 - wyswietl listę akcji ponownie\n" +
                "2 - wyświetl aktualne menu\n" +
                "3 - dodaj pozycję do menu\n" +
                "4 - usuń pozycję z menu\n" +
                "5 - oznacz danie jako nie dostępne\n" +
                "6 - dodaj pracownika\n" +
                "7 - wyświetl pracowników\n" +
                "8 - zwolnij pracownika\n" +
                "9 - wyświetl informacje o pracowniku\n" +
                "10 - złóż zamówienie\n" +
                "11 - wyświetl wszystkie zamówienia\n" +
                "12 - wyświetl szczegóły zamówienia\n" +
                "13 - kolejność zamówień do realziacji\n" +
                "14 - losowe zamówienie\n" +
                "15 - zakończ\n");
    }
    private static void startRestaurant(){
        System.out.println("Witamy w systemie obsługi restauracji XYZ!");
        printActionList();
        int actionChoosen = 0;
        try{
            actionChoosen = scan.nextInt();
        }
        catch(InputMismatchException ime){
            System.out.println("Podaj poprawną liczbę");
        }

        scan.nextLine();
        while(actionChoosen != 15){
            switch(actionChoosen){
                case 1:
                    printActionList();
                    break;
                case 2:
                    menu.showList();
                    break;
                case 3:
                    addToMenu();
                    break;
                case 4:
                    removeFromMenu();
                    break;
                case 5:
                    setUnavailable();
                    break;
                case 6:
                    addEmployee();
                    break;
                case 7:
                    employees.showList();
                    break;
                case 8:
                    fireEmployee();
                    break;
                case 9:
                    showEmployeeData();
                    break;
                case 10:
                    placeOrder();
                    break;
                case 11:
                    ordersList.showOrders();
                    break;
                case 12:
                    showOrderDetails();
                    break;
                case 13:
                    ordersList.sortedOrdersList();
                    break;
                case 14:
                    randomOrder();
                    break;
                default:
                    System.out.println("Brak okreslonej operacji");
            }
            System.out.println("\nPodaj kolejną operację:");
            try{
                actionChoosen = scan.nextInt();
            }
            catch(InputMismatchException ime){
                System.out.println("Podaj poprawną liczbę");
            }
            scan.nextLine();
        }

    }
    private static void addToMenu(){
        System.out.println("Podaj nazwę dania:" );
        String name = scan.nextLine();
        System.out.println("Podaj opis dania: ");
        String desc = scan.nextLine();
        System.out.println("Podaj cenę dania: ");
        String price = scan.nextLine();
        Pattern pattern = Pattern.compile("\\d{1,3}\\.\\d{2}");
        Matcher matcher = pattern.matcher(price);
        while(!matcher.matches()){
            System.out.println("Podaj poprawną cenę: ");
            price = scan.nextLine();
            matcher = pattern.matcher(price);
        }
        System.out.println("Czy danie jest wegetariańskie: ");
        String isVegetarian = scan.nextLine();
        boolean flag;
        if(isVegetarian.equals("tak")){
            flag = menu.addToList(new DishNoMeat(name,desc,price,true),true, name+";"+ desc+";"+ price+ ";"+"true");
        }
        else{
            flag = menu.addToList(new DishMeat(name,desc,price,false),true,name+";"+ desc+";"+ price+ ";"+"false");
        }
        System.out.println(flag ? "Danie dodane poprawnie" : "Danie nie dodane poprawnie");
    }
    private static void removeFromMenu(){
        System.out.println("Podaj ID dania:");
        int id = validateId();
        scan.nextLine();
        if(id>=0 && menu.remove(id))
            System.out.println("Usunięto poprawnie!");
        else
            System.out.println("Nie można usunąć");
    }
    private static void setUnavailable(){
        System.out.println("Podaj ID dania:");
        int id = validateId();
        scan.nextLine();
        if(id>=0 && menu.setUnavailable(id))
            System.out.println("Danie oznaczone jako niedostepne!");
        else
            System.out.println("Nie można oznaczyć dania");
    }
    private static void addEmployee(){
        System.out.println("Podaj imię:" );
        String firstName = scan.nextLine();
        System.out.println("Podaj nazwisko: ");
        String lastName = scan.nextLine();
        String jobTitle = null;
        System.out.println("Wtbierz stanowisko: " +
                "\n1 - kelner" +
                "\n2 - dostawca" +
                "\n3 - kucharz");
        int actionChoosen = 0;
        try{
            actionChoosen = scan.nextInt();
        }
        catch(InputMismatchException ime){
            System.out.println("Podaj poprawną liczbę");
        }
        while(!(actionChoosen ==10)){
            switch(actionChoosen){
                case 1:
                    jobTitle = "kelner";
                    actionChoosen=10;
                    break;
                case 2:
                    jobTitle = "dostawca";
                    actionChoosen=10;
                    break;
                case 3:
                    jobTitle = "kucharz";
                    actionChoosen=10;
                    break;
                default:
                    System.out.println("Wybierz dostępną opcję");
                    try{
                        actionChoosen = scan.nextInt();
                    }
                    catch(InputMismatchException ime){
                        System.out.println("Podaj poprawną liczbę");
                    }
            }
        }
        scan.nextLine();
        System.out.println("Podaj numer telefonu pracownika (9 cyfr):" );
        String telephoneNumber = scan.nextLine();
        Pattern pattern = Pattern.compile("\\d{9}");
        Matcher matcher = pattern.matcher(telephoneNumber);
        while(!matcher.matches()){
            System.out.println("Podaj poprawną cenę: ");
            telephoneNumber = scan.nextLine();
            matcher = pattern.matcher(telephoneNumber);
        }
        boolean flag = false;
                //employees.addToList(new Employee(firstName,lastName,telephoneNumber,jobTitle,0.0),
                //true, firstName+";"+lastName+";"+jobTitle+";"+"0.0");
        if(jobTitle.equals("kelner"))
            flag = employees.addToList(new Waiteer(firstName,lastName,telephoneNumber,jobTitle,0.0),
                    true, firstName+";"+lastName+";"+telephoneNumber+";"+jobTitle+";"+"0.0");
        if (jobTitle.equals("kucharz"))
            flag = employees.addToList(new Chef(firstName,lastName,telephoneNumber,jobTitle,0.0),
                    true, firstName+";"+lastName+";"+telephoneNumber+";"+jobTitle+";"+"0.0");
        if(jobTitle.equals("dostawca"))
            flag = employees.addToList(new Delivery(firstName,lastName,telephoneNumber,jobTitle,0.0),
                    true, firstName+";"+lastName+";"+telephoneNumber+";"+jobTitle+";"+"0.0");
        System.out.println(flag ? "Pracownik dodany poprawnie" : "Pracownik nie dodany poprawnie");
    }
    private static void fireEmployee(){
        System.out.println("Podaj ID pracownika:");
        int id = validateId();
        scan.nextLine();
        if(id>=0 && employees.remove(id))
            System.out.println("Pracownik zwolniony!");
        else
            System.out.println("Nie można usunąć");
    }
    private static void showEmployeeData(){
        int id = 0;
        boolean flag = false;
        while(!flag){
            try{
                System.out.println("Podaj id pracownika, którego dane chcesz wyświetlić");
                id = scan.nextInt();
                if(id>employees.getSize()){
                    System.out.println("Podaj poprawną liczbę");
                    flag = false;
                }
                else
                    flag = true;
            }
            catch(InputMismatchException ime){
                System.out.println("Podaj poprawną liczbę");
                flag = false;
            }
        }
        employees.showData(id);
    }
    private static void placeOrder(){
        ArrayList<Integer> orderList = new ArrayList<>();
        int action = 0;
        String finish = "n";
        System.out.println("Jeśli chcesz zobaczyć menu naciśnij 0\n");
        boolean end = false;
        boolean show = true;
        while(!end){
            try{
                System.out.println("Podaj pozycję z menu");
                action = scan.nextInt();
                scan.nextLine();
                if(action == 0)
                    menu.showList();
                else
                    orderList.add(action);
                if(show){
                    System.out.println("\nJeśli chcesz zakończyć naciśnij y");
                    show = false;
                }
                System.out.println("Czy chcesz zakończyć?");
                finish = scan.nextLine();
                if(finish.equals("y"))
                    end = true;
                else
                    end = false;
            }
            catch(InputMismatchException ime){
                scan.nextLine();
                System.out.println("Podaj poprawną liczbę");
            }
        }
        /*ArrayList<Integer> indexToRemove = new ArrayList<>();
        for (int i = 0; i<orderList.size();i++) {
            int val = orderList.get(i);
            if(val>=1 && val<=menu.getList().size()){
                if(!menu.getList().get(orderList.get(i)).isAvailable()){
                    indexToRemove.add(i);
                    System.out.println("Danie: " + menu.getList().get(orderList.get(i)).getName() + " jest niedostępne - zostanie usunięte z zamówienia!");
                }

            }else{
                System.out.println("Nie posiadamy dania o id " + orderList.get(i));
                indexToRemove.add(i);
            }
        }
        int count = 0;
        for (Integer i : indexToRemove) {
            orderList.remove(i - count);
            count++;
        }*/
        orderList = validateOrder(orderList);
        if(orderList.size() == 0){
            System.out.println("Nie wybrałeś żadnej poprawnej pozycji z menu!");
        }
        else{
            String orderType = null;
            System.out.println("Zamówienie z dostawą czy na miejscu?\nWybierz 1 - na miejscu lub 2 - z dostawą");
            end = false;
            Order order = null;
            while(!end){
                String address = "";
                int tableId = 0;
                try{
                    System.out.println("Podaj numer: ");
                    action = scan.nextInt();
                    scan.nextLine();
                    while(tableId<=0){
                        if(action == 1){
                            System.out.println("Wprowadź numer stolika: (1,2,...)");
                            tableId = validateId();
                        }
                        if(action == 2)
                            System.out.println("Wprowadź adres dostawy");
                            address = scan.nextLine();
                            break;
                    }
                    switch (action){
                        case 1:
                            order = new OrderOnSite(orderList,tableId,orderId++);
                            end = true;
                            break;
                        case 2:
                            order = new OrderForDelivery(orderList,address,orderId++);
                            end = true;
                            break;
                        default:
                            System.out.println("Nie ma takiej opcji");
                    }
                }catch(InputMismatchException ime){
                    System.out.println("Podaj poprawny numer!");
                }
            }
            ordersList.addOrder(order);
        }
    }
    private static void showOrderDetails(){
        System.out.println("Podaj ID zamówienia:");
        int id = validateId();
        ordersList.showOrderDetails(id-1,menu.getList());
    }
    private static void randomOrder(){
        ArrayList<Integer> orderList = new ArrayList<>();
        System.out.println("Podaj ilość pozycji z menu do wylosowania");
        int noOfDish = validateId();
        if(noOfDish > 0){
            int size = menu.getSize();
            for(Integer i = 1; i <= noOfDish; i++){
                int randNo = (int)(Math.random() * size) + 1;
                if(!orderList.contains(randNo))
                    orderList.add(randNo);
                else
                    i--;
            }
            orderList = validateOrder(orderList);
            if(orderList.size() == 0){
                System.out.println("Losuję jeszcze raz");
                randomOrder();
            }
            boolean end = false;
            int action = -1;
            String orderType = null;
            System.out.println("Zamówienie z dostawą czy na miejscu?\nWybierz 1 - na miejscu lub 2 - z dostawą");
            while(!end){
                String address = "";
                int tableId = 0;
                try{
                    System.out.println("Podaj numer: ");
                    action = scan.nextInt();
                    scan.nextLine();
                    while(tableId<=0){
                        if(action == 1){
                            System.out.println("Wprowadź numer stolika: (1,2,...)");
                            tableId = validateId();
                        }
                        if(action == 2)
                            System.out.println("Wprowadź adres dostawy");
                        address = scan.nextLine();
                        break;
                    }
                    switch (action){
                        case 1:
                            ordersList.addOrder(new OrderOnSite(orderList,tableId,orderId++));
                            end = true;
                            break;
                        case 2:
                            ordersList.addOrder(new OrderForDelivery(orderList,address,orderId++));
                            end = true;
                            break;
                        default:
                            System.out.println("Nie ma takiej opcji");
                    }
                }
                catch(InputMismatchException ime){
                    System.out.println("Podaj poprawny numer!");
                }
            }
        }
    }

    private static int validateId(){
        int id = -1;
        try{
            id = scan.nextInt();
        }catch(InputMismatchException ime){
            System.out.println("Błąd! Podaj cyfrę!!!");
        }
        return id;
    }
    private static ArrayList<Integer> validateOrder(ArrayList<Integer> orderList){
        ArrayList<Integer> indexToRemove = new ArrayList<>();
        for (int i = 0; i<orderList.size();i++) {
            int val = orderList.get(i);
            if(val>=1 && val<=menu.getList().size()){
                if(!menu.getList().get(orderList.get(i)).isAvailable()){
                    indexToRemove.add(i);
                    System.out.println("Danie: " + menu.getList().get(orderList.get(i)).getName() + " jest niedostępne - zostanie usunięte z zamówienia!");
                }

            }else{
                System.out.println("Nie posiadamy dania o id " + orderList.get(i));
                indexToRemove.add(i);
            }
        }
        int count = 0;
        for (Integer j : indexToRemove) {
            orderList.remove(j - count);
            count++;
        }
        return orderList;
    }
}
