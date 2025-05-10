package GUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainPane extends GridPane {
    private Button btnUpload;
    private TextField txtStatus;
    private Label lblOriginal;
    private Label lblGray; 
    private ImageView imageview;
    private ImageView grayscaleView;
    private Image img;

    public MainPane(Stage stage) {
        setUpGUI();
        uploadImage(stage);
    }

    private void setUpGUI() {
        // Layout
        setAlignment(Pos.TOP_CENTER);
        setHgap(20);
        setVgap(20);

        // Controls
        btnUpload = new Button("Upload Image");
        txtStatus = new TextField("No image uploaded");
        txtStatus.setEditable(false);

        
        lblOriginal = new Label("Figure 1: Original Image");
        lblGray = new Label("Figure 2: Grayscale Image");
        // Original image view
        imageview = new ImageView();
        imageview.setFitWidth(400);
        imageview.setFitHeight(400);
        imageview.setPreserveRatio(true);

        // Grayscale image view
        grayscaleView = new ImageView();
        grayscaleView.setFitWidth(400);
        grayscaleView.setFitHeight(400);
        grayscaleView.setPreserveRatio(true);

        // Add to grid: upload row
        add(btnUpload, 0, 0);
        add(txtStatus, 1, 0);
        // Add to grid: images row, side by side
        add(imageview, 0, 1);
        add(grayscaleView, 1, 1);
        add(lblOriginal, 0, 2);
        add(lblGray, 1, 2);
    }

    private void uploadImage(Stage stage) {
        btnUpload.setOnAction(e -> {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Choose an image");
            chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.bmp", "*.gif")
            );
            File file = chooser.showOpenDialog(stage);
            if (file != null) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    img = new Image(fis);
                    imageview.setImage(img);

                    // Convert to grayscale and show
                    Image gray = convertToGrayscale(img);
                    grayscaleView.setImage(gray);

                    txtStatus.setText("Loaded: " + file.getName());
                } catch (IOException ex) {
                    txtStatus.setText("Error: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
    }

    private Image convertToGrayscale(Image colorImage) {
        int w = (int) colorImage.getWidth();
        int h = (int) colorImage.getHeight();
        WritableImage grayImg = new WritableImage(w, h);
        PixelReader reader = colorImage.getPixelReader();
        PixelWriter writer = grayImg.getPixelWriter();

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                javafx.scene.paint.Color c = reader.getColor(x, y);
                double lum = 0.2126 * c.getRed()
                           + 0.7152 * c.getGreen()
                           + 0.0722 * c.getBlue();
                javafx.scene.paint.Color g = new javafx.scene.paint.Color(lum, lum, lum, c.getOpacity());
                writer.setColor(x, y, g);
            }
        }
        return grayImg;
    }
}
