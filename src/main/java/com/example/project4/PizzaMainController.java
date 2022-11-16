package com.example.project4;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class controls the graphical user interface of the Main Pizza Picker Menu; it allows
 * the user to go into multiple other menus such as Chicago style, New York Style, current order,
 * and stored orders.
 * @author Blake Bodajlo, Stanley Jiang
 */
public class PizzaMainController {


    @FXML
    private Button chicagoButton;

    @FXML
    private Button newYorkButton;

    @FXML
    private Button currentOrderButton;

    @FXML
    private Button storedOrdersButton;

    @FXML
    private Button exitButton;

    @FXML
    private BorderPane mainPage;

    private static Order currentOrder;

    private static StoreOrders storedOrder;
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Initialize the database for current order and stored order.
     */
    @FXML
    public void initialize() throws IOException {
        storedOrder = new StoreOrders();
        currentOrder = new Order(storedOrder.getNextOrderNumber());

    }

    /**
     * Open the Chicago style menu.
     * @param event When the user clicks on the Chicago Menu image.
     */
    public void OpenChicagoPage(MouseEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("chicagoPizza-view.fxml")));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Chicago Pizza");
        stage.setScene(scene);
        stage.show();



       // PizzaMainApplication.getmainView().hide();
       // PizzaMainApplication.getChicagoView().show();



    }
    /**
     * Open the New York style menu.
     * @param event When the user clicks on the New York Menu image.
     */
    public void OpenNYPage(MouseEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("newYorkPizza-view.fxml")));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("New York Pizza");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Open the current order menu.
     * @param event When the user clicks on the Current order image.
     */
    public void OpenOrderPage(MouseEvent event) throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource(("currentOrder-view.fxml")));
        root = loader.load();

        CurrentOrderController currentOrderController = loader.getController();
        currentOrderController.setList(currentOrder);
        currentOrderController.setPriceAndTax();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Current Order");
        stage.setScene(scene);
        stage.show();

       // PizzaMainApplication.getmainView().hide();
       // PizzaMainApplication.getCurrentOrderView().show();

    }

    /**
     * Open the Stored Orders menu.
     * @param event When the user clicks on the Stored Orders image.
     */
    public void OpenStoredOrderPage(MouseEvent event) throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource(("storeOrder-view.fxml")));
        root = loader.load();

        StoreOrderController storeOrderController = loader.getController();
        storeOrderController.setList(storedOrder);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Stored Orders");
        stage.setScene(scene);
        stage.show();

        // PizzaMainApplication.getmainView().hide();
        // PizzaMainApplication.getCurrentOrderView().show();

    }

    /**
     * Get the current order.
     * @return the current order
     */
    public static Order getCurrentOrder() {
        return currentOrder;
    }

    /**
     * Clear the order.
     */
    public static void clearOrder()
    {
        currentOrder = new Order(storedOrder.getNextOrderNumber());
    }

    /**
     * Get the stored orders.
     * @return the stored orders
     */
    public static StoreOrders getStoredOrder(){
        return storedOrder;
    }

    /**
     * Close the application.
     * @param event When the user clicks on the exits button
     */
    public void CloseApplication(MouseEvent event) throws IOException{

        Platform.exit();

    }


}