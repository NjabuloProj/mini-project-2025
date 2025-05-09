import GUI.MainPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		MainPane root = new MainPane();
		
		Scene scene = new Scene(root,900,700);
		stage.setScene(scene);
		stage.show();
	}

}
