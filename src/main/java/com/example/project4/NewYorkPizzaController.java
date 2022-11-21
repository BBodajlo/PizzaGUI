package com.example.project4;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

/**
 * This class controls the graphical user interface of the New York Style Pizza Menu.
 * Allowing the user to select the desired pizza types; types featuring BBQChicken,
 * Deluxe, and Meatzza. Custom pizzas can also be built with all topping selectable.
 * Price changes dynamically upon selection and all confirmed orders will be inserted to
 * the current order menu.
 * @author Blake Bodajlo, Stanley Jiang
 */
public class NewYorkPizzaController {



    @FXML
    private ComboBox<PizzaType> typeComboBox;

    @FXML
    private TextField crustType;

    @FXML
    private RadioButton smallSize;

    @FXML
    private RadioButton mediumSize;

    @FXML
    private RadioButton largeSize;

    @FXML
    private ListView<Topping> toppingsList;

    @FXML
    private ListView<Topping> toppingsChosen;

    @FXML
    private Button addToppingButton;

    @FXML
    private Button removeToppingButton;

    @FXML
    private ImageView newYorkPizzaPicture;
    @FXML
    private TextField priceLabel;

    @FXML
    private ToggleGroup sizeGroup;

    @FXML
    private Button newYorkPlaceOrder;

    private File file;
    private Image pizzaPicture;

    private Pizza currentPizza;
    private PizzaFactory newYorkPizzaBuilder;


    /**
     * This initializes the default view of the New York Style Menu,
     * which being Pizza Type: Deluxe, Crust: Brooklyn, and Size: small.
     */
    public void initialize(){
        typeComboBox.setItems(FXCollections.observableArrayList(PizzaType.values()));
        typeComboBox.getSelectionModel().select(0);
        newYorkPizzaBuilder = new NYPizza();
        currentPizza = newYorkPizzaBuilder.createDeluxe();
        currentPizza.setSize(Size.Small);
        smallSize.setSelected(true);
        crustType.setText(currentPizza.getCrust().toString());
        toppingsList.setItems(FXCollections.observableArrayList(Topping.values()));
        file = new File("src/main/resources/com/example/project4/DeluxeNY.jpg");
        pizzaPicture = new Image(file.toURI().toString());
        newYorkPizzaPicture.setImage(pizzaPicture);
        updatePrice();
        handleToppingsList();
        StoreOrderController.initializeStoreOrder();
        //



    }

    /**
     * Dynamically changes the displayed pizza image,
     * toppings, price, and crust upon the selection of the pizza types.
     * @param action When the user selects the combo box.
     */
    @FXML
    public void updateCrustAndPic(ActionEvent action) throws IOException{

        if(typeComboBox.getValue().equals(PizzaType.Meatzza))
        {
            file = new File("src/main/resources/com/example/project4/MeatNY.jpg");
            newYorkPizzaPicture.setImage(new Image(file.toURI().toString()));
            currentPizza = newYorkPizzaBuilder.createMeatzza();
            crustType.setText(currentPizza.getCrust().toString());
            updateSizePrice(action);
            handleToppingsList();
        }
        else if(typeComboBox.getValue().equals(PizzaType.BBQChicken))
        {
            file = new File("src/main/resources/com/example/project4/ChickenNY.jpg");
            newYorkPizzaPicture.setImage(new Image(file.toURI().toString()));
            currentPizza = newYorkPizzaBuilder.createBBQChicken();
            crustType.setText(currentPizza.getCrust().toString());
            updateSizePrice(action);
            handleToppingsList();
        }
        else if(typeComboBox.getValue().equals(PizzaType.BuildYouOwn))
        {
            file = new File("src/main/resources/com/example/project4/BYONY.jpg");
            newYorkPizzaPicture.setImage(new Image(file.toURI().toString()));
            currentPizza = newYorkPizzaBuilder.createBuildYourOwn();
            crustType.setText(currentPizza.getCrust().toString());
            updateSizePrice(action);
            handleToppingsList();
        }
        else if(typeComboBox.getValue().equals(PizzaType.Deluxe))
        {
            file = new File("src/main/resources/com/example/project4/DeluxeNY.jpg");
            newYorkPizzaPicture.setImage(new Image(file.toURI().toString()));
            currentPizza = newYorkPizzaBuilder.createDeluxe();
            crustType.setText(currentPizza.getCrust().toString());
            updateSizePrice(action);
            handleToppingsList();

        }
        updatePrice();


    }

    /**
     * This method handles the topping if the current pizza is an instance
     * BuildYourOwn.
     */
    public void handleToppingsList()
    {
        if(!(currentPizza instanceof BuildYourOwn)) {

            toppingsList.setItems(FXCollections.observableArrayList(Topping.values()));
            toppingsChosen.getItems().clear();
            for (int i = 0; i < currentPizza.getToppings().size(); i++) {
                toppingsChosen.getItems().add(currentPizza.getToppings().get(i));
                toppingsList.getItems().remove(currentPizza.getToppings().get(i));
            }
        }
        else{
            toppingsList.setItems(FXCollections.observableArrayList(Topping.values()));
            toppingsChosen.getItems().clear();
        }


    }

    /**
     * Allows the addition of different topping for a custom pizza to add topping list
     * and update the price dynamically for each topping added.
     * The maximum topping allowed for a pizza is 7.
     * @param event When the user clicks on the right arrow button.
     */
    @FXML
    public void addToppingToPizza(MouseEvent event) throws IOException
    {
        if(currentPizza instanceof BuildYourOwn) {
            if (currentPizza.getToppings().size() >= 7) {
                toppingsList.setEditable(false);
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "Exceeded topping limit!",
                        ButtonType.OK);
            }
            Topping toppingToAdd;
            if (currentPizza.getToppings().size() < 7) {
                toppingsList.setEditable(true);
                try {
                    toppingToAdd = toppingsList.getSelectionModel().getSelectedItem();
                    if (toppingToAdd == null)
                        throw new NullPointerException();
                } catch (NullPointerException e) {
                    return;
                }
                currentPizza.add(toppingToAdd);
                toppingsChosen.getItems().add(toppingToAdd);
                toppingsList.getItems().remove(toppingToAdd);
                toppingsList.getSelectionModel().clearSelection();

                this.updatePrice();

            }
        }
    }



    /**
     * Allows the removal of different topping for a custom pizza and update
     * the price dynamically for each topping removed.
     * @param event When the user clicks on the left arrow button.
     */
    @FXML
    public void removeToppingFromPizza(MouseEvent event) throws IOException
    {
        Topping toppingToRemove;

        if(currentPizza instanceof BuildYourOwn) {
            try {
                toppingToRemove = toppingsChosen.getSelectionModel().getSelectedItem();
                if (toppingToRemove == null)
                    throw new NullPointerException();
            } catch (NullPointerException e) {
                return;
            }

            currentPizza.remove(toppingToRemove);
            toppingsList.getItems().add(toppingToRemove);
            toppingsChosen.getItems().remove(toppingToRemove);
            toppingsChosen.getSelectionModel().clearSelection();
            updatePrice();
        }
    }

    /**
     * Set the price of the pizza depending on the selected sizes.
     * Size being small, medium, or large.
     * @param event When the user clicks on the radio button.
     */
    public void updateSizePrice(ActionEvent event)
    {

        if(smallSize.isSelected()) {
            currentPizza.setSize(Size.Small);
        }
        else if(mediumSize.isSelected()) {
            currentPizza.setSize(Size.Medium);
        }
        else if(largeSize.isSelected()) {
            currentPizza.setSize(Size.Large);
        }

        updatePrice();

    }

    /**
     * Update the price of text area to the current pizza.
     */
    public void updatePrice()
    {
        priceLabel.setText(String.valueOf(currentPizza.price()));

    }

    /**
     * Add order to the current order menu, when the user clicks the button
     * an alert will pop up that allows the user to confirm or cancel their order.
     * @param event When the user clicks on the "add to order" button.
     */
    public void addOrder(MouseEvent event) throws IOException{
        ArrayList<Topping> tempToppings = currentPizza.getToppings();
        Size tempSize = currentPizza.getSize();
        Crust tempCrust = currentPizza.getCrust();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Add\n" + currentPizza.toString() + "to the order?",
                ButtonType.OK, ButtonType.CANCEL);
        Optional<ButtonType> choice = alert.showAndWait();

        if(choice.get() == ButtonType.OK) {
            PizzaMainController.getCurrentOrder().add(currentPizza);
        }
        else {
            alert.close();
        }
        //refreshes pizza object to a new memory location
        //without it, adding a pizza to an order without switching type results in
        //the same object being added each time; only caused issues with buildyourown
        //but may as well have each pizza be a unique object
        if(currentPizza instanceof BuildYourOwn) {
            currentPizza = newYorkPizzaBuilder.createBuildYourOwn();
            currentPizza.setSize(tempSize);
            currentPizza.setCrust(tempCrust);
            currentPizza.initializeToppings();
            for (Topping t : tempToppings) {
                currentPizza.add(t);
            }
        }
        else if( currentPizza instanceof Meatzza)
        {
            currentPizza = newYorkPizzaBuilder.createMeatzza();
            currentPizza.setSize(tempSize);
        }
        else if( currentPizza instanceof BBQChicken)
        {
            currentPizza = newYorkPizzaBuilder.createBBQChicken();
            currentPizza.setSize(tempSize);
        }
        else if( currentPizza instanceof Deluxe)
        {
            currentPizza = newYorkPizzaBuilder.createDeluxe();
            currentPizza.setSize(tempSize);
        }

        //System.out.println(CurrentOrderController.getCurrentOrder().toString());
    }

    /**
     * Allows the user to go back to the main menu from New York Style menu.
     * @param event When the user clicks on the "Main Menu" button.
     */
    public void backToMenu(MouseEvent event)
    {
        Scene scene = PizzaMainApplication.getMainScene();
        PizzaMainApplication.getmainView().setTitle("Pizza Placer");
        PizzaMainApplication.getmainView().setScene(scene);
        PizzaMainApplication.getmainView().show();

    }




}
