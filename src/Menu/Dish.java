package Menu;

public abstract class Dish {
    protected String name;
    protected String description;
    protected String price;
    protected boolean isVegetarian;
    protected boolean available;

    public Dish(String name, String description, String price) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = true;
    }

    public String toString(){
        if(!available)
            return isVegetarian ? (name + "\n" + description + "\t" + price + " zl\t(wegetarianskie) - NIEDOSTEPNE")
                : (name + "\n" + description + "\t" + price + " zl - NIEDOSTEPNE");
        else
            return isVegetarian ? (name + "\n" + description + "\t" + price + " zl\t(wegetarianskie)")
                    : (name + "\n" + description + "\t" + price + " zl");
    }
    public String getName() {
        return this.name;
    }
    public String getPrize() {
        return this.price;
    }
    public void setUnavailable() {
        this.available = false;
    }
    public boolean isAvailable(){
        return this.available;
    }
}
