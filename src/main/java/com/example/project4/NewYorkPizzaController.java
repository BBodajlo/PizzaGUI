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
import java.util.Iterator;
import java.util.Optional;


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



    public void initialize(){
        System.out.println("here");
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
        System.out.println(currentPizza.getToppings().size());
        handleToppingsList();
        StoreOrderController.initializeStoreOrder();
        //



    }

    @FXML
    public void updateCrustAndPic(ActionEvent action) throws IOException{

        if(typeComboBox.getValue().equals(PizzaType.Meatzza))
        {
            crustType.setText(Crust.Stuffed.toString());
            file = new File("src/main/resources/com/example/project4/MeatNY.jpg");
            newYorkPizzaPicture.setImage(new Image(file.toURI().toString()));
            currentPizza = newYorkPizzaBuilder.createMeatzza();
            updateSizePrice(action);
            handleToppingsList();
        }
        else if(typeComboBox.getValue().equals(PizzaType.BBQChicken))
        {
            crustType.setText(Crust.Deep_Dish.toString());
            file = new File("src/main/resources/com/example/project4/ChickenNY.jpg");
            newYorkPizzaPicture.setImage(new Image(file.toURI().toString()));
            crustType.setText(Crust.Pan.toString());
            currentPizza = newYorkPizzaBuilder.createBBQChicken();
            updateSizePrice(action);
            handleToppingsList();
        }
        else if(typeComboBox.getValue().equals(PizzaType.BuildYouOwn))
        {
            crustType.setText(Crust.Pan.toString());
            crustType.setText(Crust.Deep_Dish.toString());
            file = new File("src/main/resources/com/example/project4/BYONY.jpg");
            newYorkPizzaPicture.setImage(new Image(file.toURI().toString()));
            currentPizza = newYorkPizzaBuilder.createBuildYourOwn();
            updateSizePrice(action);
            handleToppingsList();
        }
        else if(typeComboBox.getValue().equals(PizzaType.Deluxe))
        {
            crustType.setText(Crust.Deep_Dish.toString());
            file = new File("src/main/resources/com/example/project4/DeluxeNY.jpg");
            newYorkPizzaPicture.setImage(new Image(file.toURI().toString()));
            currentPizza = newYorkPizzaBuilder.createDeluxe();
            updateSizePrice(action);
            handleToppingsList();

        }
        System.out.println(currentPizza.price());
        updatePrice();


    }


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

    @FXML
    public void addToppingToPizza(MouseEvent event) throws IOException
    {
        System.out.println(currentPizza.getToppings().size());
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

    public void updatePrice()
    {
        priceLabel.setText(String.valueOf(currentPizza.price()));

    }

    public void addOrder(MouseEvent event) throws IOException{

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

        //System.out.println(CurrentOrderController.getCurrentOrder().toString());
    }


    public void backToMenu(MouseEvent event)
    {
        Scene scene = PizzaMainApplication.getMainScene();
        PizzaMainApplication.getmainView().setTitle("Pizza Placer");
        PizzaMainApplication.getmainView().setScene(scene);
        PizzaMainApplication.getmainView().show();

    }




}
