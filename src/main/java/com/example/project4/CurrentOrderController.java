package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * This class controls the graphical user interface of the Current Order Menu.
 * Displaying all the confirmed pizza orders in a chronological order; along with the
 * subtotal, tax and price. Allowing the user to remove, clear, or place the order.
 * When an order is placed it is inserted into Stored Orders.
 * @author Blake Bodajlo, Stanley Jiang
 */
public class CurrentOrderController {


    @FXML
    private ListView<String> listOfOrder;

    @FXML
    private Button removeItemButton;

    @FXML
    private Button clearOrderButton;

    @FXML
    private Button placeOrderButton;

    @FXML
    private Button mainMenuButton;

    @FXML
    private TextField taxBox;

    @FXML
    private TextField priceBox;

    @FXML
    private TextField subtotalBox;

    /**
     * Initialize the Current Order Menu.
     */
    public void initialize()
    {


        //listOfOrder.setItems((FXCollections.observableArrayList(currentOrder.getPizzaList())));
        //listOfOrder.getSelectionModel();


    }

    /**
     * Removes a pizza from the user's order when the user selects on an item from the
     * current order list.
     * @param event When the user clicks on the "Remove Item" button.
     */
@FXML
    public void removePizza(MouseEvent event) throws IOException{
        try {
            Pizza pizzaToRemove = PizzaMainController.getCurrentOrder().getPizzaList().get(listOfOrder.getSelectionModel().getSelectedIndex());
            listOfOrder.getItems().remove(listOfOrder.getSelectionModel().getSelectedIndex());
            PizzaMainController.getCurrentOrder().remove(pizzaToRemove);
            setPriceAndTax();
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

    /**
     * Clears all the current orders from the orders lists.
     * @param event When the user clicks on the "Clear Order" button.
     */
    @FXML
    public void clearOrder(MouseEvent event) throws IOException{
        if(!PizzaMainController.getCurrentOrder().isEmpty()) {
            PizzaMainController.clearOrder();
            listOfOrder.getItems().clear();
            setPriceAndTax();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Order is Empty!",
                    ButtonType.CLOSE);
            alert.show();
        }

    }
    /**
     * Allows the user to confirm and place their desired orders. When the
     * user clicked on place order button, an alert button will pop up asking if the user
     * would like to confirm their order. Confirmed orders will be inserted into Stored orders.
     * @param event When the user clicks on the "Place Order" button.
     */
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
                setPriceAndTax();
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


    /**
     * Set the items of the current orders.
     */
    public void setList(Order order)
    {
        for(Pizza p : order.getPizzaList())
        {

            listOfOrder.getItems().add(p.toString());
        }
        //listOfOrder.setItems((FXCollections.observableArrayList(order.toString())));
    }

    /**
     * Set the subtotal, tax, and price of the text areas.
     */
    public void setPriceAndTax()
    {
        taxBox.setText(String.valueOf(PizzaMainController.getCurrentOrder().getSalesTax()));
        priceBox.setText(String.valueOf(PizzaMainController.getCurrentOrder().getTotal()));
        subtotalBox.setText(String.valueOf(PizzaMainController.getCurrentOrder().getTotalWithoutTax()));

    }
    /**
     * Allows the user to go back to the main menu.
     * @param event When the user clicks on the "Main Menu" button.
     */
    public void goToMainMenu(ActionEvent event){

        Scene scene = PizzaMainApplication.getMainScene();
        PizzaMainApplication.getmainView().setTitle("Pizza Placer");
        PizzaMainApplication.getmainView().setScene(scene);
        PizzaMainApplication.getmainView().show();

    }




}
