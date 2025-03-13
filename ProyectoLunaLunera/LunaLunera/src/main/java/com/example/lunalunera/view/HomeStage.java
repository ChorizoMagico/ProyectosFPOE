package com.example.lunalunera.view;

import com.example.lunalunera.controller.HomeController;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Main class from the view, which extends the Stage class
 * @author Juan Esteban Arias
 * @version 1.0
 */
public class HomeStage extends Stage {
    private Scene scene;
    private VBox root;
    private TextField secretWordTextField;
    private Button playButton;
    private Label errorMessage;
    private ImageView logoImageView;
    private HomeController homeController;

    /**
     * Constructor method which initializes variables and executes the initStage() method
     * @see #initStage()
     */
    public HomeStage() {
        root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        scene = new Scene(root, 1280, 720);
        initStage();
        showHome();
        initController();
    }

    /**
     * Void method which sets the stage
     */
    private void initStage() {
        setTitle("Eclipse Lunar");
        setResizable(false);
        getIcons().add(
                new Image(String.valueOf(getClass().getResource("/com/example/lunalunera/favicon.png"))));
        setScene(scene);
        show();
    }

    /**
     * Void method which creates the visual elements of the layout in the scene
     */
    private void showHome() {
        Label titleLabel = new Label("Digite la palabra secreta: ");
        titleLabel.setTextFill(Color.CRIMSON);
        titleLabel.setFont(Font.font("Dancing Script", FontWeight.NORMAL, FontPosture.ITALIC, 20));

        logoImageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/com/example/lunalunera/logo.png")).toExternalForm()));
        logoImageView.setFitWidth(1000);
        logoImageView.setFitHeight(500);
        Rectangle clip = new Rectangle(logoImageView.getFitWidth(), logoImageView.getFitHeight());
        clip.setArcWidth(50);
        clip.setArcHeight(50);

        logoImageView.setClip(clip);

        secretWordTextField = new TextField();
        secretWordTextField.setMaxWidth(200);
        secretWordTextField.setAlignment(Pos.CENTER);
        secretWordTextField.setFont(Font.font(14));

        playButton = new Button("Jugar");
        playButton.setBackground(new Background(new BackgroundFill(
                Color.web("#00FFFF80"),
                new CornerRadii(5),
                Insets.EMPTY
                )));
        playButton.setTextFill(Color.BLACK);
        playButton.setFont(Font.font("Verdana", 14));
        playButton.setCursor(Cursor.HAND);

        errorMessage = new Label("Palabra no ingresada");
        errorMessage.setFont(Font.font(14));
        errorMessage.setTextFill(Color.BLACK);
        errorMessage.setWrapText(true);

        root.setBackground(new Background(new BackgroundFill(Color.web("#FADCD9"), CornerRadii.EMPTY, Insets.EMPTY)));

        root.getChildren().addAll(logoImageView, titleLabel, secretWordTextField, playButton, errorMessage);
    }

    /**
     * Method which initializes the homeControler variable by using
     * its constructor method with the gadgets who have events as parameters
     */
    private void initController(){
        homeController = new HomeController(secretWordTextField, playButton, this, errorMessage);
    }
}
