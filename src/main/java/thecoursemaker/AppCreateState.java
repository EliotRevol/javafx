package thecoursemaker;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class AppCreateState {
	public AppCreateState() {
		Pane working_window = new Pane();
		
	    Image image = new Image(getClass().getResource("exemple.jpg").toExternalForm());
		ImageView imageView = new ImageView(image);
		working_window.getChildren().setAll(imageView);
	    Main.root.getChildren().add(0, working_window);
	}
}
