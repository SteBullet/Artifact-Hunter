package com.example.artifacthunter.models;

import javafx.scene.image.Image;

//import java.util.Set;

public class Cell {

    private Status status;
    private int row;
    private int col;
    private Image image;

    public Cell(int Col, int Row, Image image) {
        status = Status.NOT_VISITED;
        //String[] index = id.split("separator");
        row = Row;
        col = Col;
        this.image = image;
    }

    /*public void setIndex(int Col, int Row) {
        //String[] index = id.split("separator");
        row = Row;
        col = Col;
    }*/

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
        image = switch (status) {
            case VISITED -> new Image(getClass().getResourceAsStream("/Sprites/Visited Cell.png"));
            case PLAYER_VISITED -> new Image(getClass().getResourceAsStream("/Sprites/Player.png"));
            case NOT_VISITED_MARKED -> new Image(getClass().getResourceAsStream("/Sprites/Marked Cell.png"));
            default -> new Image(getClass().getResourceAsStream("/Sprites/Default Cell.png"));
        };
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
