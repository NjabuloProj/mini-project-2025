import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BackgroundremoverFX extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        String inputImagePath = "Images/livestock3.jpeg";   // Change this to your actual image path
        String outputImagePath = "grayscale_output3.png";

        // Load image
        Image inputImage = new Image(new File(inputImagePath).toURI().toString());
        int width = (int) inputImage.getWidth();
        int height = (int) inputImage.getHeight();

        PixelReader reader = inputImage.getPixelReader();
        WritableImage grayscaleImage = new WritableImage(width, height);
        PixelWriter writer = grayscaleImage.getPixelWriter();

        // Convert each pixel to grayscale using luminance
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = reader.getColor(x, y);

                // Calculate luminance (perceptual grayscale)
                double luminance = 0.2126 * color.getRed() +
                                   0.7152 * color.getGreen() +
                                   0.0722 * color.getBlue();

                Color gray = new Color(luminance, luminance, luminance, color.getOpacity());
                writer.setColor(x, y, gray);
            }
        }

        // Save to file
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(grayscaleImage, null);
        ImageIO.write(bufferedImage, "png", new File(outputImagePath));
        System.out.println("Saved grayscale image: " + outputImagePath);

        // Display in UI
        ImageView imageView = new ImageView(grayscaleImage);
        StackPane root = new StackPane(imageView);
        Scene scene = new Scene(root, width, height);
        primaryStage.setTitle("Grayscale Image");
        primaryStage.setScene(scene);
       // primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
