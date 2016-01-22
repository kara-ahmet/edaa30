package phonebook;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class PhoneBookGUI{
	private PhoneBook phoneBook;
	private BorderPane root;
	private TextArea textArea;
	
	public PhoneBookGUI(PhoneBook pb) {
		phoneBook = pb;

		textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setWrapText(true);
		textArea.setPrefColumnCount(50);
		textArea.setPrefRowCount(20);
		
		Button quit = new Button();				
		quit.setText("Quit");					
		quit.setOnAction(e -> exit());			
		
		root = new BorderPane();
		root.setTop(makeMenu());
		root.setCenter(textArea);
		root.setBottom(quit);
		
	}
	
	public Parent getRoot(){
		return root;
	}

	
	private MenuBar makeMenu(){											
		final Menu menuEdit = new Menu("Edit");
		final MenuItem menuAdd = new MenuItem("Add");
		menuAdd.setOnAction(e -> add());
		final MenuItem menuRemoveP = new MenuItem("Remove Person");
		menuRemoveP.setOnAction(e -> removeName());
		final MenuItem menuRemoveN = new MenuItem("Remove Number");
		menuRemoveN.setOnAction(e -> removeNbr());
		menuEdit.getItems().addAll(menuAdd, menuRemoveP,menuRemoveN);
	
		final Menu menuFind = new Menu("Find");
		final MenuItem menuFindName = new MenuItem("Find Name");
		menuFindName.setOnAction(e -> FindName());
		final MenuItem menuFindNbr = new MenuItem("Find Number");
		menuFindNbr.setOnAction(e -> FindNbr());
		menuFind.getItems().addAll(menuFindName,menuFindNbr);
		
		final Menu menuView = new Menu("View");
		final MenuItem menuShowAll = new MenuItem("ShowPhoneBook");
		menuShowAll.setOnAction(e -> show());
		menuView.getItems().addAll(menuShowAll);
		
	
	    MenuBar menuBar = new MenuBar();
	    menuBar.getMenus().addAll(menuEdit, menuFind, menuView);
	 // menuBar.setUseSystemMenuBar(true);  // if you want operating system rendered menus, uncomment this line
		return menuBar;
	}

	private void add() {	
		TextInputDialog dialog = new TextInputDialog("");				
		dialog.setTitle("Add");
		dialog.setHeaderText("PhoneBook");
		dialog.setContentText("Enter Name:");
		
		Optional<String> result = dialog.showAndWait();
															
		TextInputDialog dialog2 = new TextInputDialog("");
		dialog2.setTitle("Add");
		dialog2.setHeaderText("PhoneBook");
		dialog2.setContentText("Enter Number:");
		Optional<String> result2 = dialog2.showAndWait();
		phoneBook.put(result.get(), result2.get());			
		
			
	}
	
	private void removeName() {								
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Remove");
		dialog.setHeaderText("PhoneBook");
		dialog.setContentText("Enter Name:");
		Optional<String> result = dialog.showAndWait();
		phoneBook.remove(result.get());					
		
	}

	private void removeNbr() {								
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Remove");
		dialog.setHeaderText("PhoneBook");
		dialog.setContentText("Enter Number:");
		Optional<String> result = dialog.showAndWait();
		List<String> list = phoneBook.findNames(result.get());	
		Iterator<String> itr = list.iterator();
		while(itr.hasNext()){
			String name = itr.next();
			phoneBook.removeNumber(name, result.get());			
		}	
		
	}
	
	private void FindName(){								
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Find");
		dialog.setHeaderText("PhoneBook");
		dialog.setContentText("Enter Name:");
		Optional<String> result = dialog.showAndWait();
		
		Alert alert = new Alert(AlertType.INFORMATION);		
		StringBuilder sb = new StringBuilder();				
		alert.setTitle("PhoneBook");
		alert.setHeaderText("Name");
		
		LinkedList <String> list = phoneBook.findNumber(result.get());	
		Iterator <String> itr = list.iterator();
		while(itr.hasNext()){
			String nbr =  itr.next();						
			sb.append(result.get()+ "\t" + nbr);			
			sb.append("\n");								
			}
		
		alert.setContentText(sb.toString());				
		alert.showAndWait();
		
	}
	private void FindNbr(){									
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Find");
		dialog.setHeaderText("PhoneBook");
		dialog.setContentText("Enter Number:");
		Optional<String> result = dialog.showAndWait();
		System.out.println(phoneBook.findNames(result.get()));
		
		
		Alert alert = new Alert(AlertType.INFORMATION);
		StringBuilder sb = new StringBuilder();
		alert.setTitle("PhoneBook");
		alert.setHeaderText("Number");
		
		LinkedList <String> list = phoneBook.findNames(result.get());
		Iterator <String> itr = list.iterator();
		while(itr.hasNext()){
			String name =  itr.next();
			sb.append(name + "\t" + result.get() );
			sb.append("\n");
			}
		
		alert.setContentText(sb.toString());
		alert.showAndWait();
	}
	
	private void exit(){									
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Save");
		dialog.setHeaderText("Save to File");
		dialog.setContentText("Enter FileName:");
		Optional<String> result = dialog.showAndWait();
		if(result.isPresent()){
		File f = new File(result.get());					
		phoneBook.save(f);}									
		Platform.exit();
	}
	
	private void show(){
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("PhoneBook");
		alert.setHeaderText("PhoneBook");
		alert.setContentText(phoneBook.toString());			
		alert.showAndWait();

		
	}
	
	private Optional<String> oneInputDialog(String title, String headerText, String label) {
		TextInputDialog dialog = new TextInputDialog();
		// add content to dialog
		return dialog.showAndWait();
	}
	
	private Optional<String[]> twoInputsDialog(String title, String headerText, String[] labels) {
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle(title);
		dialog.setHeaderText(headerText);
		dialog.setResizable(true);
		Label label1 = new Label(labels[0] + ':');
		Label label2 = new Label(labels[1] + ':');
		TextField tf1 = new TextField();
		TextField tf2 = new TextField();
		GridPane grid = new GridPane();
		grid.add(label1, 1, 1);
		grid.add(tf1, 2, 1);
		grid.add(label2, 1, 2);
		grid.add(tf2, 2, 2);
		
		dialog.getDialogPane().setContent(grid);
		ButtonType buttonTypeOk = new ButtonType("Ok", ButtonData.OK_DONE);
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonTypeCancel, buttonTypeOk);
		dialog.setResultConverter(new Callback<ButtonType, String>() {
			
			@Override
			public String call(ButtonType b) {
				String inputs = null;
				if (b == buttonTypeOk) {
					inputs = tf1.getText() + ":" + tf2.getText();
					
				}
				return inputs;
			}
		});
		tf1.requestFocus();

		Optional<String> result = dialog.showAndWait();
		String[] input = null;
		if (result.isPresent()) {
			input = result.get().split(":");
		}
		return Optional.ofNullable(input);
	}
}
