package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

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
        try {
            Pizza pizzaToRemove = PizzaMainController.getCurrentOrder().getPizzaList().get(listOfOrder.getSelectionModel().getSelectedIndex());
            listOfOrder.getItems().remove(listOfOrder.getSelectionModel().getSelectedIndex());
            PizzaMainController.getCurrentOrder().remove(pizzaToRemove);
        }
        catch(NullPointerException | IndexOutOfBoundsException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Order is Empty!",
                    ButtonType.CLOSE);
            alert.show();
            return;
        }
    //listOfOrder.getItems().add(currentOrder.getPizzaList().get(0));
    //toppingToRemove = toppingsChosen.getSelectionModel().getSelectedItem();

    }

    @FXML
    public void clearOrder(MouseEvent event) throws IOException{
        if(!PizzaMainController.getCurrentOrder().isEmpty()) {
            PizzaMainController.clearOrder();
            listOfOrder.getItems().clear();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Order is Empty!",
                    ButtonType.CLOSE);
            alert.show();
        }

    }
    @FXML
    public void placeOrder(MouseEvent event) throws IOException{
        if(!PizzaMainController.getCurrentOrder().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm placing order",
                    ButtonType.OK, ButtonType.CANCEL);
            Optional<ButtonType> choice = alert.showAndWait();

            if(choice.get() == ButtonType.OK) {
                PizzaMainController.getStoredOrder().add(PizzaMainController.getCurrentOrder());
                PizzaMainController.clearOrder();
                listOfOrder.getItems().clear();
            }
            else{
                alert.close();
            }

        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Order is Empty!",
                    ButtonType.CLOSE);
            alert.show();
        }
        //System.out.println(PizzaMainController.getStoredOrder().toString());
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
        PizzaMainApplication.getmainView().setTitle("Pizza Placer");
        PizzaMainApplication.getmainView().setScene(scene);
        PizzaMainApplication.getmainView().show();

    }


}
