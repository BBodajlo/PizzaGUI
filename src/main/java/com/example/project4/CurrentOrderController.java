package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class CurrentOrderController {


    @FXML
    private ListView<String> listOfOrder;

    @FXML
    private Button removeItemButton;

    @FXML
    private Button clearOrderButton;

    @FXML
    private Button placeOrderButton;

    private Button mainMenuButton;




    public void initialize()
    {


        //listOfOrder.setItems((FXCollections.observableArrayList(currentOrder.getPizzaList())));
        //listOfOrder.getSelectionModel();


    }

@FXML
    public void removePizza(MouseEvent event) throws IOException{
        Pizza pizzaToRemove = PizzaMainController.getCurrentOrder().getPizzaList().get(listOfOrder.getSelectionModel().getSelectedIndex());
        listOfOrder.getItems().remove(listOfOrder.getSelectionModel().getSelectedIndex());
        PizzaMainController.getCurrentOrder().remove(pizzaToRemove);
    //listOfOrder.getItems().add(currentOrder.getPizzaList().get(0));
    //toppingToRemove = toppingsChosen.getSelectionModel().getSelectedItem();

    }

    @FXML
    public void clearOrder(MouseEvent event) throws IOException{
        PizzaMainController.clearOrder();
        listOfOrder.getItems().clear();

    }
    @FXML
    public void placeOrder(MouseEvent event) throws IOException{
        if(!PizzaMainController.getCurrentOrder().isEmpty()) {
            PizzaMainController.getStoredOrder().add(PizzaMainController.getCurrentOrder());
            PizzaMainController.clearOrder();
            listOfOrder.getItems().clear();
        }
        System.out.println(PizzaMainController.getStoredOrder().toString());
    }



    public void setList(Order order)
    {
        System.out.println(order.toString());
        for(Pizza p : order.getPizzaList())
        {

            listOfOrder.getItems().add(p.toString());
        }
        //listOfOrder.setItems((FXCollections.observableArrayList(order.toString())));
    }



    public void goToMainMenu(ActionEvent event){

        Scene scene = PizzaMainApplication.getMainScene();
        PizzaMainApplication.getmainView().setScene(scene);
        PizzaMainApplication.getmainView().show();

    }


}
