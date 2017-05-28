package StarsRiver.Controller;

import javafx.fxml.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.event.*;
/**
 * SyncNetWorkController
 * @auther 乔新文
 * @StudentID 14130140393
 * @Email starsriver@outlook.com
 */
public class SyncNetWorkController {
    public static String Url;
    public static Boolean UrlReady;
    @FXML
    private Label Title;

    @FXML
    private TextField HostName;

    @FXML
    private TextField Port;

    @FXML
    private Button Sync;

    @FXML
    private Button Cancel;
    public void Sync(ActionEvent event){
        try{
            if(HostName.getText().length() == 0|| Port.getText().length() == 0){
                //throw new  Exception();
            }
            StarsRiver.Controller.SyncNetWorkController.Url = HostName.getText() + ":" + Port.getText();
            StarsRiver.Controller.SyncNetWorkController.UrlReady = true;
            Stage stage = (Stage) Sync.getScene().getWindow();
            Event.fireEvent(stage, new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST ));
        }
        catch(Exception e){
            Title.setText("Error");
            StarsRiver.Controller.SyncNetWorkController.UrlReady = false;
        }
    }
    public void Cancel(ActionEvent event) {
        StarsRiver.Controller.SyncNetWorkController.UrlReady = false;
        Stage stage = (Stage) Cancel.getScene().getWindow();
        Event.fireEvent(stage, new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST ));
    }
}