package com.example.artifacthunter.models;

import java.util.List;

public class Cell {
    private List<Status> status;

    public List<Status> getStatus() {
        return status;
    }

    public void setStatus(List<Status> status) {
        this.status = status;
    }

    private String id;
}
