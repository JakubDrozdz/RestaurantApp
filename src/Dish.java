public class Dish {
    protected String name;
    protected String description;
    protected String price;
    protected boolean isVegetarian;
    protected  boolean available;

    public Dish(String name, String description, String price) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = true;
    }

    public String toString(){
        if(available == false)
            return isVegetarian ? (name + "\n" + description + "\t" + price + " zl\t(wegetarianskie)")
                : (name + "\n" + description + "\t" + price + " zl - NIEDOSTEPNE");
        else
            return isVegetarian ? (name + "\n" + description + "\t" + price + " zl\t(wegetarianskie)")
                    : (name + "\n" + description + "\t" + price + " zl");
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrize() {
        return price;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public void setUnavailable() {
        this.available = false;
    }
}
