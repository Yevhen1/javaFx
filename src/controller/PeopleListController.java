package controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;


public class PeopleListController implements Initializable {
    private static final String HOST = "https://randomuser.me/";
    private static final String GET_USERS = "api/?results=20";

    @FXML
    private ListView<HBox> listViewPerson;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(HOST + GET_USERS)
                    .openConnection();
            connection.setConnectTimeout(10000);
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);

            if (connection.getResponseCode() != 200) {
                System.err.println("connection failed");
            }

            StringBuilder respBody = new StringBuilder();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            reader.lines().forEach(l -> respBody.append(l));
            reader.close();

            JSONParser parser = new JSONParser();
            try {
                ObservableList<HBox> list = FXCollections.observableArrayList();
                Object obj = parser.parse(respBody.toString());
                JSONObject jsonObject = (JSONObject)obj;
                JSONArray result = (JSONArray)jsonObject.get("results");
                Iterator<JSONObject> iterator = result.iterator();

                while (iterator.hasNext()){
                    JSONObject user = (JSONObject)parser.parse(iterator.next().toString());
                    JSONObject name = (JSONObject)parser.parse(user.get("name").toString());
                    JSONObject pic = (JSONObject)parser.parse(user.get("picture").toString());

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/item.fxml"));
                    HBox listItem = fxmlLoader.load();
                    ItemController controller = fxmlLoader.getController();
                    controller.itemImageView.setImage(new Image(pic.get("thumbnail").toString()));

                    controller.itemFirstNameLabel.setText(name.get("first").toString());
                    controller.itemLastNameItem.setText(name.get("last").toString());
                    controller.setUserData(user);
                    list.add(listItem);
                }
                listViewPerson.getItems().addAll(list);

            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }

        }catch (IOException io){
            System.out.println(io.getMessage());
        }
    }
}
