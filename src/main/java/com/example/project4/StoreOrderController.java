package com.example.project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class StoreOrderController {


    private static StoreOrders storedOrders;

    @FXML
    private ListView<String> storedOrderList;

    @FXML
    private Button removeOrderButton;

    @FXML
    private Button exportButton;

    @FXML
    private Button mainMenuButton;



    public void initialize()
    {
        storedOrders = new StoreOrders();
    }
    public static StoreOrders getStoredOrders() {
        return storedOrders;
    }

    public void removeOrder(MouseEvent event){
        Order orderToRemove =
                PizzaMainController.getStoredOrder().getOrderList().get(storedOrderList.getSelectionModel().getSelectedIndex());
        storedOrderList.getItems().remove(storedOrderList.getSelectionModel().getSelectedIndex());
        PizzaMainController.getStoredOrder().remove(orderToRemove);
        PizzaMainController.getCurrentOrder().setOrderNumber(PizzaMainController.getStoredOrder().getNextOrderNumber());
        storedOrderList.refresh();


    }

    public void exportOrder(MouseEvent event){


    }

    public void setList(StoreOrders orders)
    {
        System.out.println(orders.toString());
        for(Order o : orders.getOrderList())
        {
            storedOrderList.getItems().add(o.toString());
        }
        //listOfOrder.setItems((FXCollections.observableArrayList(order.toString())));
    }

    public void goToMainMenu(MouseEvent event){

        Scene scene = PizzaMainApplication.getMainScene();
        PizzaMainApplication.getmainView().setScene(scene);
        PizzaMainApplication.getmainView().show();

    }
    public static void initializeStoreOrder()
    {
        storedOrders = new StoreOrders();
    }

}
