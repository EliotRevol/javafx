package thecoursemaker;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	private Scene scene;
	private MenuHandler menu;
	private Pane working_window;
	
	private AppMenuBar app_menubar;
	
	public static State appState=State.InitialState;
	public static String filename;
	public static StackPane root;
	public static Stage stage;
	public enum State {
		  InitialState, CreateState, ExecuteState
	}
	
	public Main() {
		//Create menu bar
		app_menubar = new AppMenuBar();
		menu = app_menubar.getMenuBarHandler();
		
		// Nœud racine.
		root = new StackPane();
		
		root.getChildren().setAll(menu.getMenuBar());
		
		// Configuration de la scène.
		scene = new Scene(root);
	}
	
	@Override
	public void init() throws Exception {
	super.init();
		// Faire des initialisations ici.
		System.out.print(Thread.currentThread().getName());
		System.out.println("execute la méthode init de la classe MainJavaFX");
	}
	
	 @Override
	public void start(Stage primaryStage) {
		System.out.print(Thread.currentThread().getName());
		System.out.println(" execute la méthode start de la classe MainJavaFX");
		stage = primaryStage;
		
		// Configuration de la fenêtre.
		primaryStage.setScene(scene);
		primaryStage.setTitle("The Course Maker");
		primaryStage.setWidth(1000);
		primaryStage.setHeight(1000);
		primaryStage.show();
	}
	public static void main(String[] args) { launch(args); } 
	
	
}