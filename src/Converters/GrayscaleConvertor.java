package Converters;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class GrayscaleConvertor {

    private static boolean convertToGrayscale(String img) {
    	
    	String inputImagePath = "img";   // Change this to your actual image path
        String outputImagePath = "Images/output/output.png";

        Image src = new Image(new File(inputImagePath).toURI().toString());
        int width = (int) src.getWidth();
        int height = (int) src.getHeight();
        
        WritableImage grayImg = new WritableImage(width, height);
        PixelReader reader = src.getPixelReader();
        PixelWriter writer = grayImg.getPixelWriter();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int argb = reader.getArgb(x, y);
                int a = (argb >> 24) & 0xFF;
                int r = (argb >> 16) & 0xFF;
                int g = (argb >> 8) & 0xFF;
                int b = argb & 0xFF;
                int gray = (int) (0.299 * r + 0.587 * g + 0.114 * b);
                int grayArgb = (a << 24) | (gray << 16) | (gray << 8) | gray;
                writer.setArgb(x, y, grayArgb);
            }
       
      
        }
        return false;
}
}