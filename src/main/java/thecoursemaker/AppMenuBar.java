package thecoursemaker;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import thecoursemaker.Main.State;

public class AppMenuBar {
	private File opened_file;
	private MenuHandler menu;
	private AppCreateState appCreateState;
	
	public AppMenuBar() {
		System.out.print(Thread.currentThread().getName());
		System.out.println(" appelle le constructeur de la Classe MainJavaFX");
		
		menu = new MenuHandler();
		menu.addMenu("File","Project","Options");
		menu.addMenuItem("Options", "Reload");
		menu.addMenuItem("Project", "Add picture");
		menu.addMenuItem("File","New project","Open project","Save","Close project","Exit");
		
		updateMenu();
		
		EventHandler<ActionEvent> new_project = new EventHandler<ActionEvent>() {
			@Override
            public void handle(ActionEvent event) {
				TextField textField = new TextField();

		        // Créer une boîte de dialogue de type "Confirmation"
		        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		        alert.setTitle("New project");
		        alert.setHeaderText("Enter a name for the new project :");

		        // Créer une mise en page VBox pour organiser les éléments
		        VBox vbox = new VBox();
		        vbox.getChildren().add(textField);

		        // Définir la mise en page de la boîte de dialogue
		        alert.getDialogPane().setContent(vbox);

		        // Ajouter les boutons OK et Annuler
		        alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);

		        // Afficher la boîte de dialogue et attendre la réponse de l'utilisateur
		        alert.showAndWait().ifPresent(response -> {
		            if (response == ButtonType.OK) {
		                Main.filename = textField.getText();
		                //Pattern p = Pattern.compile("[^a-zA-Z0-9]*")
		                if(Main.filename.equals("")) {
		                	Alert error_alert = new Alert(Alert.AlertType.ERROR);
		                	error_alert.setContentText("Error : you must specify a name to create a new project");
		                	error_alert.show();
		                }
		                else {
		                	opened_file = new File(Main.filename+ ".tcm");
		                    try {
								if (opened_file.createNewFile()) {
								    Main.appState = State.CreateState;
								    Pane working_window = new Pane();
								    Image image = new Image(getClass().getResource("exemple.jpg").toExternalForm());
									ImageView imageView = new ImageView(image);
									working_window.getChildren().setAll(imageView);
								    Main.root.getChildren().add(0, working_window);
								} else {
									Alert error_alert = new Alert(Alert.AlertType.ERROR);
				                	error_alert.setContentText("Error : file already exist ! Overwrite ?");
				                	error_alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
				                	error_alert.showAndWait().ifPresent(response2 -> {
				    		            if (response2 == ButtonType.OK) {
				    		            	if(opened_file.delete()) {
				    		            		System.out.println("File deleted");
				    		            		try {
													if (opened_file.createNewFile()) {
														System.out.println("New file created");
													    Main.appState = State.CreateState;
													    appCreateState = new AppCreateState();
													    
													}
												} catch (IOException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											}
				    		            	else {
					    		            	System.out.println("Couldn't overwrite file");
					    		            }
				    		            }
				    		            else {
				    		                System.out.println("Opération annulée par l'utilisateur");
				    		            }
				                	});
								}
							} catch (IOException e) {
								Alert error_alert = new Alert(Alert.AlertType.CONFIRMATION);
			                	error_alert.setContentText("Error : invalid name type");
			                	error_alert.show();
							}
							
		                	//TODO Make the create window
		                }
		            } else {
		                System.out.println("Opération annulée par l'utilisateur");
		            }
		        });
            }
		};
		
		EventHandler<ActionEvent> open_project = new EventHandler<ActionEvent>() {
			@Override
            public void handle(ActionEvent event) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Select a project to open");
				fileChooser.setInitialDirectory(new File("./"));
				fileChooser.getExtensionFilters().add(new ExtensionFilter("TCM files","*.tcm"));
				File selectedFile = fileChooser.showOpenDialog(Main.stage);
				if (checkPath(selectedFile,"tcm")) {
					System.out.println(selectedFile);
					Main.appState = State.CreateState;
					updateMenu();
					//Main.stage.display(selectedFile);
				 }
			}
		};
		
		////////Set actions////////////
		menu.setAction("File","New project",new_project);
		menu.setAction("File","Open project",open_project);
		StackPane.setAlignment(menu.getMenuBar(), javafx.geometry.Pos.TOP_LEFT);
	}
	
	public void updateMenu() {
		if(Main.appState == State.InitialState) {
			menu.makeUnclickable("File", "Close project", "Save");
			menu.makeUnclickable("Options", "Reload");
			menu.makeUnclickable("Project", "Add picture");
		}
		else if(Main.appState == State.CreateState) {
			menu.makeClickable("File", "Close project", "Save");
			menu.makeClickable("Project", "Add picture");
			menu.makeUnclickable("Options", "Reload");
		}
		else if(Main.appState==State.ExecuteState) {
			menu.makeUnclickable("File", "Save");
			menu.makeClickable("Options", "Reload");
			menu.makeClickable("File", "Close project");
			menu.makeUnclickable("Project", "Add picture");
		}
	}
	
	public static boolean checkPath(File file,String... extensions) {
		if (file == null) return false;
		String path = file.getName();
		String fileExtension = path.substring(path.lastIndexOf(".") + 1,file.getName().length());
		for(String extension:extensions) {
			if(extension.equals(fileExtension)) {
				return true;
			}
		}
		return false;
	}
	
	public MenuHandler getMenuBarHandler() {
		return menu;
	}
}
