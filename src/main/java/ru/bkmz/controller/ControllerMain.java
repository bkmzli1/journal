package ru.bkmz.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.bkmz.util.Items.ItemMain;
import ru.bkmz.util.StageDialog;

import java.sql.SQLException;

public class ControllerMain {
    public Button addCourse;
    public Button addInCourse;
    public Button deleteCourse;
    public Button deleteInCourse;
    public Button edit;
    public TabPane tabCourse;

    protected static final Logger logger = LogManager.getLogger();
    public static ItemMain itemMain;

    public void initialize() throws SQLException {
        itemMain = new ItemMain(addCourse, addInCourse, deleteCourse, deleteInCourse, edit, tabCourse);
        itemMain.updateTable();
        itemMain.updateTable();
        itemMain.updateTable();


    }


    public void addCourse(ActionEvent actionEvent) {
        new StageDialog("add");
    }

    public void addInCourse(ActionEvent actionEvent) {
    }

    public void deleteCourse(ActionEvent actionEvent) {
    }

    public void deleteInCourse(ActionEvent actionEvent) {
    }

    public void edit(ActionEvent actionEvent) {
    }


}
