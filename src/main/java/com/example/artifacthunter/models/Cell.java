package com.example.artifacthunter.models;

import java.util.Set;

public class Cell {

    private Set<Status> status;
    private char row;
    private char col;

    public Cell(String id) {
        status = Set.of(Status.NOT_VISITED);
        //String[] index = id.split("a");
        row = id.charAt(1);
        col = id.charAt(0);
    }

    public Set<Status> getStatus() {
        return status;
    }

    public void setStatus(Set<Status> status) {
        this.status = status;
    }

    public int getRow() {
        return row;
    }

    public void setRow(char row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(char col) {
        this.col = col;
    }
}
