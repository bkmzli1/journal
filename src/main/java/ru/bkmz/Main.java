package ru.bkmz;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.bkmz.sql.SQL;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Objects;

public class Main extends Application {
    protected static final Logger logger = LogManager.getLogger();
    public static Stage stage;

    public static String FileSeve = System.getenv("APPDATA") + "\\.journal",
            SQLFile = FileSeve + "\\journal.sqlite";
    public static SQL sql;

    public static void main(String[] args) throws IOException {


        launch(args);
    }

    @Override
    public void init() throws Exception {
        //проверка файлов
        File file = new File(FileSeve);
        if (!file.exists()) {
            file.mkdir();

        }

        boolean create = false;
        file = new File(SQLFile);
        if (!file.exists()) {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            create = true;
        }

        //работа с sql
        sql = new SQL(SQLFile);
        if (create) {
            sql.initStart();
        }

    }

    @Override
    public void start(Stage stage) {
        logger.info("start loader FXML");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/main.fxml")));
        try {
            loader.load();
        } catch (IOException e) {
            logger.warn("load fxml", e);
        }

        Parent root = loader.getRoot();
        logger.info("stop loader FXML");
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("css/main.css")).toExternalForm());
        stage.setScene(scene);
        stage.setTitle("name");
        InputStream inputStream = ClassLoader.class.getResourceAsStream("/img/icon.png");
        try {
            Image image = new Image(inputStream);
            stage.getIcons().add(image);
        } catch (NullPointerException e) {
            logger.warn("img null");
        }
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {

                System.exit(0);
            }
        });

        stage.show();

        this.stage = stage;
    }
}
