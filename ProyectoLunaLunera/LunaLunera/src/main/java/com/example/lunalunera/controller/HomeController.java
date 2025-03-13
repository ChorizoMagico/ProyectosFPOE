package com.example.lunalunera.controller;

import com.example.lunalunera.view.HomeStage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


/**
 * Class representing the controller of the HomeStage class in the view
 * @author Juan Esteban Arias
 * @version 1.0
 */
public class HomeController extends HomeAdapter{

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

}
