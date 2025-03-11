/**
 * Module for the LunaLunera project.
 * This module defines the necessary requirements and exports
 * for the application's functionality, including the use of JavaFX
 * for the graphical user interface.
 *
 * @author Juan Esteban Arias
 * @version 1.0
 */
module com.example.lunalunera {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lunalunera to javafx.fxml;
    exports com.example.lunalunera;
}