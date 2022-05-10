package Menu;

public class DishNoMeat extends Dish {
    public DishNoMeat(String name, String description, String price,boolean isVegetarian) {
        super(name, description, price);
        super.isVegetarian = isVegetarian;
    }
}
