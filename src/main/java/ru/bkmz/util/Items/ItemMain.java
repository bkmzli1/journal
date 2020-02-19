package ru.bkmz.util.Items;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static ru.bkmz.Main.sql;

public class ItemMain {
    Button addCourse;
    Button addInCourse;
    Button deleteCourse;
    Button deleteInCourse;
    Button edit;
    TabPane tabCourse;

    protected static final Logger logger = LogManager.getLogger();

    public ItemMain(Button addCourse, Button addInCourse, Button deleteCourse, Button deleteInCourse, Button edit, TabPane tabCourse) {
        this.addCourse = addCourse;
        this.addInCourse = addInCourse;
        this.deleteCourse = deleteCourse;
        this.deleteInCourse = deleteInCourse;
        this.edit = edit;
        this.tabCourse = tabCourse;
    }

    public Button getAddCourse() {
        return addCourse;
    }

    public void setAddCourse(Button addCourse) {
        this.addCourse = addCourse;
    }

    public Button getAddInCourse() {
        return addInCourse;
    }

    public void setAddInCourse(Button addInCourse) {
        this.addInCourse = addInCourse;
    }

    public Button getDeleteCourse() {
        return deleteCourse;
    }

    public void setDeleteCourse(Button deleteCourse) {
        this.deleteCourse = deleteCourse;
    }

    public Button getDeleteInCourse() {
        return deleteInCourse;
    }

    public void setDeleteInCourse(Button deleteInCourse) {
        this.deleteInCourse = deleteInCourse;
    }

    public Button getEdit() {
        return edit;
    }

    public void setEdit(Button edit) {
        this.edit = edit;
    }

    public TabPane getTabCourse() {
        return tabCourse;
    }

    public void setTabCourse(TabPane tabCourse) {
        this.tabCourse = tabCourse;
    }

    public void updateTable() {
        logger.info("Up ");
        try {
            tabCourse.getTabs().clear();
            Statement courseS = sql.getConn().createStatement();
            ResultSet CourseR = courseS.executeQuery("SELECT * FROM ListName");
            while (CourseR.next()) {
                //группа
                Tab tab = new Tab(CourseR.getString("name"));

                tabCourse.getTabs().add(tab);
                //предмет
                Statement fartS = sql.getConn().createStatement();
                ResultSet fartR = fartS.executeQuery("SELECT * FROM Fart");
                TabPane fartTP = new TabPane();
                fartTP.setSide(Side.LEFT);
                VBox.setVgrow(fartTP, Priority.ALWAYS);
                HBox.setHgrow(fartTP, Priority.ALWAYS);
                tab.setContent(fartTP);
                while (fartR.next()) {
                    Tab fartT = new Tab(fartR.getString("name"));
                    fartT.setClosable(false);
                    fartTP.getTabs().add(fartT);
                    //таблица
                    TableView fartTV = new TableView();
                    VBox.setVgrow(fartTV, Priority.ALWAYS);
                    HBox.setHgrow(fartTV, Priority.ALWAYS);

                    TableColumn<Table, String> number = new TableColumn<Table, String>("1");
                    number.setCellValueFactory(new PropertyValueFactory<>("number"));
                    TableColumn<Table, String> kol = new TableColumn<Table, String>("2");
                    kol.setCellValueFactory(new PropertyValueFactory<>("kol"));
                    TableColumn<Table, String> price = new TableColumn<Table, String>("3");
                    price.setCellValueFactory(new PropertyValueFactory<>("price"));
                    TableColumn<Table, String> name = new TableColumn<Table, String>("4");
                    name.setCellValueFactory(new PropertyValueFactory<>("name"));
                    TableColumn<Table, String> customer = new TableColumn<Table, String>("5");
                    customer.setCellValueFactory(new PropertyValueFactory<>("customer"));

                    fartTV.getColumns().addAll(number, kol, price, name, customer);
                    fartT.setContent(fartTV);
                }
            }
            courseS.close();
            CourseR.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
