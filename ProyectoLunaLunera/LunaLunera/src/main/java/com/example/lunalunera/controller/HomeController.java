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
 * Class representing the controller of the HomeStage class in the view
 * @author Juan Esteban Arias
 * @version 1.0
 */
public class HomeController {
    private Button playButton;
    private TextField secretWordTextField;
    private Label errorMessage;
    private SecretWord secretWord;
    private HomeStage homeStage;
    private boolean validWord;
    private String message;

    /**
     * Class constructor which initialize variables
     * @param secretWordTextField A text field where the user puts the secret word
     * @param playButton a button which starts the guessing
     * @param homeStage the main stage which will need to be closed
     * @param errorMessage The label for the type of error in the input of the secret word
     */
    public HomeController(TextField secretWordTextField, Button playButton,HomeStage homeStage, Label errorMessage) {
        validWord = false;
        message = "";
        this.secretWordTextField = secretWordTextField;
        this.playButton = playButton;
        this.homeStage = homeStage;
        this.errorMessage = errorMessage;
        handleWordInTextField();
        handlePlay();
    }

    /**
     * Method which uses the Checks class to verify if the string input is a legal one (this is, if it
     * doesnt have blank spaces, if it only have the spanish letters and the legal symbols and
     * if it have eight letters or more)
     * @see Checks#allChecks
     */
    private void handleWordInTextField(){
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
    private void handlePlay() {

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
