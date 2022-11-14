package com.example.project4;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class CurrentOrderController {


    @FXML
    private ListView listOfOrder;

    @FXML
    private Button removeItemButton;

    @FXML
    private Button clearOrderButton;

    @FXML
    private Button placeOrderButton;

    private static Order currentOrder;

    private String[] list = {"sgs", "dsgadfsgs", " rdfgsdfhg"};


    public void initialize()
    {

        currentOrder = new Order(StoreOrderController.getStoredOrders().getNextOrderNumber());
        //listOfOrder = new ListView<>();
        //listOfOrder.setItems(FXCollections.observableArrayList(list));
        listOfOrder.setItems(FXCollections.observableArrayList(currentOrder.getPizzaList()));


    }

@FXML
    public void removePizza(MouseEvent event) throws IOException{

                System.out.println(currentOrder.toString());
    }

    @FXML
    public void clearOrder(MouseEvent event) throws IOException{

    }
    @FXML
    public void placeOrder(MouseEvent event) throws IOException{

    }

    public static Order getCurrentOrder() {
        return currentOrder;
    }


    public static void initializeOrderList()
    {

    }





}
