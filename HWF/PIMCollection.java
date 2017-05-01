import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashSet;
/**
 * PIMCollection
 * @auther 乔新文
 * @StudentID 14130140393
 * @Email starsriver@outlook.com
 */
public class PIMCollection extends HashSet<PIMEntity>{

    public PIMCollection(){
        super();
    }

    public PIMCollection(String[] pimCollectionStrings){
        super();
        for(int i = 0; i<pimCollectionStrings.length;i++){
            try{
                switch(pimCollectionStrings[i].substring(0, 13)){
                    case "Type: PIMTodo":{
                        this.add(PIMTodo.FromString(pimCollectionStrings[i]));
                        break;
                    }
                    case "Type: PIMNote":{
                        this.add(PIMNote.FromString(pimCollectionStrings[i]));
                        break;
                    }
                    case "Type: PIMAppo":{
                        this.add(PIMAppointment.FromString(pimCollectionStrings[i]));
                        break;
                    }
                    case "Type: PIMCont":{
                        this.add(PIMContact.FromString(pimCollectionStrings[i]));
                        break;
                    }
                    default:{
                        System.out.println(pimCollectionStrings[i] + " Can't Parse");
                    }
                }
            }
            catch(IndexOutOfBoundsException e){
                System.out.println(pimCollectionStrings[i] + " Can't Parse");
                continue;
            }
        }
    }
    public String[] toStrings(){
        String[] strs = new String[this.size()];
        int i = 0;
        for(PIMEntity item:this){
            strs[i] = item.toString();
            i++;
        }
        return strs;
    }
    public HashSet<PIMNote> getNotes(){
        return entitiesSwitch(new PIMNote("Empty"));
    }

    public HashSet<PIMTodo> getTodos(){
        return entitiesSwitch(new PIMTodo("Empty"));
    }

    public HashSet<PIMAppointment> getPIMAppointments(){
        return entitiesSwitch(new PIMAppointment("Empty"));
    }

    public HashSet<PIMContact> getContacts(){
         return entitiesSwitch(new PIMContact("Empty", "Empty"));
    }

    @SuppressWarnings({"unchecked"})
    private <T extends PIMEntity> HashSet<T> entitiesSwitch(T t){
        HashSet<T> items = new HashSet<T>();
        for(PIMEntity item : this){
            if(item.getClass().equals(t.getClass())){
                items.add((T)item);
            }
        }
        return items;
        /*原先的基于C#的泛型写法
        HashSet<T> items = new HashSet<T>();
        for(PIMEntity item : this){
            if(item instaceof T){
                items.add((T)item);
            }
        }
        return items;

         */
    }
    public HashSet<PIMEntity> getItemsForDate(LocalDate d){
        HashSet<PIMEntity> entitiesForDate = new HashSet<PIMEntity>();
        for(PIMEntity item:this){
            if((item instanceof PIMDate )&&((PIMDate)item).getDate().equals(d)){
                entitiesForDate.add(item);
            }
        }
        return entitiesForDate;
    }
    public HashSet<PIMEntity> getItemsForDate(Date d){
        LocalDate ld = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return getItemsForDate(ld);
    }

    public static void main(String[] args) {
        PIMCollection collection = new PIMCollection();
        collection.add(new PIMTodo("todo1"));
        collection.add(new PIMTodo("todo2"));
        collection.add(new PIMNote("note1"));
        collection.add(new PIMNote("note2"));
        collection.add(new PIMAppointment("appointment1"));
        collection.add(new PIMAppointment("appointment2"));
        collection.add(new PIMContact("fm1", "lm1"));
        collection.add(new PIMContact("fm2", "lm2"));
        
        HashSet<PIMNote> notes = collection.getNotes();
        HashSet<PIMTodo> todos = collection.getTodos();
        HashSet<PIMAppointment> appointments = collection.getPIMAppointments();
        HashSet<PIMContact> contacts = collection.getContacts();
        HashSet<PIMEntity> entitiesForDate = collection.getItemsForDate(LocalDate.now());

        for(PIMNote item:notes){
            System.out.println(item.getClass().toString());
            System.out.println(item.toString());
        }
        for(PIMTodo item:todos){
            System.out.println(item.getClass().toString());
            System.out.println(item.toString());
        }
        for(PIMAppointment item:appointments){
            System.out.println(item.getClass().toString());
            System.out.println(item.toString());
        }
        for(PIMContact item:contacts){
            System.out.println(item.getClass().toString());
            System.out.println(item.toString());
        }
        System.out.println("Date test");
        for(PIMEntity item:entitiesForDate){
            System.out.println(item.getClass().toString());
            System.out.println(item.toString());
        }
    }
}