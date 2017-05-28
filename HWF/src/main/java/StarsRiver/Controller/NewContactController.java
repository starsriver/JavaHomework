package StarsRiver.Controller;

import javafx.fxml.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.event.*;

import StarsRiver.Model.*;

/**
 * NewContactController
 * @auther 乔新文
 * @StudentID 14130140393
 * @Email starsriver@outlook.com
 */
public class NewContactController {
    @FXML
    private Label Title;

    @FXML
    private TextField FirstName;

    @FXML
    private TextField LastName;

    @FXML
    private TextField Priority;

    @FXML
    private TextField EMailAddress;

    @FXML
    private Button Create;

    @FXML
    private Button Cancel;
    public void Create(ActionEvent event){
        try{
            if((FirstName.getText().length() == 0&&LastName.getText().length()==0)|| Priority.getText().length() == 0){
                throw new  Exception();
            }
            PIMContact contact = new PIMContact(FirstName.getText(), LastName.getText(), EMailAddress.getText(), Priority.getText());
            StarsRiver.MainWindows.pimcollection.add(contact);

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