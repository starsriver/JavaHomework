package StarsRiver.Model;

import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import StarsRiver.Model.base.*;
import StarsRiver.Controller.base.*;
import StarsRiver.Controller.*;
/**
 * PIMManager
 *  @auther 乔新文
 * @StudentID 14130140393
 * @Email starsriver@outlook.com
 */
public class PIMManager{
    public PIMManager (String StoreType) {
        if(StoreType == null){
            StoreType = "File";
        }
        switch(StoreType.toLowerCase()){
            case "file":{
                pStore = new PIMFile();
                break;
            }
            case "network":{
                pStore = new PIMNetwork();
                break;
            }
            case "database":{
                pStore = new PIMDataBase();
                break;
            }
            default:{
                pStore = new PIMFile();
            }
        }

        PIMEntities = new PIMEntity[100];
        PIMEntityCount = 0;
        sc = new Scanner(System.in);
    }
    private PIMEntity[] PIMEntities;
    private int PIMEntityCount;
    private Scanner sc;

    private PIMStore pStore;

    public void Run(){
        System.out.println("Welcome to PIM.");
        String command = "";
        while(true){
            System.out.println("---Enter a command (suported commands are List Create Save Load Quit)---");
            command = sc.nextLine();
            switch(command.toLowerCase()){
                case "list":{
                    List();
                    break;
                }
                case "create":{
                    Create();
                    break;
                }
                case "save":{
                    Save();
                    break;
                }
                case "load":{
                    Load();
                    break;
                }
                case "quit":{
                    return;
                }
                default:{
                    System.out.println("Command Not Find.");
                }
            }
        }
    }
    public void List(){
        System.out.printf("There are %d items.\n",PIMEntityCount);
        for(int i = 0; i < PIMEntityCount; i++){
            System.out.printf("Item %d:", i);
            System.out.println(PIMEntities[i].toString());
        }
    }

    public void Create(){
        String PIMtype= "";
        PIMEntity PIMItem = null;
        System.out.println("Enter an item type ( todo, note, contact or appointment ) or quit to exit create item");
        while(PIMItem == null){
            PIMtype = sc.nextLine();
            switch(PIMtype.toLowerCase()){
                case "todo":{
                    PIMItem = CreatePIMTodo();
                    break;
                }

                case "note":{
                    PIMItem = CreatePIMNote();
                    break;
                }

                case "contact":{
                    PIMItem = CreatePIMContact();
                    break;
                }

                case "appointment":{
                    PIMItem = CreatePIMAppointment();
                    break;
                }

                case "quit":{
                    return;
                }
                default:{
                    System.out.println("Type Error, please enter an item type ( todo, note, contact or appointment ) or quit to exit create item");
                }
            }
        }
        PIMEntities[PIMEntityCount] = PIMItem;
        PIMEntityCount ++;
        System.out.println("Create Item Succeed!");
    }

    private PIMEntity CreatePIMTodo(){
        System.out.println("Enter date for todo item:(like 04/20/2017):");
        LocalDate TodoDate = null;
        String DateString = sc.nextLine();
        try{
            TodoDate = LocalDate.parse(DateString,DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        }
        catch(DateTimeParseException e){
            System.out.println("Date Format Error, Go Back Create Item.");
            return null;
        }

        System.out.println("Enter date for todo text:");
        String TextString = sc.nextLine();

        System.out.println("Enter date for todo priority:");
        String PriorityString = sc.nextLine();

        return new PIMTodo(TextString, TodoDate, PriorityString);
    }
    private PIMEntity CreatePIMNote(){
        System.out.println("Enter date for note text:");
        String TextString = sc.nextLine();

        System.out.println("Enter date for note priority:");
        String PriorityString = sc.nextLine();

        return new PIMNote(TextString, PriorityString);
    }
    private PIMEntity CreatePIMContact(){
        System.out.println("Enter date for Contact FirstName:");
        String FirstName = sc.nextLine();

        System.out.println("Enter date for Contact LastName:");
        String LastName = sc.nextLine();

        System.out.println("Enter date for Contact EmailAddress:");
        String EmailAddress = sc.nextLine();

        System.out.println("Enter date for Contact priority:");
        String PriorityString = sc.nextLine();

        return new PIMContact(FirstName, LastName, EmailAddress, PriorityString);
    }
    private PIMEntity CreatePIMAppointment(){
        System.out.println("Enter date for Appointmen item:(like 04/20/2017):");
        LocalDate AppointmenDate = null;
        String DateString = sc.nextLine();
        try{
            AppointmenDate = LocalDate.parse(DateString,DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        }
        catch(DateTimeParseException e){
            System.out.println("Date Format Error, Go Back Create Item.");
            return null;
        }

        System.out.println("Enter date for Appointmen text:");
        String TextString = sc.nextLine();

        System.out.println("Enter date for Appointmen priority:");
        String PriorityString = sc.nextLine();

        return new PIMAppointment(TextString, AppointmenDate, PriorityString);
    }
    public boolean Save(){
        String[] PIMEntitiesInfo =  new String[PIMEntityCount];
        String[] FileNameAndPath = new String[1];

        System.out.print("Enter The File Name With Path:");
        FileNameAndPath[0] = sc.nextLine();

        for(int i = 0; i < PIMEntityCount; i++){
            PIMEntitiesInfo[i] = PIMEntities[i].toString();
        }

        if(pStore.Open(FileNameAndPath) && pStore.Write(PIMEntitiesInfo)){
            pStore.Close();
            System.out.println("Save Succeed!.");
            return true;
        }
        else{
            pStore.Close();
            return false;
        }
    }
    public boolean Load(){
        String[] FileNameAndPath = new String[1];
        System.out.print("Enter The File Name With Path:");
        FileNameAndPath[0] = sc.nextLine();
        if(!pStore.Open(FileNameAndPath)){
            return false;
        }
        String[] PIMEntitiesInfo = pStore.Read();
        if(PIMEntitiesInfo == null){
            pStore.Close();
            return false;
        }
        PIMEntityCount = 0;
        for(int i = 0; i<PIMEntitiesInfo.length;i++){
            try{
                switch(PIMEntitiesInfo[i].substring(0, 13)){
                    case "Type: PIMTodo":{
                        PIMEntities[PIMEntityCount] = PIMTodo.FromString(PIMEntitiesInfo[i]);
                        PIMEntityCount++;
                        break;
                    }
                    case "Type: PIMNote":{
                        PIMEntities[PIMEntityCount] = PIMNote.FromString(PIMEntitiesInfo[i]);
                        PIMEntityCount++;
                        break;
                    }
                    case "Type: PIMAppo":{
                        PIMEntities[PIMEntityCount] = PIMAppointment.FromString(PIMEntitiesInfo[i]);
                        PIMEntityCount++;
                        break;
                    }
                    case "Type: PIMCont":{
                        PIMEntities[PIMEntityCount] = PIMContact.FromString(PIMEntitiesInfo[i]);
                        PIMEntityCount++;
                        break;
                    }
                    default:{
                        System.out.println(PIMEntitiesInfo[i] + " Can't Parse");
                    }
                }
            }
            catch(IndexOutOfBoundsException e){
                System.out.println(PIMEntitiesInfo[i] + " Can't Parse");
                continue;
            }
        }
        pStore.Close();
        System.out.println("Load Succeed!.");
        return true;
    }
    public static void main(String[] args) {
        PIMManager m = null;
        if(args.length == 0){
            m = new PIMManager(null);
        }
        else{
            m = new PIMManager(args[0]);
        }
        
        m.Run();
        // String[] s = new String[4];
        // s[0] = "Type: PIMTodo    Priority: pp    Date: 2017-04-20    text";
        // s[1] = "Type: PIMNote    Priority: pp    text";
        // s[2] = "Type: PIMAppointment    Priority: pp    Date: 2017-04-20    text";
        // s[3] = "Type: PIMContact    Priority: pp    FirstName: ff    LastName: ll    EmailAddress: ee";

        // PIMEntity[] pp = new PIMEntity[4];
        // pp[0] = new PIMTodo("");
        // pp[1] = new PIMNote("");
        // pp[2] = new PIMAppointment("");
        // pp[3] = new PIMContact("","");
        // for(int i = 0;i<4;i++){
        //     System.out.println(pp[i].toString());
        // }
        // for(int i = 0;i<4;i++){
        //     pp[i].fromString(s[i]);
        // }
        // for(int i = 0;i<4;i++){
        //     System.out.println(pp[i].toString());
        // }

    }
}