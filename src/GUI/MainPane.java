package GUI;

import java.io.File;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class MainPane extends GridPane{
	
	private Button btnUpload;
	private TextField txtStatus;
	private ImageView imageview;
	private Image img;
	
	public MainPane() {
		setUpGUI();
	}
	
	private void setUpGUI() {
		btnUpload = new Button("Upload Image");
		txtStatus = new TextField("No image uploaded");
		
		
		imageview = new ImageView(new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcThncOgOseI0qfhv5ANM_u4HzXaxA6GELfKxQ&s"));
		imageview.setFitHeight(700);
		imageview.setFitWidth(800);
		
		imageview.setPreserveRatio(true);
		
		insertComponents();
	}
	
	public void insertComponents() {
		setHgap(10);
		add(btnUpload,0,0);
		add(txtStatus,1,0);
		add(imageview,0,1,4,1);
		
	}
}
