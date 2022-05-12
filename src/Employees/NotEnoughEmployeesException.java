package Employees;

public class NotEnoughEmployeesException extends Exception{
    public NotEnoughEmployeesException(String message) {
        super(message);
    }
}
