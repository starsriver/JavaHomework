import java.time.*;
import java.time.format.DateTimeFormatter;
/**
 * 
PIMAppointment
 * @auther 乔新文
 * @StudentID 14130140393
 * @Email starsriver@outlook.com
 */
public class PIMAppointment extends PIMEntity implements PIMDate{

    public PIMAppointment (String appointmentContains) {
        AppointmentDate = LocalDate.now();
        setPriority("normal");
        AppointmentContains = appointmentContains;
    }

    public PIMAppointment (String appointmentContains, LocalDate appointmentDate) {
        setPriority("normal");
        AppointmentDate = appointmentDate;
        AppointmentContains = appointmentContains;
    }

    public PIMAppointment (String appointmentContains, LocalDate appointmentDate, String priority) {
        setPriority(priority);
        AppointmentDate = appointmentDate;
        AppointmentContains = appointmentContains;
    }

    private LocalDate AppointmentDate;

    public String AppointmentContains;
    public void fromString(String s)
    {
        // s likes "Type: PIMAppointment    Priority: pp    Date: 2017-04-20    text"
        String[] ss = s.split("(Type: )|(Priority: )|(Date: )|(    )");
        String[] sss = new String[4];
        int temp = 0;
        for (String var : ss) {
            if(temp >= 4){
                System.out.println("Input String can't to PIMAppointment.");
                return;
            }
            if(!var.equals("")){
                sss[temp] = var;
                temp ++;
            }
        }
        if(!sss[0].equals("PIMAppointment")){
            System.out.println("Input String can't to PIMAppointment.");
            return;
        }
        setPriority(sss[1]);
        AppointmentDate = LocalDate.parse(sss[2],DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        AppointmentContains = sss[3];
    }

    public static PIMAppointment FromString(String s)
    {
        // s likes "Type: PIMAppointment    Priority: pp    Date: 2017-04-20    text"
        String[] ss = s.split("(Type: )|(Priority: )|(Date: )|(    )");
        String[] sss = new String[4];
        int temp = 0;
        for (String var : ss) {
            if(temp >= 4){
                System.out.println("Input String can't to PIMAppointment.");
                return null;
            }
            if(!var.equals("")){
                sss[temp] = var;
                temp ++;
            }
        }
        if(!sss[0].equals("PIMAppointment")){
            System.out.println("Input String can't to PIMAppointment.");
            return null;
        }

        return new PIMAppointment(sss[3], LocalDate.parse(sss[2],DateTimeFormatter.ofPattern("yyyy-MM-dd")), sss[1]);
    }
    public String toString(){
        return "Type: PIMAppointment    " + "Priority: " + getPriority() + "    Date: " + printDate() + "    " + AppointmentContains;
    }

    public LocalDate getDate(){
        return AppointmentDate;
    }
    public void setDate(LocalDate d){
        AppointmentDate = d;
    }
    public String printDate(){
        return AppointmentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}