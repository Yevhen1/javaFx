package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.net.URL;
import java.util.ResourceBundle;
public class ItemController implements Initializable {

    @FXML
    HBox itemHBox;
    @FXML
    ImageView itemImageView;
    @FXML
    Label itemFirstNameLabel;
    @FXML
    Label itemLastNameItem;

    @FXML
    public void getUserData(){
    try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/userData.fxml"));
        HBox page = fxmlLoader.load();
        UserDataController controller = fxmlLoader.getController();
        JSONParser parser = new JSONParser();

        JSONObject image = (JSONObject) parser.parse(userData.get("picture").toString());
        JSONObject location = (JSONObject) parser.parse(userData.get("location").toString());
        JSONObject street = (JSONObject) parser.parse(location.get("street").toString());

        controller.largeImageUserData.setImage(new Image(image.get("large").toString()));
        controller.countryUserData.setText("country:  " + location.get("country").toString());
        controller.cityUserData.setText("city:  " + location.get("city").toString());
        controller.streetUserData.setText("street:  " + street.get("name").toString() + street.get("number").toString());
        controller.emailUserData.setText("email:  " + userData.get("email").toString());
        controller.phoneNumberUserData.setText("Phone number:  " + userData.get("phone"));

        Scene scene = new Scene(page, 600, 256);
        Stage stage = new Stage();
        stage.setMinHeight(256);
        stage.setMinWidth(600);
        stage.setTitle("User data");
        stage.setScene(scene);
        stage.show();

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }


    private JSONObject userData = null;


    public void setUserData(JSONObject jObj){
        userData = jObj;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources){
    }

}
