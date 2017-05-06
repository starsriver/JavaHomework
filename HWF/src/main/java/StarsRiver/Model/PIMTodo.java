package StarsRiver.Model;

import java.time.*;
import java.time.format.DateTimeFormatter;
import StarsRiver.Model.base.*;;
/**
 * PIMTodo
 * @auther 乔新文
 * @StudentID 14130140393
 * @Email starsriver@outlook.com
 */
public class PIMTodo extends PIMEntity implements PIMDate{

    public PIMTodo (String todoContains) {
        TodoDate = LocalDate.now();
        setPriority("normal");
        TodoContains = todoContains;
    }

    public PIMTodo (String todoContains, LocalDate todoDate) {
        setPriority("normal");
        TodoDate = todoDate;
        TodoContains = todoContains;
    }

    public PIMTodo (String todoContains, LocalDate todoDate, String priority) {
        setPriority(priority);
        TodoDate = todoDate;
        TodoContains = todoContains;
    }

    private LocalDate TodoDate;

    public String TodoContains;
    
    public void fromString(String s)
    {
        //s likes "Type: PIMTodo    Priority: normal    Date: 2017-04-20    text""
        String[] ss = s.split("(Type: )|(Priority: )|(Date: )|(    )");
        String[] sss = new String[4];
        int temp = 0;
        for (String var : ss) {
            if(temp >= 4){
                System.out.println("Input String can't to PIMTodo.");
                return;
            }
            if(!var.equals("")){
                sss[temp] = var;
                temp ++;
            }
        }
        if(!sss[0].equals("PIMTodo")){
            System.out.println("Input String can't to PIMTodo.");
            return;
        }
        setPriority(sss[1]);
        TodoDate = LocalDate.parse(sss[2],DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        TodoContains = sss[3];
    }

    public static PIMTodo FromString(String s)
    {
        //s likes "Type: PIMTodo    Priority: normal    Date: 2017-04-20    text""
        String[] ss = s.split("(Type: )|(Priority: )|(Date: )|(    )");
        String[] sss = new String[4];
        int temp = 0;
        for (String var : ss) {
            if(temp >= 4){
                System.out.println("Input String can't to PIMTodo.");
                return null;
            }
            if(!var.equals("")){
                sss[temp] = var;
                temp ++;
            }
        }
        if(!sss[0].equals("PIMTodo")){
            System.out.println("Input String can't to PIMTodo.");
            return null;
        }

        return new PIMTodo(sss[3], LocalDate.parse(sss[2],DateTimeFormatter.ofPattern("yyyy-MM-dd")), sss[1]);
    }
    public String toString(){
        return "Type: PIMTodo    " + "Priority: " + getPriority() + "    Date: " + printDate() + "    " + TodoContains;
    }

    public LocalDate getDate(){
        return TodoDate;
    }
    public void setDate(LocalDate d){
        TodoDate = d;
    }
    public String printDate(){
        return TodoDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}