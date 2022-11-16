package com.example.project4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class launches and displays the Pizza Placer application; it's feature includes
 * allowing the user to select from Chicago and New York styled pizza, placing the orders,
 * confirming the orders, and allowing it to be exported.
 * @author Blake Bodajlo, Stanley Jiang
 */
public class PizzaMainApplication extends Application {

    private static Stage mainView, chicagoView, newYorkView, currentOrderView;
    private static Scene mainScene;

    /**
     * Start the main display of the application.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PizzaMainApplication.class.getResource("pizza-view" +
                ".fxml"));
        mainScene = new Scene(fxmlLoader.load(), 1000, 1000);
        stage.setTitle("Pizza Placer");
        stage.setScene(mainScene);
        mainView = stage;
        stage.show();


        chicagoView = new Stage();
        fxmlLoader = new FXMLLoader(PizzaMainApplication.class.getResource(
                "chicagoPizza-view.fxml"));
        Scene sceneChicago = new Scene(fxmlLoader.load(), 1000, 1000);
        chicagoView.setTitle("Chicago Picker");
        chicagoView.setScene(sceneChicago);

        newYorkView = new Stage();
        fxmlLoader = new FXMLLoader(PizzaMainApplication.class.getResource(
                "newYorkPizza-view.fxml"));
        Scene sceneNewYork = new Scene(fxmlLoader.load(), 1000, 1000);
        newYorkView.setTitle("New York Picker");
        newYorkView.setScene(sceneNewYork);

        /*currentOrderView = new Stage();
        fxmlLoader = new FXMLLoader(PizzaMainApplication.class.getResource(
                "currentOrder-view.fxml"));
        Scene sceneCurrentOrder = new Scene(fxmlLoader.load(), 1000, 1000);
        currentOrderView.setTitle("Current Order");
        currentOrderView.setScene(sceneCurrentOrder);*/

    }


    public static Stage getmainView()
    {
        return  mainView;
    }

    public static Stage getChicagoView()
    {
        return chicagoView;
    }

    public static Stage getNewYorkView()
    {
        return newYorkView;
    }

    public static Stage getCurrentOrderView()
    {
        return currentOrderView;
    }

    public static Scene getMainScene(){
        return mainScene;
    }



    public static void main(String[] args) {
        launch();
    }
}