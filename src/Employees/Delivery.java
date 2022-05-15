package Employees;

public class Delivery extends Employee{
    protected boolean ready;
    public Delivery(String firstName, String lastName,String telephoneNumber, String jobTitle, Double tip) {
        super(firstName, lastName,telephoneNumber, jobTitle, tip);
        this.ready = true;
    }
}
