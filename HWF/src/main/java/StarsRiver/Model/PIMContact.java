package StarsRiver.Model;

import StarsRiver.Model.base.*;;
/**
 * PIMContact
 * @auther 乔新文
 * @StudentID 14130140393
 * @Email starsriver@outlook.com
 */
public class PIMContact extends PIMEntity{

    public PIMContact (String firstName,String lastName) {
        EmailAddress = "";
        setPriority("normal");
        FirstName = firstName;
        LastName = lastName;
    }

    public PIMContact (String firstName,String lastName,String emailAddress) {
        setPriority("normal");
        EmailAddress = emailAddress;        
        FirstName = firstName;
        LastName = lastName;
    }

    public PIMContact (String firstName,String lastName,String emailAddress,String priority) {
        setPriority(priority);
        EmailAddress = emailAddress;        
        FirstName = firstName;
        LastName = lastName;
    }

    public String FirstName;
    public String LastName;
    public String EmailAddress;
    public void fromString(String s)
    {
        // s likes "Type: PIMContact    Priority: pp    FirstName: ff    LastName: ll    EmailAddress: ee"
        String[] ss = s.split("(Type: )|(Priority: )|(FirstName: )|(LastName: )|(EmailAddress: )|(    )");
        String[] sss = new String[5];
        int temp = 0;
        for (String var : ss) {
            if(temp >= 5){
                System.out.println("Input String can't to PIMContact.");
                return;
            }
            if(!var.equals("")){
                sss[temp] = var;
                temp ++;
            }
        }
        if(!sss[0].equals("PIMContact")){
            System.out.println("Input String can't to PIMContact.");
            return;
        }
        setPriority(sss[1]);
        FirstName = sss[2];
        LastName = sss[3];
        EmailAddress = sss[4];
    }
    public static PIMContact FromString(String s)
    {
        // s likes "Type: PIMContact    Priority: pp    FirstName: ff    LastName: ll    EmailAddress: ee"
        String[] ss = s.split("(Type: )|(Priority: )|(FirstName: )|(LastName: )|(EmailAddress: )|(    )");
        String[] sss = new String[5];
        int temp = 0;
        for (String var : ss) {
            if(temp >= 5){
                System.out.println("Input String can't to PIMContact.");
                return null;
            }
            if(!var.equals("")){
                sss[temp] = var;
                temp ++;
            }
        }
        if(!sss[0].equals("PIMContact")){
            System.out.println("Input String can't to PIMContact.");
            return null;
        }

        return new PIMContact(sss[2], sss[3], sss[4], sss[1]);
    }
    public String toString(){
        return "Type: PIMContact    " + "Priority: " + getPriority() + "    FirstName: " + FirstName + "    LastName: " + LastName + "    EmailAddress: " + EmailAddress;
    }
}