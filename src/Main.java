import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

import GUI.MainPane;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

public class Main extends Application {

    private static final int THRESHOLD = 100;
    
   
    @Override
    public void start(Stage primaryStage) {
       MainPane pane = new MainPane(primaryStage);
       Scene scene = new Scene(pane);
       primaryStage.setScene(scene);
       primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

