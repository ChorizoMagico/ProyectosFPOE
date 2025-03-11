package com.example.lunalunera.controller;

import com.example.lunalunera.model.SecretWord;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * Class representing the controller of the GameStage class in the view
 * @author Juan Esteban Arias
 * @version 1.0
 */
public class GameController {
    private SecretWord secretWord;
    private SecretWord.BinaryWord binaryWord;
    private TextField[] wordTextField;
    private Label counterFailedAttempts;
    private Button helpButton;
    private Label counterHelp;
    private ImageView moonImageView;
    private int intCounterHelp;
    private boolean[] failedAttemptCounted;



    /**
     * Constructor method
     * @param secretWord The secret word which is the input of the user
     * @param wordTextField The array of text fields for the letter of the secret word
     * @param moonImageView The image of the status of the moon
     * @param helpButton The button with the help functionality
     * @param counterHelp The label for the counter of the helps used
     * @param counterFailedAttempts The label for the counter of the failed attempts at guessing the word
     */
    public GameController(ImageView moonImageView, SecretWord secretWord, TextField[] wordTextField, Label counterFailedAttempts, Button helpButton, Label counterHelp) {
        this.moonImageView = moonImageView;
        this.secretWord = secretWord;
        binaryWord = secretWord.new BinaryWord();
        this.wordTextField = wordTextField;
        this.counterFailedAttempts = counterFailedAttempts;
        this.helpButton = helpButton;
        this.counterHelp = counterHelp;
        intCounterHelp = 0;
        this.failedAttemptCounted = new boolean[wordTextField.length];
        handleAllTextFields();
        handleHelpButton();
    }

    /**
     * Method which sets the moon image depending on the number of failed attempts at guessing the secret word
     * @see SecretWord.BinaryWord#getFailedAttempts()
     */
    public void setMoonPhase(){
        if(binaryWord.getFailedAttempts() == 1){
            moonImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/com/example/lunalunera/lunanueva.png")).toExternalForm()));
        } else if (binaryWord.getFailedAttempts() == 2) {
            moonImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/com/example/lunalunera/lunacreciente.png")).toExternalForm()));
        } else if (binaryWord.getFailedAttempts() == 3) {
            moonImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/com/example/lunalunera/cuartocreciente.png")).toExternalForm()));
        } else if (binaryWord.getFailedAttempts() == 4) {
            moonImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/com/example/lunalunera/lunagibosacreciente.png")).toExternalForm()));
        } else if (binaryWord.getFailedAttempts() == 5) {
            moonImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/com/example/lunalunera/lunallena.png")).toExternalForm()));
        } else if (binaryWord.getFailedAttempts() == 6) {
            moonImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/com/example/lunalunera/lunagibosamenguante.png")).toExternalForm()));
        } else if (binaryWord.getFailedAttempts() == 7) {
            moonImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/com/example/lunalunera/cuartomenguante.png")).toExternalForm()));
        } else if (binaryWord.getFailedAttempts() == 8) {
            moonImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/com/example/lunalunera/lunamenguante.png")).toExternalForm()));
        } else if (binaryWord.getFailedAttempts() >= 9) {
            moonImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/com/example/lunalunera/lunanueva.png")).toExternalForm()));
        }
    }

    /**
     * Method which calls the helpWithTheSecretWord() method when the helpButton is clicked and sums one
     * to the counter of helps used
     * If 4 helps are used, the button is disabled
     * @see #helpWithTheSecretWord()
     */
    public void handleHelpButton() {
        helpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                helpWithTheSecretWord();
                if(intCounterHelp == 4){
                    helpButton.setDisable(true);
                }
                counterHelp.setText("Ayudas disponibles: " + String.valueOf(4-intCounterHelp));
            }
        });
    }

    /**
     * Method which search for the first space unaswered and answered it with the correct letter
     */
    public void helpWithTheSecretWord() {
        for(int i = 0; i < secretWord.getWord().length; i++){

            if(!binaryWord.getBinaryLetter(i)){

                wordTextField[i].setText(secretWord.getWord()[i]);
                wordTextField[i].setStyle("-fx-border-color: green; -fx-border-width: 2px; -fx-border-radius: 5px;");
                wordTextField[i].setDisable(true);
                failedAttemptCounted[i] = false;
                binaryWord.setBinaryWord(i);
                intCounterHelp++;
                break;
            }
        }
    }


    /**
     * Method which add a listener to one textField of the array and see if the new letter in the array
     * is a subset of the regular expression, which is, if the letter is a legal one. If not, it
     * returns to the previous letter
     * @param index an int which specifies the textField of the array
     */
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
     * @see #isACorrectAnswer(String, int)
     */
    public void handleCorrectAnswers(int index){
        wordTextField[index].textProperty().addListener( new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (!newValue.isEmpty()) {

                    if (isACorrectAnswer(newValue, index)) {
                        wordTextField[index].setStyle("-fx-border-color: green; -fx-border-width: 2px; -fx-border-radius: 5px;");
                        wordTextField[index].setDisable(true);
                        failedAttemptCounted[index] = false;
                    } else {

                        if(!failedAttemptCounted[index]){
                            wordTextField[index].setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 5px;");
                            binaryWord.setFailedAttempts();

                            counterFailedAttempts.setText("Intentos fallidos: " + binaryWord.getFailedAttempts());
                            failedAttemptCounted[index] = true;
                        }

                    }
                    setMoonPhase();
                }
                else{
                    failedAttemptCounted[index] = false;
                }

            }
        });

    }

    /**
     * Method which verifies is a letter is one of the letters of the secret word
     * and if that letters is already found
     * @param answer letter being verified, a string
     * @param index The position of the letter at the word which should be in the text field
     * @return it returns true if the letter is in the secret word, false if not or is already found
     */
    public boolean isACorrectAnswer(String answer, int index){
        boolean correctAnswer = false;

        if(answer.equals(secretWord.getWord()[index]) & !binaryWord.getBinaryLetter(index)) {
            binaryWord.setBinaryWord(index);
            correctAnswer = true;
        }
        return correctAnswer;
    }

    /**
     * It runs the handleTextFields() and handleCorrectAnswers() methods in all the textField array
     * @see #handleTextFields(int)
     * @see #handleCorrectAnswers(int)
     */
    public void handleAllTextFields(){
        for(int i=0; i<secretWord.getWord().length; i++){
            handleTextFields(i);
            handleCorrectAnswers(i);
        }
    }


}
