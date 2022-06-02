package com.example.artifacthunter.test;

import com.example.artifacthunter.MainController;
import com.example.artifacthunter.models.Cell;
import com.example.artifacthunter.models.Status;
import javafx.scene.image.Image;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class MainControllerTest {
    @Test
    public void correctnessThreatsCounter() {
        int colCount = 10;
        int rowCount = 10;
        int countOfTreats = 15;
        List<List<Cell>> field = new ArrayList<>();
        for (int i = 0; i < colCount + 2; i++) {
            field.add(new ArrayList<>());
            for (int j = 0; j < rowCount + 2; j++) {
                field.get(i).add(new Cell(i, j, new Image(getClass().getResourceAsStream("/Sprites/Default Cell.png"))));
            }
        }
        if (countOfTreats > colCount * rowCount / 2) {
            Assert.assertTrue(false);
        }
        else {
            for (int i = 0; i < colCount + 2; i++)
                for (int j = 0; j < rowCount + 2; j++) {
                    field.get(i).get(j).setStatus(Status.NOT_VISITED);
                    field.get(i).get(j).setStatus(Status.NOT_VISITED);
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
        }
        for (int i = 0; i < colCount + 2; i++)
            for (int j = 0; j < rowCount + 2; j++)
                if (field.get(i).get(j).getThreats() > 8 || field.get(i).get(j).getThreats() < 0)
                    Assert.assertTrue(false);
    }

    @Test
    public void correctnessCountOfThreatCells() {
        int colCount = 10;
        int rowCount = 10;
        int countOfTreats = 15;
        List<List<Cell>> field = new ArrayList<>();
        for (int i = 0; i < colCount + 2; i++) {
            field.add(new ArrayList<>());
            for (int j = 0; j < rowCount + 2; j++) {
                field.get(i).add(new Cell(i, j, new Image(getClass().getResourceAsStream("/Sprites/Default Cell.png"))));
            }
        }
        if (countOfTreats > colCount * rowCount / 2) {
            Assert.assertTrue(false);
        }
        else {
            for (int i = 0; i < colCount + 2; i++)
                for (int j = 0; j < rowCount + 2; j++) {
                    field.get(i).get(j).setStatus(Status.NOT_VISITED);
                    field.get(i).get(j).setStatus(Status.NOT_VISITED);
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
        }
        int treats = 0;
        for (int i = 0; i < colCount + 2; i++)
            for (int j = 0; j < rowCount + 2; j++)
                if (field.get(i).get(j).getStatus() == Status.DANGEROUS_NOT_VISITED)
                    treats++;
        Assert.assertEquals(countOfTreats, treats);
    }
}