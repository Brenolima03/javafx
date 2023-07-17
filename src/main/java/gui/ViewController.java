package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import model.entities.Person;

public class ViewController implements Initializable {

    @FXML
    private ComboBox<Person> comboBoxPerson;
    
    @FXML
    private Button btAll;
    
    @FXML
    private ObservableList<Person> obsList;
    
    @FXML
    public void onBtAllAction() {
        for (Person person : comboBoxPerson.getItems()) {
            System.out.println(person);
        }
    }



    @FXML
    public void onComboBoxPersonAction() {
        // Retrieves the selected model on combobox
        Person person = comboBoxPerson.getSelectionModel().getSelectedItem();
        System.out.println(person);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Person> list = new ArrayList<>();
        list.add(new Person(1, "Bob", "bob@gmail.com"));
        list.add(new Person(2, "Breno", "breno@gmail.com"));
        list.add(new Person(3, "Eunice", "eunice@gmail.com"));

        obsList = FXCollections.observableArrayList(list);
        comboBoxPerson.setItems(obsList);
    }
}
