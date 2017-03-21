package pl.losK.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;

public class MenuController extends Controller {

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem logout;

    @FXML
    private StackPane contentPane;

    @FXML
    private Menu makeBill;

    @FXML
    void logoutOnAction(ActionEvent event) {
        getMainController().loadView("LoginView");
    }

    public StackPane getContentPane() {
        return contentPane;
    }

    public void setContentPane(StackPane contentPane) {
        this.contentPane = contentPane;
    }

    @FXML
    void initialize() {
        contentPane.setMinHeight(470);
        setMenuController(this);
    }

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

    @FXML
    private MenuItem edit;

    @FXML
    void editOnAction(ActionEvent event) {
        loadContentView("CompanyCreateView");
    }

    public void loadContentView(String viewName) {
        getMainController().loadView(viewName, contentPane);
    }

    @FXML
    private MenuItem addProduct;

    @FXML
    void addProductOnAction(ActionEvent event) {
        loadContentView("ComponentView");
    }

    @FXML
    void makeBillOnAction(ActionEvent event) {
        loadContentView("BillFormView");
    }
}
