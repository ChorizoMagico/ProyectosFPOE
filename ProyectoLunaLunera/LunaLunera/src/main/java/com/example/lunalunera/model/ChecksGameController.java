package com.example.lunalunera.model;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;
/**
 * This class checks the conditions in which the letter inserted is correct or not
 * Besides that, it also has utility method which doesn't are in the GameController class
 * @author Juan Esteban Arias
 * @version 1.0
 */
public class ChecksGameController {
    private SecretWord secretWord;
    private SecretWord.BinaryWord binaryWord;
    private ImageView[] moonLives;
    private ImageView moonImageView;
    private TextField[] wordTextField;
    private int intCounterHelp;
    private boolean[] failedAttemptCounted;
    private boolean gameEnded;
    private boolean isGameEndedAlready;

    /**
     * Method constructor
     * @param secretWord The secret word which is the input of the user
     * @param binaryWord An array of booleans representing the guessed letters of the secret word
     * @param moonLives An array of 8 moons, representing the remaining tries of the user
     * @param moonImageView The image of the moon, which fades a little every wrong try
     * @param wordTextField An array of text field in which the letters are guessed
     */
    public ChecksGameController(SecretWord secretWord, SecretWord.BinaryWord binaryWord, ImageView[] moonLives, ImageView moonImageView, TextField[] wordTextField) {
        this.secretWord = secretWord;
        this.binaryWord = binaryWord;
        this.moonLives = moonLives;
        this.moonImageView = moonImageView;
        this.wordTextField = wordTextField;
        intCounterHelp = 0;
        failedAttemptCounted = new boolean[wordTextField.length];
        gameEnded = false;
        isGameEndedAlready = false;
    }

    /**
     * A get method for the intCounterHelp attribute
     * @return an int which says the helps used by the user
     */
    public int getIntCounterHelp() {
        return intCounterHelp;
    }

    /**
     * A get method for the failedAttemptCounted attribute
     * @return an array of booleans for the fields with wrong letters
     */
    public boolean[] getFailedAttemptCounted() {
        return failedAttemptCounted;
    }

    /**
     * A set method which assigns a boolean to the attribute which represents the state of the game,
     * so the alert of victory/lose can appear
     * @param trueOrFalse a boolean which indicates if the game ended or not
     */
    public void setIsGameEndedAlready(boolean trueOrFalse) {
        isGameEndedAlready = trueOrFalse;
    }

    /**
     * A get method whitch returns a boolean for the attribute which represents the state of the game,
     * so the alert of victory/lose can appear
     * @return a boolean which indicates if the game ended or not
     */
    public boolean getIsGameEndedAlready() {
        return isGameEndedAlready;
    }

    /**
     * Method which sets an error image in the display of the available tries
     */
    public void changeLives() {
        if(binaryWord.getFailedAttempts() < 8){
            moonLives[binaryWord.getFailedAttempts()].setImage( new Image(Objects.requireNonNull(getClass().getResource("/com/example/lunalunera/wrong.png")).toExternalForm()));
        }

    }

    /**
     * Method which verifies if all the letters are guessed or if the failed attempts
     * are equals or more than 9, which means the user lost the game
     * @return a boolean which says if the game has ended or not
     */
    public boolean isGameEnded() {
        for(int i = 0; i < secretWord.getWord().length; i++) {
            if(!binaryWord.getBinaryLetter(i)){
                gameEnded = false;
                break;
            }
            if(i == secretWord.getWord().length-1){
                gameEnded = true;
            }
        }

        if(binaryWord.getFailedAttempts() >= 8){
            gameEnded = true;
        }
        return gameEnded;
    }

    /**
     * Method which sets the moon image depending on the number of failed attempts at guessing the secret word
     * @see SecretWord.BinaryWord#getFailedAttempts()
     */
    public void setMoonPhase(){
        if(binaryWord.getFailedAttempts() == 0){
            moonImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/com/example/lunalunera/lunanueva.png")).toExternalForm()));
        } else if (binaryWord.getFailedAttempts() == 1) {
            moonImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/com/example/lunalunera/lunacreciente.png")).toExternalForm()));
        } else if (binaryWord.getFailedAttempts() == 2) {
            moonImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/com/example/lunalunera/cuartocreciente.png")).toExternalForm()));
        } else if (binaryWord.getFailedAttempts() == 3) {
            moonImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/com/example/lunalunera/lunagibosacreciente.png")).toExternalForm()));
        } else if (binaryWord.getFailedAttempts() == 4) {
            moonImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/com/example/lunalunera/lunallena.png")).toExternalForm()));
        } else if (binaryWord.getFailedAttempts() == 5) {
            moonImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/com/example/lunalunera/lunagibosamenguante.png")).toExternalForm()));
        } else if (binaryWord.getFailedAttempts() == 6) {
            moonImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/com/example/lunalunera/cuartomenguante.png")).toExternalForm()));
        } else if (binaryWord.getFailedAttempts() == 7) {
            moonImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/com/example/lunalunera/lunamenguante.png")).toExternalForm()));
        } else if (binaryWord.getFailedAttempts() >= 8) {
            moonImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/com/example/lunalunera/lunanueva.png")).toExternalForm()));
        }
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
}
