package me.dkim19375.javafxtest;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LoginController {
    @FXML
    private TextField passText;
    @FXML
    private Label correctLabel;
    @FXML
    private Label incorrectLabel;
    @FXML
    private Button submitButton;
    @FXML
    private ProgressBar progressBar;
    private boolean done = false;

    @FXML
    private void initialize() {
        passText.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                submitted();
            }
        });
        progressBar.setVisible(false);
        correctLabel.setVisible(false);
        incorrectLabel.setVisible(false);
        submitButton.setOnAction((event) -> submitted());
    }

    private void submitted() {
        if (passText.getText().equals("SomePassword")) {
            if (done) {
                return;
            }
            done = true;
            incorrectLabel.setVisible(false);
            correctLabel.setVisible(true);
            progressBar.setProgress(0.0);
            progressBar.setVisible(true);
            final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
            service.scheduleAtFixedRate(() -> {
                if (progressBar.getProgress() >= 1.0) {
                    return;
                }

                progressBar.setProgress(progressBar.getProgress() + 0.00025);
            }, 1, 1, TimeUnit.MILLISECONDS);
            service.schedule(() -> {
                try {
                    final Parent welcome = FXMLLoader.load(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource("welcome.fxml"))); // Throw exception if null
                    final Parent ymlViewer = FXMLLoader.load(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource("ymlviewer.fxml"))); // Throw exception if null
                    JavaFXTest.getPrimaryStage().getScene().setRoot(welcome);
                    service.schedule(() -> JavaFXTest.getPrimaryStage().getScene().setRoot(ymlViewer), 5, TimeUnit.SECONDS);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }, 4, TimeUnit.SECONDS);
            return;
        }
        correctLabel.setVisible(false);
        incorrectLabel.setVisible(true);
    }
}
