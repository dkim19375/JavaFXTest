package me.dkim19375.javafxtest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Objects;

public class JavaFXTest extends Application {
    private static Stage primaryStage;

    protected static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        JavaFXTest.primaryStage = primaryStage;
        final Parent login = FXMLLoader.load(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource("login.fxml"))); // Throw exception if null
        primaryStage.setTitle("Testing App");
        primaryStage.setScene(new Scene(login, 1280, 720));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }
}
