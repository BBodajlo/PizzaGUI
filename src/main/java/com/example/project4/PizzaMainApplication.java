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


    }


    public static Stage getmainView()
    {
        return  mainView;
    }


    public static Scene getMainScene(){
        return mainScene;
    }



    public static void main(String[] args) {
        launch();
    }
}