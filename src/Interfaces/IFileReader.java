package Interfaces;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public interface IFileReader {
    default String[][] fileRead(String filePath){
        String[][] dataStrings = null;
        try{
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            int counter = 0;
            while(br.readLine() != null){
                ++counter;
            }
            dataStrings = new String[counter][];
            br.close();
            br = new BufferedReader(new FileReader(filePath));
            int i=0;
            while((line = br.readLine()) != null){
                dataStrings[i] = line.split(";");
                i++;
            }
            br.close();

        }catch(FileNotFoundException fnfe){
            System.out.println("File not found!");
        }
        catch(IOException e){
            System.out.println("Read error");
        }
        return dataStrings;
    }
}
