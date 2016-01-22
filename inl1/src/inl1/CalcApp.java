package inl1;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class CalcApp extends Application {
	private TextField t1, t2, t3, t4;
	private Calculator calc;
	private int textCheck = 0;

	public static void main(String[] args) {
		Application.launch(args);
	}

	public void start(Stage stage) throws Exception {
		calc = new Calculator();

		this.t1 = new TextField();
		this.t2 = new TextField();
		this.t3 = new TextField();
		this.t4 = new TextField();
		t1.setDisable(true);
		t2.setDisable(true);
		t3.setDisable(true);
		t4.setDisable(true);
		t1.setAlignment(Pos.CENTER_RIGHT);
		t2.setAlignment(Pos.CENTER_RIGHT);
		t3.setAlignment(Pos.CENTER_RIGHT);
		t4.setAlignment(Pos.CENTER_RIGHT);
		t1.setPrefWidth(249);

		HBox h = new HBox();
		Button E = new Button("ENTER");
		Button chs = new Button("CHS");
		Button clear = new Button("CLEAR");
		Button cstk = new Button("CSTK");
		E.setPrefWidth(62);
		chs.setPrefWidth(62);
		clear.setPrefWidth(62);
		cstk.setPrefWidth(62);
		h.getChildren().addAll(E, chs, clear, cstk);
		E.setOnAction(e -> Enter());
		chs.setOnAction(e -> ChangeSign());
		clear.setOnAction(e -> Clear());
		cstk.setOnAction(e -> ClearStack());

		HBox h1 = new HBox();
		Button plus = new Button("+");
		Button b7 = new Button("7");
		Button b8 = new Button("8");
		Button b9 = new Button("9");
		plus.setPrefWidth(62);
		b7.setPrefWidth(62);
		b8.setPrefWidth(62);
		b9.setPrefWidth(62);
		h1.getChildren().addAll(plus, b7, b8, b9);
		plus.setOnAction(e -> plus());
		b7.setOnAction(e -> nbr7());
		b8.setOnAction(e -> nbr8());
		b9.setOnAction(e -> nbr9());

		HBox h2 = new HBox();
		Button minus = new Button("-");
		Button b4 = new Button("4");
		Button b5 = new Button("5");
		Button b6 = new Button("6");
		minus.setPrefWidth(62);
		b4.setPrefWidth(62);
		b5.setPrefWidth(62);
		b6.setPrefWidth(62);
		h2.getChildren().addAll(minus, b4, b5, b6);
		b4.setOnAction(e -> nbr4());
		b5.setOnAction(e -> nbr5());
		b6.setOnAction(e -> nbr6());
		minus.setOnAction(e -> minus());

		HBox h3 = new HBox();
		Button multi = new Button("*");
		Button b1 = new Button("1");
		Button b2 = new Button("2");
		Button b3 = new Button("3");
		multi.setPrefWidth(62);
		b1.setPrefWidth(62);
		b2.setPrefWidth(62);
		b3.setPrefWidth(62);
		h3.getChildren().addAll(multi, b1, b2, b3);
		b1.setOnAction(e -> nbr1());
		b2.setOnAction(e -> nbr2());
		b3.setOnAction(e -> nbr3());
		multi.setOnAction(e -> multi());

		HBox h4 = new HBox();
		Button div = new Button("/");
		Button b0 = new Button("0");
		div.setPrefWidth(62);
		b0.setPrefWidth(62);
		h4.getChildren().addAll(div, b0);
		b0.setOnAction(e -> nbr0());
		div.setOnAction(e -> div());

		GridPane grid = new GridPane();

		grid.add(t1, 0, 0);
		grid.add(t2, 0, 1);
		grid.add(t3, 0, 2);
		grid.add(t4, 0, 3);
		grid.add(h, 0, 4);
		grid.add(h1, 0, 5);
		grid.add(h2, 0, 6);
		grid.add(h3, 0, 7);
		grid.add(h4, 0, 8);

		stage.setScene(new Scene(grid, 250, 230));
		stage.setTitle("Calculator");

		stage.show();
	}

	private void Enter() {

		if (textCheck == 0) {

			t2.setText(t1.getText());
			int nbr = Integer.parseInt(t1.getText());
			calc.add(1, nbr);
		
		}
		if (textCheck == 1) {
			if (t1.getLength() == 0) {
				t3.setText(t2.getText());
				t2.setText(t3.getText());
				int nbr = Integer.parseInt(t2.getText());
				calc.add(1, nbr);
				calc.add(2, nbr);

			} else {
				t3.setText(t2.getText());
				t2.setText(t1.getText());
				int nbr = Integer.parseInt(t3.getText());
				int n = Integer.parseInt(t2.getText());
				calc.add(1, n);
				calc.add(2, nbr);

			}
		}

		if (textCheck == 2) {
			if (t1.getLength() == 0) {
				
				t4.setText(t3.getText());
				t3.setText(t2.getText());
			
				int nbr = Integer.parseInt(t3.getText());
				calc.add(3, nbr);

			} else {
				t4.setText(t3.getText());
				t3.setText(t2.getText());
				t2.setText(t1.getText());
				int a = Integer.parseInt(t4.getText());
				int nbr = Integer.parseInt(t3.getText());
				int n = Integer.parseInt(t2.getText());
				calc.add(3, a);
				calc.add(2, nbr);
				calc.add(1, n);
			}
		}

		t1.clear();
		textCheck++;

	}

	private void ChangeSign() { 
		int nbr = -calc.getFirst();
		t1.setText(Integer.toString(nbr)); 
		calc.add(0, nbr);

	}

	private void Clear() { 
		t1.clear();
		calc.reset(0); 

	}

	private void ClearStack() { 
		calc.resetAll();
		t1.clear();
		t2.clear();
		t3.clear();
		t4.clear();
		textCheck = 0;
		
	}

	private void plus() { 
		if(t2.getLength()==0){
			t1.setText(t1.getText());
		}else {

		
		t1.setText(String.valueOf(calc.plus()));
		t2.setText(t3.getText());
		t3.setText(t4.getText());
		
		if(t2.getLength()>0){
		calc.add(1, Integer.parseInt(t2.getText()));
		}	
		if(t3.getLength()>0){
		calc.add(2,Integer.parseInt(t3.getText()) );
		}
		if(t4.getLength()>0){
			calc.add(3,Integer.parseInt(t4.getText()) );
		}
		}
		textCheck = 0;

	}

	private void minus() {
		if(t2.getLength()==0){
			t1.setText(t1.getText());
		}else {
			
		
		
		t1.setText(String.valueOf(calc.minus()));
		t2.setText(t3.getText());
		t3.setText(t4.getText());
	
		if(t2.getLength()>0){
			calc.add(1, Integer.parseInt(t2.getText()));
			}	
			if(t3.getLength()>0){
			calc.add(2,Integer.parseInt(t3.getText()) );
			}
			if(t4.getLength()>0){
				calc.add(3,Integer.parseInt(t4.getText()) );
			}
		}
		textCheck = 0;
	}

	private void multi() {
		if(t2.getLength()==0){
			t1.setText(t1.getText());
		}else {
		t1.setText(String.valueOf(calc.multiply()));
		t2.setText(t3.getText());
		t3.setText(t4.getText());
		
		if(t2.getLength()>0){
			calc.add(1, Integer.parseInt(t2.getText()));
			}	
			if(t3.getLength()>0){
			calc.add(2,Integer.parseInt(t3.getText()) );
			}
			if(t4.getLength()>0){
				calc.add(3,Integer.parseInt(t4.getText()) );
			}
		}
		textCheck = 0;
	}

	private void div() {
		if(t2.getLength()==0){
			t1.setText(t1.getText());
		}else {
		t1.setText(String.valueOf(calc.division()));
		t2.setText(t3.getText());
		t3.setText(t4.getText());
		if(t2.getLength()>0){
			calc.add(1, Integer.parseInt(t2.getText()));
			}	
			if(t3.getLength()>0){
			calc.add(2,Integer.parseInt(t3.getText()) );
			}
			if(t4.getLength()>0){
				calc.add(3,Integer.parseInt(t4.getText()) );
			}
		}
		textCheck = 0;

	}

	private void nbr0() {

		t1.setText(t1.getText());
		t1.appendText(Integer.toString(0)); 
		int nbr = Integer.parseInt(t1.getText());
		calc.add(0, nbr);
	}

	private void nbr1() {

		t1.setText(t1.getText());
		t1.appendText(Integer.toString(1));
		int nbr = Integer.parseInt(t1.getText());
		calc.add(0, nbr);
	}

	private void nbr2() {
		t1.setText(t1.getText());
		t1.appendText(Integer.toString(2));
		int nbr = Integer.parseInt(t1.getText());
		calc.add(0, nbr);
	}

	private void nbr3() {
		t1.setText(t1.getText());
		t1.appendText(Integer.toString(3));
		int nbr = Integer.parseInt(t1.getText());
		calc.add(0, nbr);
	}

	private void nbr4() {
		t1.setText(t1.getText());
		t1.appendText(Integer.toString(4));
		int nbr = Integer.parseInt(t1.getText());
		calc.add(0, nbr);
	}

	private void nbr5() {
		t1.setText(t1.getText());
		t1.appendText(Integer.toString(5));
		int nbr = Integer.parseInt(t1.getText());
		calc.add(0, nbr);
	}

	private void nbr6() {
		t1.setText(t1.getText());
		t1.appendText(Integer.toString(6));
		int nbr = Integer.parseInt(t1.getText());
		calc.add(0, nbr);
	}

	private void nbr7() {
		t1.setText(t1.getText());
		t1.appendText(Integer.toString(7));
		int nbr = Integer.parseInt(t1.getText());
		calc.add(0, nbr);
	}

	private void nbr8() {
		t1.setText(t1.getText());
		t1.appendText(Integer.toString(8));
		int nbr = Integer.parseInt(t1.getText());
		calc.add(0, nbr);
	}

	private void nbr9() {
		t1.setText(t1.getText());
		t1.appendText(Integer.toString(9));
		int nbr = Integer.parseInt(t1.getText());
		calc.add(0, nbr);
	}

}
