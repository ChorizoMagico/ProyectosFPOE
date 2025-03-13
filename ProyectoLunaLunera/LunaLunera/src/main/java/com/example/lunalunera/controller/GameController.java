package com.example.lunalunera.controller;

import com.example.lunalunera.model.ChecksGameController;
import com.example.lunalunera.model.SecretWord;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * Class representing the controller of the GameStage class in the view
 * @author Juan Esteban Arias
 * @version 1.0
 */
public class GameController extends GameAdapter{
    /**
     * Constructor method
     * @param moonLives Array of 8 image views which represents the tries of the user
     * @param secretWord The secret word which is the input of the user
     * @param wordTextField The array of text fields for the letter of the secret word
     * @param moonImageView The image of the status of the moon
     * @param helpButton The button with the help functionality
     * @param counterHelp The label for the counter of the helps used
     * @param counterFailedAttempts The label for the counter of the failed attempts at guessing the word
     */
    public GameController(ImageView moonImageView, ImageView[] moonLives, SecretWord secretWord, TextField[] wordTextField, Label counterFailedAttempts, Button helpButton, Label counterHelp) {
        this.moonImageView = moonImageView;
        this.moonLives = moonLives;
        this.secretWord = secretWord;
        binaryWord = secretWord.new BinaryWord();
        this.wordTextField = wordTextField;
        this.counterFailedAttempts = counterFailedAttempts;
        this.helpButton = helpButton;
        this.counterHelp = counterHelp;

        checksGameController = new ChecksGameController(secretWord, binaryWord, moonLives, moonImageView, wordTextField);
        handleAllTextFields();
        handleHelpButton();
    }

}
