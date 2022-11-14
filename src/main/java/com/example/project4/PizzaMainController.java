package com.example.project4;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class PizzaMainController {


    @FXML
    private Button chicagoButton;

    @FXML
    private Button newYorkButton;

    @FXML
    private Button currentOrderButton;

    @FXML
    private Button storedOrdersButton;

    @FXML
    private Button exitButton;

    @FXML
    private BorderPane mainPage;




    @FXML
    public void initialize() throws IOException {


    }


    public void OpenChicagoPage(MouseEvent event) throws IOException{
        PizzaMainApplication.getmainView().hide();
        PizzaMainApplication.getChicagoView().show();



    }

    public void OpenOrderPage(MouseEvent event) throws IOException{
        PizzaMainApplication.getmainView().hide();
        PizzaMainApplication.getCurrentOrderView().show();




    }
    public void CloseApplication(MouseEvent event) throws IOException{

        Platform.exit();

    }


}