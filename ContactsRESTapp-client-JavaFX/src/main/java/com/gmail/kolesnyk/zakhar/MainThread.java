package com.gmail.kolesnyk.zakhar;


import com.gmail.kolesnyk.zakhar.controller.ContactsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainThread extends Application {
    private static ContactsController controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/MainWindow.fxml"));
        Parent parent = loader.load();
        primaryStage.setTitle("Clients");
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.show();
        controller = loader.getController();
    }
}
