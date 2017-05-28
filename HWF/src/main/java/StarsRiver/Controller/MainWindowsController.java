package StarsRiver.Controller;

import javafx.fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.*;
import javafx.util.StringConverter;
import javafx.scene.control.*;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.*;
import javafx.collections.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;

import java.time.temporal.TemporalAdjusters;
import java.io.*;
import StarsRiver.MainWindows;
import StarsRiver.Controller.base.PIMStore;
import StarsRiver.Model.*;
import javafx.event.*;
/**
 * MainWindowsController
 */
public class MainWindowsController implements Initializable {

    private static boolean ShowTodo;
    private static boolean ShowAppointment;
    static{
        ShowTodo = true;
        ShowAppointment = true;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {  
        
        //MainPane.add(new Label("text"),0,0);
        updateView();
    } 

    @FXML
    private GridPane MainPane;

    @FXML
    private DatePicker DateLab;

    @FXML
    private HBox Account;

    @FXML
    private ListView<String> NoteList;

    @FXML
    private ListView<String> ContactList;

    private String temp;

    public void NewTodo(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/NewTodo.fxml"));
        Stage primStage = new Stage();
        primStage.setTitle("New Todo");
        primStage.setScene(new Scene(root));
        primStage.show();
    }
    public void NewNote(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/NewNote.fxml"));
        Stage primStage = new Stage();
        primStage.setTitle("NewNote");
        primStage.setScene(new Scene(root));
        primStage.show();
    }

    public void NewContact(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/NewContact.fxml"));
        Stage primStage = new Stage();
        primStage.setTitle("NewContact");
        primStage.setScene(new Scene(root));
        primStage.show();
    }

    public void NewAppointment(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/NewAppointment.fxml"));
        Stage primStage = new Stage();
        primStage.setTitle("NewAppointment");
        primStage.setScene(new Scene(root));
        primStage.show();
    }

    public void ShowAll(ActionEvent event) {
        ShowTodo = !ShowTodo;
        ShowAppointment = !ShowAppointment;
        updateView();
    }
    public void ShowTodo(ActionEvent event) {
        ShowTodo = !ShowTodo;
        updateView();
    }
    public void ShowAppointment(ActionEvent event) {
        ShowAppointment = !ShowAppointment;
        updateView();
    }
    public void SyncWithFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        Stage primStage = new Stage();
        fileChooser.setTitle("Open PIMStore File");
        File file = fileChooser.showOpenDialog(primStage);
        if(file == null){
            return;
        }

        PIMStore store = new PIMFile();
        Sync(store, new String[]{file.getAbsolutePath()});
        updateView();
    }
    public void SyncWithDataBase(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        Stage primStage = new Stage();
        fileChooser.setTitle("Open PIMStore DataBase File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("db3", "*.db3"), new FileChooser.ExtensionFilter("db", "*.db"));
        File file = fileChooser.showOpenDialog(primStage);
        if(file == null){
            return;
        }

        PIMStore store = new PIMDataBase();
        Sync(store, new String[]{"org.sqlite.JDBC","jdbc:sqlite:" + file.getAbsolutePath()});
        updateView();
    }
    public void SyncWithNetWork(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/View/SyncNetWork.fxml"));
        Stage primStage = new Stage();
        primStage.initModality(Modality.APPLICATION_MODAL);
        primStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                if(SyncNetWorkController.UrlReady){
                    PIMStore store = new PIMNetwork();
                    Sync(store, new String[]{SyncNetWorkController.Url,"post"});
                    updateView();
                }
            }
        });
        primStage.setTitle("Input HostName and Port");
        primStage.setScene(new Scene(root));
        primStage.show();
    }

    public void DateBefor(ActionEvent event) {
        StarsRiver.MainWindows.date = StarsRiver.MainWindows.date.plusMonths(-1);
        updateView();
    }
    public void Changed(ActionEvent event) {
        StarsRiver.MainWindows.date = DateLab.getValue();
        updateView();
    }
    public void DateAfter(ActionEvent event) {
        StarsRiver.MainWindows.date = StarsRiver.MainWindows.date.plusMonths(1);
        updateView();
    }

    private void updateView(){
        GridPane.clearConstraints(MainPane);
        MainPane.getChildren().removeAll(MainPane.getChildren());

        MainPane.setGridLinesVisible(true);
        MainPane.setOpacity(0.5);
        MainPane.setVgap(10);
        MainPane.setHgap(10);
        StringConverter converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = 
                DateTimeFormatter.ofPattern("yyyy-MM");
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };  
        DateLab.setConverter(converter);
        DateLab.setValue(StarsRiver.MainWindows.date);

        for(int i = 0; i<7;i++){
            Label laber = new Label(DayOfWeek.of(i+1).toString());
            laber.setFont(new Font("Regular", 16));
            laber.setTextAlignment(TextAlignment.CENTER);
            MainPane.add(laber,i,0);
        }

        DateLab.setValue(MainWindows.date);

        LocalDate begin = MainWindows.date.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate end = MainWindows.date.with(TemporalAdjusters.lastDayOfMonth());
        int beginDayOfWeek = begin.getDayOfWeek().getValue();
        int temp = 1;
        int dayOfMonth = 1;
        for(int j = 1; j < 7; j++){
            for(int i = 0; i < 7; i++){
                if(temp < beginDayOfWeek){
                    temp ++;
                    continue;
                }
                if(dayOfMonth <= end.getDayOfMonth()){
                    TextArea textArea = new TextArea();
                    textArea.setWrapText(true);
                    textArea.setFont(new Font("Regular", 12));

                    String str = Integer.toString(dayOfMonth) + "\n";
                    PIMCollection baseDate = StarsRiver.MainWindows.pimcollection.getItemsForDate(begin);
                    if(ShowTodo){
                        HashSet<PIMTodo> todos = baseDate.getTodos();
                        for (PIMTodo item : todos) {
                            str += item.toString() + "\n";
                        }
                    }
                    if(ShowAppointment){
                        HashSet<PIMAppointment> appointments = baseDate.getPIMAppointments();
                        for (PIMAppointment item : appointments) {
                            str += item.toString() + "\n";
                        }
                    }

                    textArea.setText(str);
                    textArea.setEditable(false);

                    MainPane.add(textArea,i,j);

                    begin = begin.plusDays(1);
                    dayOfMonth++;
                }
            }
        }

        ObservableList<String> notelist =  FXCollections.observableArrayList();
        for (PIMNote item : StarsRiver.MainWindows.pimcollection.getNotes()) {
            notelist.add(item.toString());
        }
        NoteList.setItems(notelist);

        ObservableList<String> contactlist =  FXCollections.observableArrayList();
        for (PIMContact item : StarsRiver.MainWindows.pimcollection.getContacts()) {
            contactlist.add(item.toString());
        }
        ContactList.setItems(contactlist);
    }

    public static boolean Sync(PIMStore store,String[] str) {
        if(!store.Open(str)){
            System.out.println("Sync error");
            return false;
        }
        if(!store.Write(MainWindows.pimcollection.toStrings())){
            System.out.println("Sync error");
            return false;
        }
        store.Close();

        if(store.getClass()==PIMNetwork.class){
            str[1] = "all";
        }
        if(!store.Open(str)){
            System.out.println("Sync error");
            return false;
        }
        String[] data = store.Read();
        if(data == null){
            System.out.println("Sync error");
            return false;
        }
        store.Close();

        StarsRiver.MainWindows.pimcollection = new PIMCollection(data);
        return true;
    }
}