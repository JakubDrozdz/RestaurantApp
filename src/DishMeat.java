public class DishMeat extends Dish{
    public DishMeat(String name, String description, String price,boolean isVegetarian) {
        super(name, description, price);
        super.isVegetarian = isVegetarian;
    }
}
