package com.example.lunalunera.controller;

import com.example.lunalunera.model.Checks;

/**
 * Interface representing the controller of the HomeStage class in the view
 * @author Juan Esteban Arias
 * @version 1.0
 */
public interface iHomeController {

    /**
     * Method which uses the Checks class to verify if the string input is a legal one (this is, if it
     * doesn't have blank spaces, if it only has the spanish letters and the legal symbols and
     * if it has eight letters or more)
     * @see Checks#allChecks
     */
    void handleWordInTextField();


    /**
     * Method which handle the event of clicking the playButton button by printing a string in the text field
     * only if the boolean validWord is true
     */
    void handlePlay();

}
