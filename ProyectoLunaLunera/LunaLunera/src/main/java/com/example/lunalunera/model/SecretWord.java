package com.example.lunalunera.model;

/**
 * Class who have the logic behind the word played in the game of the moon
 * @author Juan Esteban Arias
 * @version 1.0
 */
public class SecretWord extends SecretWordAdapter {

    /**
     * Constructor which receives a string, erases the blank spaces and fill it in the String[] attribute
     * @param word The secret word, a string
     */
    public SecretWord(String word){
        this.word=word.trim().split("");
    }

    /**
     * Class which have the logic to see which letter have been found by the user
     * It has a counter and a boolean[] which represents the letters found by the user
     * @author Juan Esteban Arias
     * @version 1.0
     */
    public class BinaryWord{
        private boolean[] binaryWord;
        private int failedAttempts;

        /**
         * Class constructor which sets the counter at 0 and the bollean[] at false
         */
        public BinaryWord(){
            binaryWord = new boolean[getWord().length];
            failedAttempts = 0;
        }

        /**
         * Set method which turns one cell from the array to true
         * @param index the cell of the array, an int
         */
        public void setBinaryWord(int index){
            binaryWord[index] = true;
        }

        /**
         * Get method which returns one boolean from the array
         * @param index the cell from the array, an int
         * @return true or false from one cell of the array
         */
        public boolean getBinaryLetter(int index){
            return binaryWord[index];
        }

        /**
         * Get method which returns the array of booleans
         * @return an array with all the guessed and unguessed letters
         */
        public boolean[] getBinaryWord(){
            return binaryWord;
        }

        /**
         * Get method which returns
         * @return int which represents the failed attempts by the user
         */
        public int getFailedAttempts(){
            return failedAttempts;
        }

        /**
         * Set method which increase the failed attempts at one
         */
        public void setFailedAttempts(){
            failedAttempts++;
        }

    }

}
