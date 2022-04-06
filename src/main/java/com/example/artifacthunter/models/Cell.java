package com.example.artifacthunter.models;

import javafx.scene.image.ImageView;

//import java.util.Set;

public class Cell {

    private Status status;
    private int row;
    private int col;
    private ImageView iv;

    public Cell(int Col, int Row, ImageView Iv) {
        status = Status.NOT_VISITED;
        //String[] index = id.split("separator");
        row = Row;
        col = Col;
        iv = Iv;
    }

    public void setIndex(int Col, int Row) {
        //String[] index = id.split("separator");
        row = Row;
        col = Col;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public ImageView getIv() {
        return iv;
    }

    public void setIv(ImageView iv) {
        this.iv = iv;
    }
}
