/**
 * cal
 *  @auther 乔新文
 * @StudentID 14130140393
 * @Email starsriver@outlook.com
 */
import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.time.format.DateTimeFormatter;
public class cal {
    public static void main(String[] args) {
        LocalDate ldb = null;
        try{
            if(args[0].length() == 1){
                args[0] = "0" + args[0];
            }
            ldb = LocalDate.parse(args[0] + args[1] + "01",DateTimeFormatter.ofPattern("MMyyyydd"));
        }
        catch(Exception e){
            ldb = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        }
        PrintfMouth(ldb);
    }

    public static void PrintfMouth(LocalDate ldb){
        LocalDate lde = ldb.with(TemporalAdjusters.lastDayOfMonth());
        
        String mouth = ldb.getMonth().toString();
        System.out.println(mouth.substring(0, 1) + mouth.substring(1).toLowerCase() + " " + Integer.toString(ldb.getYear()));
        System.out.println("Su Mo Tu We Th Fr Sa");

        for(int i = 1; i<=lde.getDayOfMonth() + ldb.getDayOfWeek().getValue(); i++){
            if(i<=ldb.getDayOfWeek().getValue()){
                System.out.print("   ");
            }
            else{
                System.out.print(String.format("%02d ", i - ldb.getDayOfWeek().getValue()));
            }
            if(i%7==0){
                System.out.println();
            }
        }
    }
}