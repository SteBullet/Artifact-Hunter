package com.example.artifacthunter;

import com.example.artifacthunter.models.Cell;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
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
                //здесь будет блок кода, выбирающий изображение в зависимости от состояния cell в field
                field.get(i).add(new Cell(i, j, new ImageView("F:\\JavaFX\\Artifact Hunter\\testimg.jpg")));
            }
        }
        /*field = new ArrayList<>();
        for (int i = 0; i < playField.getRowCount(); i++)
        {
            field.add(new ArrayList<>());
            for (int j = 0; j < playField.getColumnCount(); j++)
                field.get(i).add(new Cell("0separator0"));
        }
        playField.getChildren().forEach(cell ->
        {
            if (cell instanceof Button) {
                field.get(GridPane.getRowIndex(cell)).get(GridPane.getColumnIndex(cell)).setIndex(cell.getId());
            }
        });
        Button currentCell = playField.getChildren().forEach(cell ->
        {
            if (cell.getId() == "1separator0")
            {
                Button currentCell = (Button)cell;
                currentCell.setText("5");
            }
        });
        currentCell.setText("Ура");*/
        RefreshField(10,10);
    }

    private void RefreshField(int colCount, int rowCount) {
        playField.getChildren().removeAll(playField.getChildren());
        for (int i = 0; i < colCount; i++) {
            for (int j = 0; j < rowCount; j++) {
                ImageView iv = field.get(i).get(j).getIv();
                iv.setId(i + "separator" + j);
                iv.onMouseClickedProperty().set(Move(iv));
                playField.add(iv, i, j);
            }
        }
    }

    private EventHandler<? super MouseEvent> Move(ImageView iv) {
        int col, row;
        String[] index = iv.getId().split("separator");
        col = Integer.parseInt(index[0]);
        row = Integer.parseInt(index[1]);
        field.get(col).get(row).setIv(new ImageView("F:\\JavaFX\\Artifact Hunter\\rubick.jpg"));
        //RefreshField(10, 10);
        return null;
    }

}