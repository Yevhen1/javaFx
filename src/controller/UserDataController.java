package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;
public class UserDataController implements Initializable {

    @FXML
    ImageView largeImageUserData;

    @FXML
    Label countryUserData;

    @FXML
    Label cityUserData;

    @FXML
    Label streetUserData;

    @FXML
    Label emailUserData;

    @FXML
    Label phoneNumberUserData;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
