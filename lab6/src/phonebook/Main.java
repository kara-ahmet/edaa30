package phonebook;

import java.io.File;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class Main extends Application {
	private PhoneBook phoneBook;
	
	/**
	 * The entry point for the Java program.
	 * @param args
	 */
	public static void main(String[] args) {	
		// launch() do the following:
		// - creates an instance of the Main class
		// - calls Main.init()
		// - create and start the javaFX application thread
		// - waits for the javaFX application to finish (close all windows)
		// the javaFX application thread do:
		// - calls Main.start(Stage s)
		// - runs the event handling loop
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Here you can place any action to be done when the application is opened, i.e. read phone book from file.
		phoneBook = new PhoneBook();

		PhoneBookGUI gui = new PhoneBookGUI(phoneBook);

		Scene scene = new Scene(gui.getRoot());

		primaryStage.setTitle("PhoneBook");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		TextInputDialog dialog = new TextInputDialog("");		
		dialog.setTitle("Open");								
		dialog.setHeaderText("Open File");
		dialog.setContentText("Enter FileName:");
		
		Optional<String> result = dialog.showAndWait();			
		
		if(result.isPresent()){									
		File f = new File(result.get());						
		phoneBook.open(f);										
		}
	
	}

	@Override
	public void stop() {
		
		
		// Here you can place any action to be done when the application is closed, i.e. save phone book to file.
	}

}
