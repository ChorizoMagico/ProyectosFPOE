package com.example.lunalunera.controller;

import com.example.lunalunera.model.ChecksGameController;

/**
 * Interface representing the controller of the GameStage class in the view
 * @author Juan Esteban Arias
 * @version 1.0
 */
public interface IGameController {

    /**
     * Method which sees if a text field is selected.
     * If yes, then it switches from the sleeping image to the first image of the moon game.
     * @param index The specific textField being handled
     */
    void handleFocusField(int index);

    /**
     * Method which calls the helpWithTheSecretWord() method when the helpButton is clicked and sums one
     * to the counter of helps used
     * If 4 helps are used, the button is disabled
     * @see ChecksGameController#helpWithTheSecretWord()
     */
    void handleHelpButton();


    /**
     * Method which add a listener to one textField of the array and see if the new letter in the array
     * is a subset of the regular expression, which is, if the letter is a legal one. If not, it
     * returns to the previous letter
     * @param index an int which specifies the textField of the array
     */
    void handleTextFields(int index);

    /**
     * Method which add a listener to one textField of the array and see if the new letter
     * is one of the secret word by using the isACorrectAnswer() method.
     * If yes, it disables the text field. If not, it puts the textField in red and puts that textField
     * as a failed attempt in a boolean[], forcing to erase the letter and try again.
     * It also counts and shows the failed attempts.
     * @param index the textField which is being verified
     * @see ChecksGameController#isACorrectAnswer(String, int)
     */
    void handleCorrectAnswers(int index);

    /**
     * It runs the handleTextFields() and handleCorrectAnswers() methods in all the textField array
     * @see #handleTextFields(int)
     * @see #handleCorrectAnswers(int)
     */
    void handleAllTextFields();
}
