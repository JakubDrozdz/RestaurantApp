package Employees;

import Menu.DishMeat;
import Menu.DishNoMeat;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public abstract class Employee{
    protected String lastName;
    protected String firstName;
    protected String telephoneNumber;
    protected Double tip;
    protected String jobTitle;
    protected boolean ready;
    protected int ordersDone;
    public Employee(String firstName, String lastName, String telephoneNumber, String jobTitle,Double tip) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.tip = tip;
        this.jobTitle = jobTitle;
        this.telephoneNumber = telephoneNumber;
        this.ready = true;
        this.ordersDone = 0;
    }

    public String toString(){
        return firstName + " " + lastName + ", stanowisko: " + jobTitle + ", wysokość napiwku: " + tip;
    }
    public String toString(boolean details){
        return firstName + " " + lastName + ", stanowisko: " + jobTitle
                +", numer telefonu: "+ telephoneNumber+ ", wysokość napiwku: " + tip + ", zrealizowane zamówienia: " + ordersDone;
    }
    public String getJobTitle() {
        return jobTitle;
    }
    public static int countEmpOnPosition(Employee e){
        int counter = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader("resources/employeesList.txt"));
            String line;
            while((line = br.readLine()) != null){
                String[] data = line.split(";");
                String pos = data[3];
                if(pos.equals(e.getJobTitle()))
                    counter++;
            }
            br.close();
        }catch(FileNotFoundException fnfe){
            System.out.println("Counting employees on position: File not found!");
        }
        catch(IOException ioe){
            System.out.println("Counting employees on position: Read error");
        }
        return counter;
    }
    public static boolean canDeleteEmployee(Object e){
        int noOfEmployees = countEmpOnPosition((Employee) e);
        if(noOfEmployees > 1)
            return true;
        else
            return false;
    }
    public void setTip(Double tip) {
        this.tip = tip;
    }
    public boolean isReady() {
        return ready;
    }
    public void setReady(boolean ready) {
        this.ready = ready;
    }
    public void addOrderDone() {
        this.ordersDone++;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }
}
