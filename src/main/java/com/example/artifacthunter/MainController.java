package com.example.artifacthunter;

import com.example.artifacthunter.models.Cell;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.artifacthunter.Application.field;

public class MainController implements Initializable {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    private GridPane playField;

    //private List<List<Cell>> field;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (List<Cell> line : field)
            line = new ArrayList<>(3);
        playField.getChildren().stream().forEach(cell -> field.get(GridPane.getRowIndex(cell)).add(GridPane.getColumnIndex(cell), new Cell(cell.getId())));

        Button currentCell = (Button)playField.getChildren().stream().filter(cell -> cell.getId() == "aa").findFirst().orElseThrow();
        currentCell.setText("Ура");
    }

}