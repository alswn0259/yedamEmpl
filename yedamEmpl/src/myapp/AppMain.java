package myapp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AppMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		//컨트롤 : label, button
		Label label = new Label();
		label.setText("Hello, JavaFX"); //setText라벨에 보여주는 문구
		label.setFont(new Font(50)); //Font 클래스에 50인스턴스
		
		Button button = new Button();
		button.setText("확인");
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Platform.exit(); //프로그램 종료하고 나오세요 메소드.		
			}
		});
		
		//컨테이너 : VBox
		VBox root = new VBox();
		root.setPrefWidth(350); //폭
		root.setPrefHeight(150); //높이
		root.setAlignment(Pos.CENTER); //열거형 넣어줘야함 pos 사용
		root.setSpacing(20); //여백
		
		root.getChildren().add(label); //컨트롤 안에 라벨과 버튼 담는다.
		root.getChildren().add(button);
		
		//컨데이너를 Scene의 매개값으로 넣어줌(화면을 만든것)
		Scene scene = new Scene(root);
		
		// Stage의 매개값으로 Scene 담고 보여줌
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args); // Application.launch(메인메소드 매개변수)
								 //start 메소드가 시작됨. 
	}
	
}
