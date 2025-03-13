package com.example.lunalunera.controller;

import com.example.lunalunera.model.Checks;
import com.example.lunalunera.model.SecretWord;
import com.example.lunalunera.view.GameStage;
import com.example.lunalunera.view.HomeStage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Adapter class which implements the method in the interface iHomeController
 * @author Juan Esteban Arias
 * @version 1.0
 */

public abstract class HomeAdapter implements iHomeController {
    /**
     * A button which initiates the guessing of the word
     */
    protected Button playButton;
    /**
     * The text field for the input of the secret word
     */
    protected TextField secretWordTextField;
    /**
     * A label explaining why the secret word is not a valid one
     */
    protected Label errorMessage;
    /**
     * The secret word in which the game turns around
     */
    protected SecretWord secretWord;
    /**
     * The main stage in which the visual gadgets are
     */
    protected HomeStage homeStage;
    /**
     * A boolean which indicates if the input (secret word) is a valid one
     */
    protected boolean validWord;
    /**
     * The message with the error produced by a no valid input
     */
    protected String message;

    /**
     * Method which uses the Checks class to verify if the string input is a legal one (this is, if it
     * doesn't have blank spaces, if it only has the spanish letters and the legal symbols and
     * if it has eight letters or more)
     * @see Checks#allChecks
     */
    @Override
    public void handleWordInTextField(){
        Checks check = new Checks();
        secretWordTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                check.setSecretWord(secretWordTextField.getText());
                check.allChecks();
                message = check.getMessage();
                errorMessage.setText(message);
                validWord = check.getValidWord();
            }
        });
    }

    /**
     * Method which handle the event of clicking the playButton button by printing a string in the text field
     * only if the boolean validWord is true
     */
    @Override
    public void handlePlay() {

        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!validWord){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Palabra no válida");
                    alert.setContentText(message);
                    alert.showAndWait();
                }
                else{
                    secretWord = new SecretWord(secretWordTextField.getText());
                    new GameStage().createTextFieldsSecretWord(secretWord);
                    homeStage.close();
                }
            }
        });
    }
}
