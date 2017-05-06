package StarsRiver.Controller;

import StarsRiver.Controller.base.*;
/**
 * PIMFile
 * @auther 乔新文
 * @StudentID 14130140393
 * @Email starsriver@outlook.com
 */
import java.util.*;
import java.io.*;
public class PIMFile implements PIMStore{

    public PIMFile () {
        file = null;
    }
    private File file;
    public boolean Open(String[] str){
        try{
            file = new File(str[0]);
            if(file.exists()&&file.isFile()){
                //file.delete();
            }
            else{
                file.createNewFile();
            }
            return true;
        }
        catch(Exception e){
            System.out.println("Open File failed!.");
            file = null;
            return false;
        }
    }
    
    public boolean Write(String[] str){
        if(file == null){
            System.out.println("Write File failed!.");
            return false;
        }
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (String var : str) {
                bw.write(var);
                bw.newLine();
            }
            bw.flush();
            bw.close();
            return true;
        }
        catch(Exception e){
            System.out.println("Write File failed!.");
            return false;
        }

    }
    public String[] Read(){
        if(file == null){
            System.out.println("Read File failed!.");
            return null;
        }
        try{
            ArrayList<String> tempList = new ArrayList<String>();
            String temp = null;
            BufferedReader br = new BufferedReader(new FileReader(file));
            while((temp = br.readLine()) != null){
                tempList.add(temp);
            }
            br.close();
            return tempList.toArray(new String[tempList.size()]);
        }
        catch(Exception e){
            System.out.println("Read File failed!.");
            return null;
        }
    }
    public void Close(){

    }
}