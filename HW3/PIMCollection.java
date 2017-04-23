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
    }
    public HashSet<PIMEntity> getItemsForDate(LocalDate d){
        HashSet<PIMEntity> entitiesForDate = new HashSet<PIMEntity>();
        HashSet<PIMTodo> todos = entitiesSwitch(new PIMTodo("Empty"));
        HashSet<PIMAppointment> appointments = entitiesSwitch(new PIMAppointment("Empty"));
        for(PIMTodo item : todos){
            if(item.getDate().equals(d)){
                entitiesForDate.add(item);
            }
        }
        for(PIMAppointment item : appointments){
            if(item.getDate().equals(d)){
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