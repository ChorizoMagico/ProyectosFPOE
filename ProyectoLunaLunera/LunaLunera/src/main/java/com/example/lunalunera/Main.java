package com.example.lunalunera;

import com.example.lunalunera.view.HomeStage;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main class of the project, which extends the abstract superclass named Application
 * and launches the javafx interface
 *
 * @author Juan Esteban Arias
 * @version 1.0
 */
public class Main extends Application {
    /**
     * Main method which receives an entry from the command line and executes the main
     * method from javafx
     * @see Application#launch(String...)
     * @param args a command line(string)
     */
    public static void main(String[] args) {launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        new HomeStage();
    }
}
