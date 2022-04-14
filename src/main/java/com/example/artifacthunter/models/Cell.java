package com.example.artifacthunter.models;

import javafx.scene.image.Image;

//import java.util.Set;

/**
 * Single cell class
 */
public class Cell {
    /**
     *
     */
    private Status status;
    /**
     *
     */
    private int row;
    /**
     *
     */
    private int col;
    /**
     *
     */
    private Image image;


    /**
     *
     * @param Col
     * @param Row
     * @param image
     */
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

    /**
     *
     * @return
     */
    public Status getStatus() {
        return status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(Status status) {
        this.status = status;
        image = switch (status) {
            case VISITED -> new Image(getClass().getResourceAsStream("/Sprites/Visited Cell.png"));
            case PLAYER_VISITED -> new Image(getClass().getResourceAsStream("/Sprites/Player.png"));
            case NOT_VISITED_MARKED -> new Image(getClass().getResourceAsStream("/Sprites/Marked Cell.png"));
            default -> new Image(getClass().getResourceAsStream("/Sprites/Default Cell.png"));
        };
    }

    /**
     *
     * @return
     */
    public int getRow() {
        return row;
    }

    /**
     *
     * @param row
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     *
     * @return
     */
    public int getCol() {
        return col;
    }

    /**
     *
     * @param col
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     *
     * @return
     */
    public Image getImage() {
        return image;
    }

    /**
     *
     * @param image
     */
    public void setImage(Image image) {
        this.image = image;
    }
}
