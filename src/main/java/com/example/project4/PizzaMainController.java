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

    @FXML
    public void initialize() throws IOException {
        storedOrder = new StoreOrders();
        currentOrder = new Order(storedOrder.getNextOrderNumber());

    }


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
    public void OpenNYPage(MouseEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("newYorkPizza-view.fxml")));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("New York Pizza");
        stage.setScene(scene);
        stage.show();

    }

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

    public static Order getCurrentOrder() {
        return currentOrder;
    }

    public static void clearOrder()
    {
        currentOrder = new Order(storedOrder.getNextOrderNumber());
    }

    public static StoreOrders getStoredOrder(){
        return storedOrder;
    }

    public void CloseApplication(MouseEvent event) throws IOException{

        Platform.exit();

    }


}