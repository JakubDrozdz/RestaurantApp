import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {
    private static Menu menu = new Menu();
    private static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        startRestaurant();
        //menu.addToMenu(new DishNoMeat("Toffu curry","curry z tofu","36.99", true),true);
    }

    private static void printActionList(){
        System.out.println("Wybierz odpowiednią cyfrę w celu wykonania konkretnej operacji:");
        System.out.println("1 - wyswietl listę akcji ponownie\n" +
                "2 - wyświetl aktualne menu\n" +
                "3 - dodaj pozycję do menu\n" +
                "4 - usuń pozycję z menu\n" +
                "5 - oznacz danie jako nie dostępne\n" +
                "10 - zakończ\n");
    }
    private static void startRestaurant(){
        System.out.println("Witamy w systemie obsługi restauracji XYZ!");
        printActionList();
        int actionChoosen = scan.nextInt();
        scan.nextLine();
        while(actionChoosen != 10){
            switch(actionChoosen){
                case 1:
                    printActionList();
                    break;
                case 2:
                    showMenu();
                    break;
                case 3:
                    addToMenu();
                    break;
                case 4:
                    removeFromMenu();
                    break;
                case 5:
                    removeFromMenu();
                    break;
                default:
                    System.out.println("Brak okreslonej operacji");
            }
            System.out.println("\nPodaj kolejną operację:");
            actionChoosen = scan.nextInt();
            scan.nextLine();
        }

    }
    private static void showMenu(){
        menu.showMenu();
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
        Boolean flag = null;
        if(isVegetarian.equals("tak")){
            flag = menu.addToMenu(new DishNoMeat(name,desc,price,true),true);
        }
        else{
            flag = menu.addToMenu(new DishMeat(name,desc,price,false),true);
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
    private static void setUnnavailable(){
        System.out.println("Podaj ID dania:");
        int id = scan.nextInt();
        scan.nextLine();
        if(menu.setUnavailable(id))
            System.out.println("Danie oznaczone jako niedostepne!");
        else
            System.out.println("Nie można oznaczyć dania");
    }
}
