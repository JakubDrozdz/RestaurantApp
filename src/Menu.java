
import java.io.*;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Menu {
    //private String filepath = "../../../resources/menuList.txt"; //file path which works in when runing program from terminal
    private String filepath = "resources/menuList.txt";
    private HashMap<Integer,Dish> menuList;
    private Integer id;
    public Menu() {
        this.menuList = new HashMap();
        id = 1;
        readMenu();
    }
    public boolean addToMenu(Dish dish,boolean addToFile){
        if(addToFile){
            try {
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
            }
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
        try{
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            String line;
            while((line = br.readLine()) != null){
                String[] data = line.split(";");
                Dish d;
                if(data[data.length-1].equals("true")){
                    d = new DishNoMeat(data[0],data[1],data[2],true);
                }
                else{
                    d = new DishMeat(data[0],data[1],data[2],false);
                }
                addToMenu(d,false);
            }
            br.close();

        }catch(FileNotFoundException fnfe){
            System.out.println("File not found!");
        }
        catch(IOException e){
            System.out.println("Read error");
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
                if(currentLine == dishId){
                    flag = true;
                    continue;
                }
                else{
                    if(currentLine == lines){
                        writer.write(line);
                    }
                    else{
                        writer.write(line + System.getProperty("line.separator"));
                    }
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
