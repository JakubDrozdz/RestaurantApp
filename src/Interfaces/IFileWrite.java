package Interfaces;

import java.io.*;

public interface IFileWrite {
    default boolean fileWrite(String filePath, String dataString){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath,true));
            bw.write("\n"+dataString);
            bw.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found!");
            return false;
        }
        catch(IOException e){
            System.out.println("File error!");
            return false;
        }
        return true;
    }
}
