package com.example.artifacthunter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("SteBullet is beutifull! 123131819984156g!");
    }
}