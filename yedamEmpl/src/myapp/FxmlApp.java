package myapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxmlApp extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
				Parent root = FXMLLoader.load(getClass().getResource("viewPackage/Board.fxml")); //Label, Button
				//이거를 테그를 가지고 만들겠다는 것. 
				//VBox 이건 AnchorPane 이건 Parent 타입으로 하면 다 받을 수 있음
				
				
				//컨데이너를 Scene의 매개값으로 넣어줌(화면을 만든것)
				Scene scene = new Scene(root);
				
				// Stage의 매개값으로 Scene 담고 보여줌
				primaryStage.setScene(scene);
				primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
}
