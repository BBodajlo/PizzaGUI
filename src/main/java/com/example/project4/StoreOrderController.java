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

/**
 * This class controls the graphical user interface of the Stored Orders Menu.
 * Displaying all the confirmed orders in chronological orders; allowing it to be removed
 * and exported to an external text file.
 * @author Blake Bodajlo, Stanley Jiang
 */
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


    /**
     * Initialize the database for storing the stored orders
     */
    public void initialize()
    {
        storedOrders = new StoreOrders();
    }

    /**
     * Get the store orders
     * @return The stored orders
     */
    public static StoreOrders getStoredOrders() {
        return storedOrders;
    }

    /**
     * Remove a selected stored order from the display. When the stored order
     * list is empty it will display an error alert box.
     * @param event When the user clicks on the Remove order button.
     */
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
    /**
     * Export all the stored orders to an external text file.
     * @param event When the user clicks on the Export button.
     */
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
            pw.print(PizzaMainController.getStoredOrder().toString());
            storedOrderList.getItems().clear();
            pw.close();
        }
        catch(Exception e)
        {
            return;
        }


    }

    /**
     * Set the stored orders list.
     * @param orders The stored orders
     */
    public void setList(StoreOrders orders)
    {
        for(Order o : orders.getOrderList())
        {
            storedOrderList.getItems().add(o.toString());
        }
        //listOfOrder.setItems((FXCollections.observableArrayList(order.toString())));
    }

    /**
     * Allow the user to go back to the main menu.
     * @param event When the user clicks on the main menu button.
     */
    public void goToMainMenu(MouseEvent event){

        Scene scene = PizzaMainApplication.getMainScene();
        PizzaMainApplication.getmainView().setTitle("Pizza Placer");
        PizzaMainApplication.getmainView().setScene(scene);
        PizzaMainApplication.getmainView().show();

    }

    /**
     * Create a database for stored orders.
     */
    public static void initializeStoreOrder()
    {
        storedOrders = new StoreOrders();
    }

}
