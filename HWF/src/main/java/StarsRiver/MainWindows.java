package StarsRiver;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import StarsRiver.Model.*;
/**
 * 
MainWindows
 * @auther 乔新文
 * @StudentID 14130140393
 * @Email starsriver@outlook.com
 */
public class MainWindows extends Application{

    public static LocalDate date;
    public static PIMCollection pimcollection;
    static{
        date = LocalDate.now();
        pimcollection = new PIMCollection();
    }

    @Override
    public void start(Stage primStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/main.fxml"));
        primStage.setTitle("HWF");
        primStage.setScene(new Scene(root));
        primStage.show();
    }
    public static void main(String[] args) {
        if(args.length >= 1){
            try{
                StarsRiver.MainWindows.date = LocalDate.parse(args[0],DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
            catch(Exception e){
                StarsRiver.MainWindows.date = LocalDate.now();
            }
        }
        Application.launch(MainWindows.class, args);
    }
}