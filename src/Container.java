import Employees.Employee;
import Interfaces.IFileReader;
import Interfaces.IFileWrite;
import Menu.Dish;
import Menu.DishMeat;
import Menu.DishNoMeat;

import java.io.*;
import java.util.HashMap;

public class Container <T> implements IFileWrite, IFileReader {
    //private String filepath = "../../../resources/menuList.txt"; //file path which works in when runing program from terminal
    private String filepath;
    private HashMap<Integer, T> list;
    private Integer id;
    private String objName;
    public Container(String objName) {
        this.list = new HashMap();
        this.id = 1;
        this.objName = objName;
        this.filepath ="resources/" + this.objName + "List.txt";
        //this.filepath ="../../../resources/" + this.objName + "List.txt";
        readList();
    }
    public boolean addToList(T t, boolean addToFile, String dataString){
        boolean flag = false;
        if(addToFile){
            if(this.objName.equals("menu"))
                flag =  fileWrite(filepath, dataString);
            if(this.objName.equals("employees"))
                flag =  fileWrite(filepath, dataString);
        }
        list.put((id++),t);
        return flag;
    }
    public void showList(){
        System.out.println();
        list.forEach((i,t)->{
            System.out.println(i + ". " + t);
        });
    }
    private void readList(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            String line;
            while((line = br.readLine()) != null){
                String[] data = line.split(";");
                T t = null;
                if(this.objName.equals("menu")){
                    if(data[data.length-1].equals("true")){
                        t = (T) new DishNoMeat(data[0],data[1],data[2],true);
                    }
                    else{
                        t = (T) new DishMeat(data[0],data[1],data[2],false);
                    }
                }
                if(this.objName.equals("employees")){
                    t = (T) new Employee(data[0],data[1],data[2],Double.parseDouble(data[3]));
                }
                addToList(t,false,null);
            }
            br.close();
        }catch(FileNotFoundException fnfe){
            System.out.println("File not found!");
        }
        catch(IOException e){
            System.out.println("Read error");
        }
        /*String[][] data = fileRead(filepath);
        for (int i = 0; i < data.length; i++) {
            T t;
            if(data[i][data.length-1].equals("true")){
                t = (T) new DishNoMeat(data[i][0],data[i][1],data[i][2],true);
            }
            else{
                t = (T) new DishMeat(data[i][0],data[i][1],data[i][2],false);
            }
            addToMenu(t,false);
        }*/

    }

    public boolean remove(int tId){
        list.remove(tId);
        boolean flag=false;
        try{
            File inputFile = new File(filepath);
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            File tempFile = new File("resources/tempMenu.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            String line;
            int currentLine=0;
            int lines = 0;
            while (br.readLine() != null) lines++;
            br.close();
            br=new BufferedReader(new FileReader(inputFile));
            while((line = br.readLine()) != null){
                currentLine++;
                if(tId<=lines){
                    if(currentLine == tId){
                        flag = true;
                        continue;
                    }
                    else{
                        if(tId == lines){
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
            list.clear();
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
    public boolean setUnavailable(int tId){
        if(this.objName.equals("menu")){
            if(tId<=id && tId>=1){
                ((Dish)list.get(tId)).setUnavailable();
                return true;
            }
            else
                return false;
        }
        return false;
    }
}
