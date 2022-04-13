package com.example.artifacthunter;

import com.example.artifacthunter.models.Cell;
import com.example.artifacthunter.models.Status;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.*;

public class MainController implements Initializable {

    private final Set<Status> NOT_VISITED_NOT_MARKED_SET = Set.of(Status.NOT_VISITED, Status.DANGEROUS_NOT_VISITED, Status.ARTIFACT_NOT_VISITED);

    private final Set<Status> NOT_VISITED_MARKED_SET = Set.of(Status.NOT_VISITED_MARKED, Status.ARTIFACT_NOT_VISITED_MARKED, Status.DANGEROUS_NOT_VISITED_MARKED);

    private int playerCol = 0, playerRow = 0;

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    private GridPane playField;

    private List<List<Cell>> field;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int colCount = 10, rowCount = 10;
        field = new ArrayList<>();
        for (int i = 0; i < colCount; i++) {
            field.add(new ArrayList<>());
            for (int j = 0; j < rowCount; j++) {
                field.get(i).add(new Cell(i, j, new Image(getClass().getResourceAsStream("/Sprites/Default Cell.png"))));
            }
        }
        field.get(playerCol).get(playerRow).setStatus(Status.PLAYER_VISITED);
        RefreshField(10,10);
    }

    private void RefreshField(int colCount, int rowCount) {
        playField.getChildren().removeAll(playField.getChildren());
        for (int i = 0; i < colCount; i++) {
            for (int j = 0; j < rowCount; j++) {
                ImageView iv = new ImageView();
                iv.setImage(field.get(i).get(j).getImage());
                iv.setId(i + "separator" + j);
                iv.setOnMouseClicked(mouseEvent -> {
                    if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                        int col, row;
                        String[] index = iv.getId().split("separator");
                        col = Integer.parseInt(index[0]);
                        row = Integer.parseInt(index[1]);
                        if (Math.abs(playerCol - col) + Math.abs(playerRow - row) == 1) {
                            field.get(playerCol).get(playerRow).setStatus(Status.VISITED);
                            field.get(col).get(row).setStatus(Status.PLAYER_VISITED);
                            playerCol = col;
                            playerRow = row;
                            RefreshField(10, 10);
                        }
                    }
                    else
                    if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                        int col, row;
                        String[] index = iv.getId().split("separator");
                        col = Integer.parseInt(index[0]);
                        row = Integer.parseInt(index[1]);
                        if (NOT_VISITED_NOT_MARKED_SET.contains(field.get(col).get(row).getStatus()))
                        {
                            switch(field.get(col).get(row).getStatus()) {
                                case NOT_VISITED:
                                    field.get(col).get(row).setStatus(Status.NOT_VISITED_MARKED);
                                    break;
                                case ARTIFACT_NOT_VISITED:
                                    field.get(col).get(row).setStatus(Status.ARTIFACT_NOT_VISITED_MARKED);
                                    break;
                                case DANGEROUS_NOT_VISITED:
                                    field.get(col).get(row).setStatus(Status.DANGEROUS_NOT_VISITED_MARKED);
                                    break;
                            }
                        }
                        else
                        if (NOT_VISITED_MARKED_SET.contains(field.get(col).get(row).getStatus()))
                        {
                            switch(field.get(col).get(row).getStatus()) {
                                case NOT_VISITED_MARKED:
                                    field.get(col).get(row).setStatus(Status.NOT_VISITED);
                                    break;
                                case ARTIFACT_NOT_VISITED_MARKED:
                                    field.get(col).get(row).setStatus(Status.ARTIFACT_NOT_VISITED);
                                    break;
                                case DANGEROUS_NOT_VISITED_MARKED:
                                    field.get(col).get(row).setStatus(Status.DANGEROUS_NOT_VISITED);
                                    break;
                            }
                        }
                    }
                });
                playField.add(iv, i, j);
            }
        }
    }

}