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
        int id = scan.nextInt();
        scan.nextLine();
        if(menu.remove(id))
            System.out.println("Usunięto poprawnie!");
        else
            System.out.println("Nie można usunąć");
    }
    private static void setUnavailable(){
        System.out.println("Podaj ID dania:");
        int id = scan.nextInt();
        scan.nextLine();
        if(menu.setUnavailable(id))
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
        int id = scan.nextInt();
        scan.nextLine();
        if(employees.remove(id))
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
        System.out.println("Jeśli chcesz zobaczyć menu naciśnij 0\nJeśli chcesz zakończyć naciśnij y");
        boolean end = false;
        while(!end){
            try{
                System.out.println("Podaj pozycję z menu");
                action = scan.nextInt();
                scan.nextLine();
                if(action == 0)
                    menu.showList();
                else
                    orderList.add(action);
                System.out.println("Czy chcesz zakończyć?");
                finish = scan.nextLine();
                if(finish.equals("y"))
                    end = true;
                else
                    end = false;
            }
            catch(InputMismatchException ime){
                System.out.println("Podaj poprawną liczbę");
            }
        }

        System.out.println("befor");
        for (Integer i : orderList) {
            System.out.println(i);
        }

        ArrayList<Integer> indexToRemove = new ArrayList<>();
        for (int i = 0; i<orderList.size();i++) {
            int val = orderList.get(i);
            if(val>=1 && val<=menu.getList().size()){
                if(!menu.getList().get(orderList.get(i)).isAvailable())
                    indexToRemove.add(i);
            }else{
                indexToRemove.add(i);
            }
        }
        int count = 0;
        for (Integer i : indexToRemove) {
            orderList.remove(i - count);
            count++;
        }

        System.out.println("after");
        for (Integer i : orderList) {
            System.out.println(i);
        }

        String orderType = null;
        System.out.println("Zamówienie z dostawą czy na miejscu?\nWybierz 1 - dostawa lub 2 - na miejscu");
        end = false;
        Order order = null;
        while(!end){
            try{
                System.out.print("Podaj numer: ");
                action = scan.nextInt();
                scan.nextLine();
                switch (action){
                    case 1:
                        order = new OrderForDelivery(orderList,"address");
                        end = true;
                        break;
                    case 2:
                        order = new OrderOnSite(orderList,1);
                        end = true;
                        break;
                    default:
                        System.out.println("Nie ma takiej opcji");
                }
                System.out.println();
            }catch(InputMismatchException ime){
                System.out.println("Podaj poprawny numer!");
            }
        }

        order.validate(order);
        ordersList.addOrder(order);
    }
}
