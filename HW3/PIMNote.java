/**
 * PIMNote
 * @auther 乔新文
 * @StudentID 14130140393
 * @Email starsriver@outlook.com
 */
public class PIMNote extends PIMEntity{

    public PIMNote (String noteContains) {
        setPriority("normal");
        NoteContains = noteContains;
    }
    public PIMNote (String noteContains,String priority) {
        setPriority(priority);
        NoteContains = noteContains;
    }

    public String NoteContains;
    public void fromString(String s)
    {
        // s likes "Type: PIMNote    Priority: pp    text"
        String[] ss = s.split("(Type: )|(Priority: )|(    )");
        String[] sss = new String[3];
        int temp = 0;
        for (String var : ss) {
            if(temp >= 3){
                System.out.println("Input String can't to PIMNote.");
                return;
            }
            if(!var.equals("")){
                sss[temp] = var;
                temp ++;
            }
        }
        if(!sss[0].equals("PIMNote")){
            System.out.println("Input String can't to PIMNote.");
            return;
        }
        setPriority(sss[1]);
        NoteContains = sss[2];
    }

    public static PIMNote FromString(String s)
    {
        // s likes "Type: PIMNote    Priority: pp    text"
        String[] ss = s.split("(Type: )|(Priority: )|(    )");
        String[] sss = new String[3];
        int temp = 0;
        for (String var : ss) {
            if(temp >= 3){
                System.out.println("Input String can't to PIMNote.");
                return null;
            }
            if(!var.equals("")){
                sss[temp] = var;
                temp ++;
            }
        }
        if(!sss[0].equals("PIMNote")){
            System.out.println("Input String can't to PIMNote.");
            return null;
        }

        return new PIMNote(sss[2], sss[1]);
    }
    public String toString(){
        return "Type: PIMNote    " + "Priority: " + getPriority() + "    " + NoteContains;
    }
}