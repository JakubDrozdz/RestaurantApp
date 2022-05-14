package Employees;

import Menu.DishMeat;
import Menu.DishNoMeat;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Employee{
    protected String lastName;
    protected String firstName;
    protected Double tip;
    protected String jobTitle;

    public Employee(String firstName, String lastName, String jobTitle,Double tip) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.tip = tip;
        this.jobTitle = jobTitle;
    }

    public String toString(){
        return jobTitle + ": " + firstName + " " + lastName + ", napiwek: " + + tip;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Double getTip() {
        return tip;
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
                String pos = data[2];
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

    /*
    public boolean addEmployee(Employee employee, boolean addToFile){
        boolean flag = false;
        if(addToFile){
            //flag =  fileWrite(filepath,employee.getName()+";"+employee.getDescription()+";"+ employee.getPrize()+";"+employee.isVegetarian());
        }
        employeesList.put((id++),employee);
        return true;
    }
    public void listEmployees(){
        System.out.println();
        employeesList.forEach((id,dish)->{
            System.out.println(id + ". " + dish);
        });
    }
    private void readList(){
        String[][] data = fileRead(filepath);
        for (int i = 0; i < data.length; i++) {
            Employee e = null;
            if(data[i][data.length-1].equals("true")){
                //e = new DishNoMeat(data[i][0],data[i][1],data[i][2],true);
            }
            else{
                //e = new DishMeat(data[i][0],data[i][1],data[i][2],false);
            }
            addEmployee(e,false);
        }
    }
    public boolean remove(int employeeId){
        employeesList.remove(employeeId);
        boolean flag=false;
        try{
            File inputFile = new File(filepath);
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            File tempFile = new File("resources/tempEmployee.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            String line;
            int currentLine=0;
            int lines = 0;
            while (br.readLine() != null) lines++;
            br.close();
            br=new BufferedReader(new FileReader(inputFile));
            while((line = br.readLine()) != null){
                currentLine++;
                if(employeeId<=lines){
                    if(currentLine == employeeId){
                        flag = true;
                        continue;
                    }
                    else{
                        if(employeeId == lines){
                            if((currentLine == lines-1)){
                                writer.write(line);
                            }
                            else{
                                writer.write(line + System.getProperty("line.separator"));
                            }
                        }
                        else{
                            if((currentLine == lines)){
                                writer.write(line);
                            }
                            else{
                                writer.write(line + System.getProperty("line.separator"));
                            }
                        }

                    }
                }
                else{
                    if((currentLine == lines)){
                        writer.write(line);
                    }
                    else
                        writer.write(line + System.getProperty("line.separator"));
                }
            }

            br.close();
            writer.close();
            tempFile.renameTo(inputFile);
            id=1;
            employeesList.clear();
            readList();

        }catch(FileNotFoundException fnfe){
            System.out.println("File not found!");
            return false;
        }
        catch(IOException e){
            System.out.println("Read error");
            return false;
        }
        return flag;
    }
     */
}
