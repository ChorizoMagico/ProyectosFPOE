package com.example.lunalunera.model;

/**
 * Adapter class for the Interface class of SecretWord
 * @author Juan Esteban Arias
 * @version 1.0
 */
public abstract class SecretWordAdapter implements ISecretWord {
    /**
     * Array of characters representing the secret word.
     * It is initialized in the constructor of the subclass.
     */
    protected String[] word;


    /**
     * Get method which returns the String[] attribute "word"
     * @return string[] attribute "word"
     */
    @Override
    public String[] getWord(){
        return word;
    }

}
