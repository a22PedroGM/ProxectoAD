package com.example.adproyect;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControllerHoteles {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}