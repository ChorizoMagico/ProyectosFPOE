package com.example.lunalunera.model;

/**
 * This class checks the conditions in which the secretWord is playable or not
 * @author Juan Esteban Arias
 * @version 1.0
 */
public class Checks {
    private String secretWord;
    private String messageEightLetters;
    private String messageBlankSpace;
    private String messageSpanishAlphabet;
    private String message;
    private boolean validWord;
    /**
     *Constructor method
     */
    public Checks(){
        message = "";
        messageEightLetters = "";
        messageBlankSpace = "";
        messageSpanishAlphabet = "";
        secretWord = "";
        validWord = false;
    }

    /**
     * Set method, initialize the attribute secretWord
     * @param secretWord A String which is assigned to the Attribute "secretWord"
     */
    public void setSecretWord(String secretWord) {
        this.secretWord = secretWord;
    }
    /**
     * Checks if the word have 8 letters or more
     * @return true if the word have 8 letters or more, false if not
     */
    public boolean eightLetters(){
        return secretWord.length() >= 8;
    }


    /**
     * Checks if the word have blank spaces
     * @return true if the word have blank spaces, false if not
     */
    public boolean blankSpace(){
        return secretWord.contains(" ");
    }

    /**
     * Check if the letters of the word are a subset of the latin alphabet with the ñ, vocals with ticks
     * or the blank space
     * @return true if the letters of the word are a subset of the latin alphabet with special letters, false if not
     */
    public boolean spanishAlphabet(){
        return secretWord.matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ ]*");
    }

    /**
     * Does all the checks and assign an error to the String message
     */
    public void allChecks(){
        if(!eightLetters()) {
            messageEightLetters = "La palabra secreta debe contener 8 letras o más\n";
        }
        else{
            messageEightLetters = "";
        }

        if(!spanishAlphabet()) {
            messageSpanishAlphabet = "La palabra secreta solo puede tener carácteres en español\n";
        }
        else{
            messageSpanishAlphabet = "";
        }

        if(blankSpace()) {
            messageBlankSpace = "La palabra secreta no puede tener espacios en blanco\n";
        }
        else{
            messageBlankSpace = "";
        }

        validWord = spanishAlphabet() && !blankSpace() && eightLetters();

    }

    /**
     * Get method for the Attribute message, which is a String
     * @return the String message
     */
    public String getMessage(){
        if(validWord){
            message = "Palabra válida";
        }
        else{
            message = messageEightLetters + messageBlankSpace + messageSpanishAlphabet;
        }
        return message;
    }

    /**
     * Get method for the validWord boolean
     * @return boolean attribute validWord
     */
    public boolean getValidWord(){
        return validWord;
    }
}


