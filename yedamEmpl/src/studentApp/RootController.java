package studentApp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RootController implements Initializable{
	@FXML Button addBtn;
	@FXML TableView<Student> tableView;
	
	private Stage primaryStage;
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		addBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				addBtnAction(e);
			}
			
		});
	
	}
	public void addBtnAction(ActionEvent e) {
		Stage stage = new Stage(StageStyle.DECORATED);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(primaryStage); //왜 필요한지?
		
		try {
			BorderPane bp = FXMLLoader.load(getClass().getResource("form.fxml"));
			Scene scene = new Scene(bp);
			stage.setScene(scene);
			stage.show();
			
			Button saveBtn = (Button) bp.lookup("#saveBtn");
			saveBtn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					TextField name = (TextField) bp.lookup("#name");
					TextField korScore = (TextField) bp.lookup("#korScore");
					TextField engScore = (TextField) bp.lookup("#engScore");
					TextField mathScore = (TextField) bp.lookup("#mathScore");
					
					ObservableList<Student> stdList = FXCollections.observableArrayList();
					Student std = new Student();
					std.setName(name.getText());
					std.setKorScore(korScore.getText());
					std.setEngScore(engScore.getText());
					std.setMathScore(mathScore.getText());
					stdList.add(std);
					System.out.println(stdList.toString());
					
					tableView.setItems(stdList); //매개값 오브절버브 리스트 여야함
				}
			});
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	} //end of addBtnAction
	
	
}
