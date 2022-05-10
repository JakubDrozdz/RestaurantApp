package Menu;

import Interfaces.IFileReader;
import Interfaces.IFileWrite;

import java.io.*;

import java.util.HashMap;

public class Menu implements IFileWrite, IFileReader {
    //private final String filepath = "../../../resources/menuList.txt"; //file path which works in when runing program from terminal
    private final String filepath = "resources/menuList.txt";
    private HashMap<Integer, Dish> menuList;
    private Integer id;
    public Menu() {
        this.menuList = new HashMap();
        id = 1;
        readMenu();
    }
    public boolean addToMenu(Dish dish, boolean addToFile){
        boolean flag = false;
        if(addToFile){
            /*try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(filepath,true));
                bw.write("\n"+dish.getName()+";"+dish.getDescription()+";"+ dish.getPrize()+";"+dish.isVegetarian());
                bw.close();
            } catch (FileNotFoundException fnfe) {
                System.out.println("File not found!");
                return false;
            }
            catch(IOException e){
                System.out.println("File error!");
                return false;
            }*/
            flag =  fileWrite(filepath,dish.getName()+";"+dish.getDescription()+";"+ dish.getPrize()+";"+dish.isVegetarian());
        }
        menuList.put((id++),dish);
        return true;
    }
    public void showMenu(){
        System.out.println();
        menuList.forEach((id,dish)->{
            System.out.println(id + ". " + dish);
        });
    }
    private void readMenu(){
        /*try{
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            String line;
            while((line = br.readLine()) != null){
                String[] data = line.split(";");
                Menu.Menu.Dish d;
                if(data[data.length-1].equals("true")){
                    d = new Menu.Menu.DishNoMeat(data[0],data[1],data[2],true);
                }
                else{
                    d = new Menu.Menu.DishMeat(data[0],data[1],data[2],false);
                }
                addToMenu(d,false);
            }
            br.close();

        }catch(FileNotFoundException fnfe){
            System.out.println("File not found!");
        }
        catch(IOException e){
            System.out.println("Read error");
        }*/
        String[][] data = fileRead(filepath);
        for (int i = 0; i < data.length; i++) {
            Dish d;
            if(data[i][data.length-1].equals("true")){
                d = new DishNoMeat(data[i][0],data[i][1],data[i][2],true);
            }
            else{
                d = new DishMeat(data[i][0],data[i][1],data[i][2],false);
            }
            addToMenu(d,false);
        }
    }
    public boolean remove(int dishId){
        menuList.remove(dishId);
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
                if(dishId<=lines){
                    if(currentLine == dishId){
                        flag = true;
                        continue;
                    }
                    else{
                        if(dishId == lines){
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
            menuList.clear();
            readMenu();

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
    public boolean setUnavailable(int dishId){
        if(dishId<=id && dishId>=1){
            menuList.get(dishId).setUnavailable();
            return true;
        }
        else
            return false;
    }
}
