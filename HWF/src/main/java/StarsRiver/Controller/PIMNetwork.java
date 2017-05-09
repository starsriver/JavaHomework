package StarsRiver.Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.*;
import StarsRiver.Controller.base.*;
/**
 * PIMNetwork
 * @auther 乔新文
 * @StudentID 14130140393
 * @Email starsriver@outlook.com
 */
public class PIMNetwork implements PIMStore{

    public PIMNetwork () {
        serviceUrl = null;
        in = null;
        out = null; 
        connection = null;  
    }
    private final String GetAll = "/api/get/all";
    private final String GetTodos = "/api/get/todos";
    private final String GetNotes = "/api/get/notes";
    private final String GetAppointments = "/api/get/appointments";
    private final String GetContacts = "/api/get/contacts";
    private final String PostAll = "/api/save";

    private URL serviceUrl = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private URLConnection connection = null;
    public boolean Open(String[] str){
        InitUrl(str);
        if(serviceUrl == null){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean Write(String[] str){
        String result = "";
        if(serviceUrl == null){
            System.out.println("Url Error");
            return false;
        }
        try{
            connection = serviceUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setDoOutput(true);
            connection.setDoInput(true);
            out = new PrintWriter(connection.getOutputStream());

            Gson gson = new Gson();
            String json = gson.toJson(str);

            out.print(json);
            out.flush();

            in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF8"));
            String line = "";
            while((line = in.readLine())!=null){
                result += line;
            }
            int num = 0;
            try{
                num = Integer.parseInt(result);
                if(num >= 0){
                    return true;
                }
                else{
                    return false;
                }
            }
            catch(Exception e){
                return false;
            }
        }
        catch(Exception e){
            System.out.println("Post Request Error!");
            return false;
        }
    }
    public String[] Read(){
        String result = "";
        if(serviceUrl == null){
            System.out.println("Url Error");
            return null;
        }
        try{
            connection = serviceUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            connection.connect();

            in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF8"));
            String line;
            while((line = in.readLine()) != null){
                result += line;
            }
        }
        catch(Exception e){
            System.out.println("Get Request Error!");
            return null;
        }
        Gson gson = new Gson();
        
        String[] resultes = gson.fromJson(result, String[].class);
        return resultes;
    }
    public void Close(){
        serviceUrl = null;
        connection = null;
        try{
            if(in != null){
                in.close();
            }
            if(out != null){
                out.close();
            }
        }
        catch(Exception e){
            System.out.println("Close Error!");
        }
    }

    private void InitUrl(String[] str){
        if(str.length != 2){
            serviceUrl = null;
        }
        String Urlstr = "http://";
        str[1] = str[1].toLowerCase();
        if(str[1].contains("all")){
            Urlstr += str[0] + GetAll;
        }
        else if(str[1].contains("note")){
            Urlstr += str[0] + GetNotes;
        }
        else if(str[1].contains("todo")){
            Urlstr += str[0] + GetTodos;
        }
        else if(str[1].contains("appo")){
            Urlstr += str[0] + GetAppointments;
        }
        else if(str[1].contains("cont")){
            Urlstr += str[0] + GetContacts;
        }
        else if(str[1].contains("post")){
            Urlstr += str[0] + PostAll;
        }
        else{
            serviceUrl =  null;
        }
        try{
            URL resultUrl = new URL(Urlstr);
            serviceUrl =  resultUrl;
        }
        catch(Exception e){
            serviceUrl = null;
        }
    }

    public static void main(String[] args) {
        // PIMNetwork n = new PIMNetwork();
        // System.out.println("all");
        // n.Open(new String[]{"localhost:8088","all"});
        // String[] r = n.Read();
        // for(String i : r){
        //     System.out.println(i);
        // }
        // n.Close();

        // System.out.println("appointment");
        // n.Open(new String[]{"localhost:8088","appointment"});
        // r = n.Read();
        // for(String i : r){
        //     System.out.println(i);
        // }
        // n.Close();

        // System.out.println("todo");
        // n.Open(new String[]{"localhost:8088","todo"});
        // r = n.Read();
        // for(String i : r){
        //     System.out.println(i);
        // }
        // n.Close();

        // System.out.println("note");
        // n.Open(new String[]{"localhost:8088","note"});
        // r = n.Read();
        // for(String i : r){
        //     System.out.println(i);
        // }
        // n.Close();

        // System.out.println("cont");
        // n.Open(new String[]{"localhost:8088","cont"});
        // r = n.Read();
        // for(String i : r){
        //     System.out.println(i);
        // }
        // n.Close();

        // PIMNetwork n = new PIMNetwork();
        // n.Open(str)

        PIMNetwork n = new PIMNetwork();
        System.out.println("post");
        n.Open(new String[]{"localhost:8088","post"});
        boolean r = n.Write(new String[]{"aaaaaaaa","bbbbbbbbb","ccccccccccc"});
        System.out.println(r);
        n.Close();
    }
}