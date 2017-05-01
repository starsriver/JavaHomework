import java.sql.*;
import java.util.LinkedList;
/**
 * PIMDataBase
 * @auther 乔新文
 * @StudentID 14130140393
 * @Email starsriver@outlook.com
 */
public class PIMDataBase implements PIMStore{

    public PIMDataBase () {
        connection = null;
        statement = null;
    }
    Connection connection;
    Statement statement;

    private final String ReplacePIMTodoSQL = "REPLACE INTO PIMTodo VALUES ('%s','%s','%s');";
    private final String ReplacePIMNoteSQL = "REPLACE INTO PIMNote VALUES ('%s','%s');";
    private final String ReplacePIMContactSQL = "REPLACE INTO PIMContact VALUES ('%s','%s','%s','%s');";
    private final String ReplacePIMAppointmentSQL = "REPLACE INTO PIMAppointment VALUES ('%s','%s','%s');";
    public boolean Open(String[] str){
        try{
            Class.forName(str[0]);
            connection = DriverManager.getConnection(str[1]);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            initDB();
            return true;
        }
        catch(Exception e){
            System.out.println("Init DataBase Error, Will Try To RollBack!");
            try{
                connection.rollback();
                System.out.println("RollBack Successed!");
            }
            catch(SQLException ee){
                System.out.println("RollBack Failed!");
            }
            return false;
        }
    }
    public boolean Write(String[] str){
        PIMCollection pimCollection = new PIMCollection(str);
        try{
            for(PIMTodo item:pimCollection.getTodos()){
                statement.executeUpdate(String.format(ReplacePIMTodoSQL, item.getPriority(), item.getDate(), item.TodoContains));
            }
            for(PIMNote item:pimCollection.getNotes()){
                statement.executeUpdate(String.format(ReplacePIMNoteSQL, item.getPriority(), item.NoteContains));
            }
            for(PIMContact item:pimCollection.getContacts()){
                statement.executeUpdate(String.format(ReplacePIMContactSQL, item.getPriority(), item.FirstName, item.LastName, item.EmailAddress));
            }
            for(PIMAppointment item:pimCollection.getPIMAppointments()){
                statement.executeUpdate(String.format(ReplacePIMAppointmentSQL, item.getPriority(), item.getDate(), item.AppointmentContains));
            }
            connection.commit();
            return true;
        }
        catch(Exception e){
            System.out.println("Update DataBase Error, Will Try To RollBack!");
            try{
                connection.rollback();
                System.out.println("RollBack Successed!");
            }
            catch(Exception ee){
                System.out.println("RollBack Failed!");
            }
            return false;
        }
    }
    public String[] Read(){
        LinkedList<String> resulList = new LinkedList<String>();
        try{
            ResultSet resultSet = statement.executeQuery("SELECT * FROM PIMEntities;");
            while(resultSet.next()){
                resulList.add(resultSet.getString("PIMEntity"));
            }
            String[] result = new String[resulList.size()];
            resultSet.close();
            return resulList.toArray(result);
        }
        catch(Exception e){
            System.out.println("Read DataBase Error!");
            return new String[]{};
        }
    }
    public void Close(){
        try{
            statement.close();
            connection.close();
        }
        catch(SQLException e){
            System.out.println("Close DataBase Error!");
        }
    }

    private void initDB(){
        try{
            statement.execute("CREATE TABLE IF NOT EXISTS PIMTodo ( Priority TEXT NOT NULL, PIMDate TEXT NOT NULL, Contains TEXT, CONSTRAINT pk_PIMTodo PRIMARY KEY (Priority,PIMDate,Contains) ) ;");
            statement.execute("CREATE TABLE IF NOT EXISTS PIMNote ( Priority TEXT NOT NULL, Contains TEXT, CONSTRAINT pk_PIMNote PRIMARY KEY (Priority,Contains) ) ;");
            statement.execute("CREATE TABLE IF NOT EXISTS PIMContact ( Priority TEXT NOT NULL, FirstName TEXT, LastName TEXT, EmailAddress TEXT,CONSTRAINT pk_PIMContact PRIMARY KEY (Priority,FirstName,LastName,EmailAddress) ) ;");
            statement.execute("CREATE TABLE IF NOT EXISTS PIMAppointment ( Priority TEXT NOT NULL, PIMDate TEXT NOT NULL, Contains TEXT, CONSTRAINT pk_PIMAppointment PRIMARY KEY (Priority,PIMDate,Contains) ) ;");
            statement.execute("CREATE VIEW IF NOT EXISTS PIMTodoView (PIMEntity) AS SELECT 'Type: PIMTodo    Priority: '||Priority||'    Date: '||PIMDate||'    '||Contains FROM PIMTodo ;");
            statement.execute("CREATE VIEW IF NOT EXISTS PIMNoteView (PIMEntity) AS SELECT 'Type: PIMNote    Priority: '||Priority||'    '||Contains FROM PIMNote;");
            statement.execute("CREATE VIEW IF NOT EXISTS PIMContactView (PIMEntity) AS SELECT 'Type: PIMContact    Priority: '||Priority||'    FirstName: '||FirstName||'    LastName: '||LastName||'    EmailAddress: '||EmailAddress FROM PIMContact;");
            statement.execute("CREATE VIEW IF NOT EXISTS PIMAppointmentView (PIMEntity) AS SELECT 'Type: PIMAppointment    Priority: '||Priority||'    Date: '||PIMDate||'    '||Contains FROM PIMAppointment;");
            statement.execute("CREATE VIEW IF NOT EXISTS PIMEntities AS SELECT PIMEntity FROM PIMTodoView UNION ALL SELECT PIMEntity FROM PIMNoteView UNION ALL SELECT PIMEntity FROM PIMContactView UNION ALL SELECT PIMEntity FROM PIMAppointmentView;");
            connection.commit();
        }
        catch(Exception e){
            System.out.println("Init DataBase Error");
        }
    }
    public static void main(String[] args) {
        // PIMDataBase D = new PIMDataBase();
        // D.Open(new String[]{"org.sqlite.JDBC","jdbc:sqlite:PIMDataBase.db3"});
        // PIMCollection str = new PIMCollection();
        // str.add(new PIMTodo("TT2"));
        // str.add(new PIMNote("nn2"));
        // str.add(new PIMContact("FF", "ll"));
        // str.add(new PIMAppointment("AA"));
        // D.Write(str.toStrings());

        PIMDataBase D = new PIMDataBase();
        D.Open(new String[]{"org.sqlite.JDBC","jdbc:sqlite:PIMDataBase.db3"});
        PIMCollection str = new PIMCollection(D.Read());
        for(PIMEntity s:str){
            System.out.println(s);
        } 
        D.Close();
    }
}