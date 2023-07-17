package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class MainViewController implements Initializable {

    @FXML
    private MenuItem menuItemSeller;
    @FXML
    private MenuItem menuItemDepartment;
    @FXML
    private MenuItem menuItemAbout;
    
    @FXML
    public void onMenuIteSellerAction() {
        System.out.println("onMenuIteSellerAction");
    }
    
    @FXML
    public void onMenuIteDepartmentAction() {
        System.out.println("onMenuIteDepartmentAction");
    }
    
    @FXML
    public void onMenuIteAboutAction() {
        System.out.println("onMenuIteAboutAction");
    }
    
    @Override
    public void initialize(URL uri, ResourceBundle rb) {
       
    }   
}
