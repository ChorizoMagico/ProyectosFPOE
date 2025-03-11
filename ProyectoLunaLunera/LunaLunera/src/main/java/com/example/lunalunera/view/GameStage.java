package com.example.lunalunera.view;

import com.example.lunalunera.controller.GameController;
import com.example.lunalunera.model.SecretWord;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Class who have the stage of the guessing of the word
 * @author Juan Esteban Arias
 * @version 1.0
 */
public class GameStage extends Stage {
    private Scene scene;
    private VBox root;
    private SecretWord secretWord;
    private TextField[] wordTextField;
    private Label counterFailedAttempts;
    private Label counterHelp;
    private Button helpButton;
    private ImageView moonImageView;
    private GameController gameController;



    /**
     * Contructor method which initializes variables
     */
    public GameStage()
    {
        root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        scene = new Scene(root, 1280, 720);
        initStage();
        showGame();

    }

    /**
     * Method which set the stage
     */
    private void initStage(){
        setTitle("Miniproyecto 1");
        setResizable(false);
        getIcons().add(
                new Image(String.valueOf(getClass().getResource("/com/example/lunalunera/favicon.png"))));
        setScene(scene);
        show();
    }

    /**
     * Method which initializes the gadgets of the visual interface
     */
    private void showGame(){
        moonImageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/com/example/lunalunera/lunainicio.png")).toExternalForm()));

        counterFailedAttempts = new Label("Intentos fallidos: 0");
        counterFailedAttempts.setTextFill(Color.CRIMSON);
        counterFailedAttempts.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 16));

        counterHelp = new Label("Ayudas disponibles: 4");
        counterHelp.setTextFill(Color.GREENYELLOW);
        counterHelp.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 16));

        helpButton = new Button("Ayuda");
        helpButton.setBackground(new Background(new BackgroundFill(
                Color.web("#00FFFF80"),
                new CornerRadii(5),
                Insets.EMPTY
        )));
        helpButton.setTextFill(Color.BLACK);
        helpButton.setFont(Font.font("Verdana", 14));
        helpButton.setCursor(Cursor.HAND);

        root.getChildren().addAll(moonImageView, counterFailedAttempts, helpButton, counterHelp);
    }

    /**
     * Method which creates the spaces for the secret word
     * @param secretWord the secret word which is the user input to the game
     */
    public void createTextFieldsSecretWord(SecretWord secretWord){
        this.secretWord = secretWord;
        wordTextField = new TextField[secretWord.getWord().length];

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        for(int i=0; i<secretWord.getWord().length; i++){
            wordTextField[i] = new TextField();
            wordTextField[i].setMaxWidth(64);
            wordTextField[i].setAlignment(Pos.CENTER);
            wordTextField[i].setFont(Font.font("Verdana", 14));
            hBox.getChildren().add(wordTextField[i]);
        }
        root.getChildren().add(hBox);

        initController();
    }

    /**
     * Method which initialize the gameController
     */
    public void initController(){
        gameController = new GameController(moonImageView, secretWord, wordTextField, counterFailedAttempts, helpButton, counterHelp);
    }
}
