package com.example.lunalunera.controller;

import com.example.lunalunera.model.ChecksGameController;
import com.example.lunalunera.model.SecretWord;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * Adapter class which implements the method in the interface iGameController
 * @author Juan Esteban Arias
 * @version 1.0
 */
public class GameAdapter implements IGameController {

    /**
     * An instance of the class with the conditions in which the letter inserted is correct or not
     * and the utility method required which doesn't are in the GameController class
     */
    protected ChecksGameController checksGameController;
    /**
     * The class with the secret word being guessed and the logic behind it
     */
    protected SecretWord secretWord;
    /**
     * An instance of the Class which have: 1) the logic to see which letter have been found by the user,
     * 2) a counter, 3) and a boolean[] which represents the letters found by the user
     */
    protected SecretWord.BinaryWord binaryWord;
    /**
     * The text fields for the letter being guessed
     */
    protected TextField[] wordTextField;
    /**
     * A label with the number of failed attempts
     */
    protected Label counterFailedAttempts;
    /**
     * A button for the help functionality
     */
    protected Button helpButton;
    /**
     * A label for the number of helps to remain
     */
    protected Label counterHelp;
    /**
     * The image of the moon, which fades slidely with each wrong try
     */
    protected ImageView moonImageView;
    /**
     * The moons which represents the remaining tries
     */
    protected ImageView[] moonLives;



    /**
     * Method which sees if a text field is selected.
     * If yes, then it switches from the sleeping image to the first image of the moon game.
     */
    @Override
    public void handleFocusField(int index) {
        wordTextField[index].focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue && binaryWord.getFailedAttempts() == 0) {
                    checksGameController.setMoonPhase();
                }
            }
        });
    }

    /**
     * Method which calls the helpWithTheSecretWord() method when the helpButton is clicked and sums one
     * to the counter of helps used
     * If 4 helps are used, the button is disabled
     * @see ChecksGameController#helpWithTheSecretWord()
     */
    @Override
    public void handleHelpButton() {
        helpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                checksGameController.helpWithTheSecretWord();
                if(checksGameController.getIntCounterHelp() == 4){
                    helpButton.setDisable(true);
                }
                counterHelp.setText("Ayudas disponibles: " + String.valueOf(4-checksGameController.getIntCounterHelp()));
            }
        });
    }


    /**
     * Method which add a listener to one textField of the array and see if the new letter in the array
     * is a subset of the regular expression, which is, if the letter is a legal one. If not, it
     * returns to the previous letter
     * @param index an int which specifies the textField of the array
     */
    @Override
    public void handleTextFields(int index){
        wordTextField[index].textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ]?")){
                    wordTextField[index].setText(oldValue);
                }
            }
        });
    }

    /**
     * Method which add a listener to one textField of the array and see if the new letter
     * is one of the secret word by using the isACorrectAnswer() method.
     * If yes, it disables the text field. If not, it puts the textField in red and puts that textField
     * as a failed attempt in a boolean[], forcing to erase the letter and try again.
     * It also counts and shows the failed attempts.
     * @param index the textField which is being verified
     * @see ChecksGameController#isACorrectAnswer(String, int)
     */
    @Override
    public void handleCorrectAnswers(int index){
        wordTextField[index].textProperty().addListener( new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (!newValue.isEmpty()) {

                    if (checksGameController.isACorrectAnswer(newValue, index)) {
                        wordTextField[index].setBorder(new Border(new BorderStroke(
                                Color.GREEN,
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(5),
                                new BorderWidths(2)
                        )));
                        wordTextField[index].setDisable(true);
                        checksGameController.getFailedAttemptCounted()[index] = false;
                    } else {

                        if(!checksGameController.getFailedAttemptCounted()[index]){
                            checksGameController.changeLives();
                            wordTextField[index].setBorder(new Border(new BorderStroke(
                                    Color.RED,
                                    BorderStrokeStyle.SOLID,
                                    new CornerRadii(5),
                                    new BorderWidths(2)
                            )));
                            binaryWord.setFailedAttempts();

                            counterFailedAttempts.setText("Intentos fallidos: " + binaryWord.getFailedAttempts());
                            checksGameController.getFailedAttemptCounted()[index] = true;
                        }

                    }
                    checksGameController.setMoonPhase();
                    if(checksGameController.isGameEnded() && !checksGameController.getIsGameEndedAlready()){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Fin del juego");
                        if(binaryWord.getFailedAttempts() >= 8){
                            alert.setHeaderText("Perdiste :(");
                            alert.setContentText("lero lero");
                        }
                        else{
                            alert.setHeaderText("Ganaste :)");
                            alert.setContentText("Sos muy inteligente, sigue así");
                        }
                        checksGameController.setIsGameEndedAlready(true);
                        alert.showAndWait();
                    }
                }
                else{
                    checksGameController.getFailedAttemptCounted()[index] = false;
                    wordTextField[index].setBorder(Border.EMPTY);
                }

            }
        });

    }

    /**
     * It runs the handleTextFields() and handleCorrectAnswers() methods in all the textField array
     * @see #handleTextFields(int)
     * @see #handleCorrectAnswers(int)
     */
    @Override
    public void handleAllTextFields(){
        for(int i=0; i<secretWord.getWord().length; i++){
            handleFocusField(i);
            handleTextFields(i);
            handleCorrectAnswers(i);
        }
    }
}
