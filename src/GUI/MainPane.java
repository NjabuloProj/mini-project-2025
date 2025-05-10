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
    private ImageView imageview;
    private Label lbLabel;
    private ImageView grayscaleView;
    private ImageView edgeView;
    private Image img;

    public MainPane(Stage stage) {
        setUpGUI();
        uploadImage(stage);
    }

    private void setUpGUI() {
        // layout spacing and alignment
        setAlignment(Pos.TOP_CENTER);
        setHgap(20);
        setVgap(20);

        // controls
        btnUpload = new Button("Upload Image");
        txtStatus = new TextField("No image uploaded");
        txtStatus.setEditable(false);

        // original image
        imageview = new ImageView();
        imageview.setFitWidth(300);
        imageview.setFitHeight(300);
        imageview.setPreserveRatio(true);

        lbLabel = new Label();
        // grayscale image
        grayscaleView = new ImageView();
        grayscaleView.setFitWidth(300);
        grayscaleView.setFitHeight(300);
        grayscaleView.setPreserveRatio(true);

        // edge mapping image
        edgeView = new ImageView();
        edgeView.setFitWidth(300);
        edgeView.setFitHeight(300);
        edgeView.setPreserveRatio(true);

        // add to grid: upload controls
        add(btnUpload,      0, 0, 1, 1);
        add(txtStatus,      1, 0, 2, 1);
        // add to grid: images row
        add(imageview,      0, 1);
        add(grayscaleView,  1, 1);
        add(edgeView,       2, 1);
        add(lbLabel, 0, 3);
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

                    // 1) Convert to grayscale
                    Image gray = convertToGrayscale(img);
                    grayscaleView.setImage(gray);

                    // 2) Detect edges (edges black on white background)
                    Image edges = detectEdges(gray, 0.2);
                    edgeView.setImage(edges);
                    
                    AnimalCounter counter = new AnimalCounter(edges);
                   
                	int animalCount = counter.countAnimals();
                	 lbLabel.setText(String.valueOf(animalCount));
                	System.out.println("Animals detected: " + animalCount);

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
                writer.setColor(x, y,
                    new javafx.scene.paint.Color(lum, lum, lum, c.getOpacity()));
            }
        }
        return grayImg;
    }

    private Image detectEdges(Image grayImage, double threshold) {
        int w = (int) grayImage.getWidth();
        int h = (int) grayImage.getHeight();
        WritableImage edgeImg = new WritableImage(w, h);
        PixelReader reader = grayImage.getPixelReader();
        PixelWriter writer = edgeImg.getPixelWriter();

        // Sobel kernels
        int[][] kx = {
            { -1, 0, 1 },
            { -2, 0, 2 },
            { -1, 0, 1 }
        };
        int[][] ky = {
            { -1, -2, -1 },
            {  0,  0,  0 },
            {  1,  2,  1 }
        };
        double maxMag = Math.sqrt((4*255)*(4*255)*2);

        // initialize background to white
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                writer.setColor(x, y, javafx.scene.paint.Color.WHITE);
            }
        }

        for (int y = 1; y < h - 1; y++) {
            for (int x = 1; x < w - 1; x++) {
                double gx = 0, gy = 0;
                for (int j = -1; j <= 1; j++) {
                    for (int i = -1; i <= 1; i++) {
                        double lum = reader.getColor(x + i, y + j).getRed() * 255;
                        gx += kx[j + 1][i + 1] * lum;
                        gy += ky[j + 1][i + 1] * lum;
                    }
                }
                double mag = Math.hypot(gx, gy) / maxMag;
                if (mag > threshold) {
                    writer.setColor(x, y, javafx.scene.paint.Color.BLACK);
                }
            }
        }
        return edgeImg;
    }
}
