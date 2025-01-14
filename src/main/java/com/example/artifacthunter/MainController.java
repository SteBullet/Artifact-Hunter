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
    /**
     * Set of not visited, not marked statuses of cell
     */
    private final Set<Status> NOT_VISITED_NOT_MARKED_SET = Set.of(Status.NOT_VISITED, Status.DANGEROUS_NOT_VISITED, Status.ARTIFACT_NOT_VISITED);
    /**
     * Set of not visited, marked statuses of cell
     */
    private final Set<Status> NOT_VISITED_MARKED_SET = Set.of(Status.NOT_VISITED_MARKED, Status.ARTIFACT_NOT_VISITED_MARKED, Status.DANGEROUS_NOT_VISITED_MARKED);
    /**
     * Set of dangerous statuses of cell
     */
    private final Set<Status> DANGEROUS_SET = Set.of(Status.DANGEROUS_NOT_VISITED, Status.DANGEROUS_NOT_VISITED_MARKED);
    /**
     * Player Column
     */
    private int playerCol = 0;
    /**
     * Player Row
     */
    private int playerRow = 0;

    @FXML
    private Label StatusLabel;

    @FXML
    private Label ThreatsLabel;

    @FXML
    private Label DistanceLabel;

    @FXML
    protected void onRestartButtonClick() {
        StatusLabel.setText("");
        GenerateField(10, 10, 15);
    }

    @FXML
    private GridPane playField;

    /**
     * List of playfield cells
     */
    private List<List<Cell>> field;

    /**
     * Random filling of the field
     * @param colCount
     * @param rowCount
     */
    public void GenerateField(int colCount, int rowCount, int countOfTreats)
    {
        if (countOfTreats > colCount * rowCount / 2)
            StatusLabel.setText("Too many threats!");
        else {
            for (int i = 0; i < colCount + 2; i++)
                for (int j = 0; j < rowCount + 2; j++) {
                    field.get(i).get(j).setStatus(Status.NOT_VISITED);
                    field.get(i).get(j).setThreats(0);
                }
            int randCol;
            int randRow;
            int cOT = countOfTreats;
            while (cOT > 0) {
                randCol = (int) (Math.random() * (colCount - 2) + 2);
                randRow = (int) (Math.random() * (rowCount - 2) + 2);
                if (field.get(randCol).get(randRow).getStatus() != Status.DANGEROUS_NOT_VISITED) {
                    field.get(randCol).get(randRow).setStatus(Status.DANGEROUS_NOT_VISITED);
                    cOT--;
                }
            }

            int c, r;
            c = (int) (Math.random() * (colCount - 2) + 2);
            r = (int) (Math.random() * (rowCount - 2) + 2);
            while (field.get(c).get(r).getStatus() == Status.DANGEROUS_NOT_VISITED) {
                c = (int) (Math.random() * (colCount - 2) + 2);
                r = (int) (Math.random() * (rowCount - 2) + 2);
            }
            field.get(c).get(r).setStatus(Status.ARTIFACT_NOT_VISITED);

            for (int i = 1; i < colCount + 1; i++)
                for (int j = 1; j < rowCount + 1; j++) {
                    if (field.get(i - 1).get(j - 1).getStatus() == Status.DANGEROUS_NOT_VISITED)
                        field.get(i).get(j).setThreats(field.get(i).get(j).getThreats() + 1);
                    if (field.get(i).get(j - 1).getStatus() == Status.DANGEROUS_NOT_VISITED)
                        field.get(i).get(j).setThreats(field.get(i).get(j).getThreats() + 1);
                    if (field.get(i + 1).get(j - 1).getStatus() == Status.DANGEROUS_NOT_VISITED)
                        field.get(i).get(j).setThreats(field.get(i).get(j).getThreats() + 1);
                    if (field.get(i + 1).get(j).getStatus() == Status.DANGEROUS_NOT_VISITED)
                        field.get(i).get(j).setThreats(field.get(i).get(j).getThreats() + 1);
                    if (field.get(i + 1).get(j + 1).getStatus() == Status.DANGEROUS_NOT_VISITED)
                        field.get(i).get(j).setThreats(field.get(i).get(j).getThreats() + 1);
                    if (field.get(i).get(j + 1).getStatus() == Status.DANGEROUS_NOT_VISITED)
                        field.get(i).get(j).setThreats(field.get(i).get(j).getThreats() + 1);
                    if (field.get(i - 1).get(j + 1).getStatus() == Status.DANGEROUS_NOT_VISITED)
                        field.get(i).get(j).setThreats(field.get(i).get(j).getThreats() + 1);
                    if (field.get(i - 1).get(j).getStatus() == Status.DANGEROUS_NOT_VISITED)
                        field.get(i).get(j).setThreats(field.get(i).get(j).getThreats() + 1);
                    field.get(i).get(j).setDistance(Math.sqrt(Math.pow(Math.abs(i - c), 2) + Math.pow(Math.abs(j - r), 2)));
                }
            RefreshFieldStart(10, 10);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        int colCount = 10;
        int rowCount = 10;
        field = new ArrayList<>();
        for (int i = 0; i < colCount + 2; i++) {
            field.add(new ArrayList<>());
            for (int j = 0; j < rowCount + 2; j++) {
                field.get(i).add(new Cell(i, j, new Image(getClass().getResourceAsStream("/Sprites/Default Cell.png"))));
            }
        }
        GenerateField(10, 10, 15);
    }

    /**
     * Refreshing field at first time of new game
     * @param colCount
     * @param rowCount
     */
    private void RefreshFieldStart(int colCount, int rowCount) {
        playField.getChildren().removeAll(playField.getChildren());
        for (int i = 1; i < colCount+1; i++) {
            for (int j = 1; j < rowCount+1; j++) {
                ImageView iv = new ImageView();
                try {
                    iv.setImage(field.get(i).get(j).getImage());
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    StatusLabel.setText("Ошибка. Изображения не найдены.");
                }
                iv.setId(i + "separator" + j);
                if (i == 1 || i == colCount || j == 1 || j == rowCount)
                iv.setOnMouseClicked(mouseEvent -> {
                    if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                        int col, row;
                        String[] index = iv.getId().split("separator");
                        col = Integer.parseInt(index[0]);
                        row = Integer.parseInt(index[1]);
                        playerCol = col;
                        playerRow = row;
                        field.get(col).get(row).setStatus(Status.PLAYER_VISITED);
                        ThreatsLabel.setText("Threats: " + field.get(col).get(row).getThreats());
                        DistanceLabel.setText("Distance: " + field.get(col).get(row).getDistance());
                        RefreshField(10, 10);
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
                            RefreshField(10, 10);
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
                            RefreshField(10, 10);
                        }
                    }
                });
                playField.add(iv, i, j);
            }
        }
        ThreatsLabel.setText("Threats: 0");
        DistanceLabel.setText("Distance: unknown");
    }

    /**
     * Refreshing field every turn (except fist and last round)
     * @param colCount
     * @param rowCount
     */
    private void RefreshField(int colCount, int rowCount) {
        playField.getChildren().removeAll(playField.getChildren());
        for (int i = 1; i < colCount+1; i++) {
            for (int j = 1; j < rowCount+1; j++) {
                ImageView iv = new ImageView();
                try {
                    iv.setImage(field.get(i).get(j).getImage());
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    StatusLabel.setText("Ошибка. Изображения не найдены.");
                }
                iv.setId(i + "separator" + j);
                iv.setOnMouseClicked(mouseEvent -> {
                    if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                        int col, row;
                        String[] index = iv.getId().split("separator");
                        col = Integer.parseInt(index[0]);
                        row = Integer.parseInt(index[1]);
                        if (Math.abs(playerCol - col) + Math.abs(playerRow - row) == 1) {
                            field.get(playerCol).get(playerRow).setStatus(Status.VISITED);
                            playerCol = col;
                            playerRow = row;
                            if (DANGEROUS_SET.contains(field.get(col).get(row).getStatus()))
                            {
                                field.get(col).get(row).setStatus(Status.PLAYER_DANGEROUS);
                                StatusLabel.setText("GAME OVER");
                                RefreshFieldEnd(10, 10);
                            }
                            else {
                                if (field.get(col).get(row).getStatus() == Status.ARTIFACT_NOT_VISITED)
                                {
                                    field.get(col).get(row).setStatus(Status.PLAYER_ARTIFACT);
                                    StatusLabel.setText("YOU FIND THE ARTIFACT! YOU WIN!");
                                    RefreshFieldEnd(10, 10);
                                }
                                else
                                {
                                    field.get(col).get(row).setStatus(Status.PLAYER_VISITED);
                                    RefreshField(10, 10);
                                }
                            }
                            ThreatsLabel.setText("Threats: " + field.get(col).get(row).getThreats());
                            DistanceLabel.setText("Distance: " + field.get(col).get(row).getDistance());
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
                            RefreshField(10, 10);
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
                            RefreshField(10, 10);
                        }
                    }
                });
                playField.add(iv, i, j);
            }
        }
    }

    /**
     * Refreshing field at last time of the game
     * @param colCount
     * @param rowCount
     */
    private void RefreshFieldEnd(int colCount, int rowCount) {
        playField.getChildren().removeAll(playField.getChildren());
        for (int i = 1; i < colCount+1; i++) {
            for (int j = 1; j < rowCount+1; j++) {
                ImageView iv = new ImageView();
                if (field.get(i).get(j).getStatus() == Status.ARTIFACT_NOT_VISITED || field.get(i).get(j).getStatus() == Status.ARTIFACT_NOT_VISITED_MARKED)
                    field.get(i).get(j).setStatus(Status.ARTIFACT_OPEN);
                if (field.get(i).get(j).getStatus() == Status.DANGEROUS_NOT_VISITED || field.get(i).get(j).getStatus() == Status.DANGEROUS_NOT_VISITED_MARKED)
                    field.get(i).get(j).setStatus(Status.DANGEROUS_OPEN);
                try {
                    iv.setImage(field.get(i).get(j).getImage());
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    StatusLabel.setText("Ошибка. Изображения не найдены.");
                }
                iv.setId(i + "separator" + j);
                playField.add(iv, i, j);
            }
        }
    }

}