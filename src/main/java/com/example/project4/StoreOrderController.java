package com.example.project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

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
        try {
            Order orderToRemove =
                    PizzaMainController.getStoredOrder().getOrderList().get(storedOrderList.getSelectionModel().getSelectedIndex());
            storedOrderList.getItems().remove(storedOrderList.getSelectionModel().getSelectedIndex());
            PizzaMainController.getStoredOrder().remove(orderToRemove);
            PizzaMainController.getCurrentOrder().setOrderNumber(PizzaMainController.getStoredOrder().getNextOrderNumber());
            storedOrderList.refresh();
        }
        catch (Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Stored Order is Empty!",
                    ButtonType.CLOSE);
            alert.show();
            return;
        }

    }

    public void exportOrder(MouseEvent event) throws IOException {

       FileChooser file = new FileChooser();
        file.setTitle("Select File");
        file.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File sourceFile = file.showOpenDialog(stage);
       // File f = new File("StoredOrder.txt");
        try{
            PrintWriter pw = new PrintWriter(sourceFile);
            System.out.println(PizzaMainController.getStoredOrder().toString());
            pw.print(PizzaMainController.getStoredOrder().toString());
            storedOrderList.getItems().clear();
            pw.close();
        }
        catch(Exception e)
        {
            return;
        }


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
        PizzaMainApplication.getmainView().setTitle("Pizza Placer");
        PizzaMainApplication.getmainView().setScene(scene);
        PizzaMainApplication.getmainView().show();

    }
    public static void initializeStoreOrder()
    {
        storedOrders = new StoreOrders();
    }

}
