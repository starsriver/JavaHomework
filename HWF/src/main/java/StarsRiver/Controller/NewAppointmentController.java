package StarsRiver.Controller;

import javafx.fxml.*;
import javafx.stage.*;
import javafx.scene.control.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.event.*;

import StarsRiver.Model.*;

/**
 * NewAppointmentController
 * @auther 乔新文
 * @StudentID 14130140393
 * @Email starsriver@outlook.com
 */
public class NewAppointmentController {
    @FXML
    private Label Title;

    @FXML
    private TextField Year;

    @FXML
    private TextField Month;

    @FXML
    private TextField Day;

    @FXML
    private TextField Priority;

    @FXML
    private TextArea Contains;

    @FXML
    private Button Create;

    @FXML
    private Button Cancel;
    public void Create(ActionEvent event){
        try{
            if(Contains.getText().length() == 0 || Priority.getText().length() == 0){
                throw new  Exception();
            }
            PIMAppointment appointment = new PIMAppointment(Contains.getText(), LocalDate.parse(Year.getText()+Month.getText()+Day.getText(),DateTimeFormatter.ofPattern("yyyyMMdd")), Priority.getText());
            StarsRiver.MainWindows.pimcollection.add(appointment);

            Stage stage = (Stage) Create.getScene().getWindow();
            Event.fireEvent(stage, new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST ));
        }
        catch(Exception e){
            Title.setText("Error");
        }
    }
    public void Cancel(ActionEvent event) {
        Stage stage = (Stage) Cancel.getScene().getWindow();
        Event.fireEvent(stage, new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST ));
    }
}