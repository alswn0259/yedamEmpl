package event;



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AppMain extends Application{

	@Override	//화면을 구현해서 만드는 작업 할것 
	public void start(Stage primaryStage) throws Exception {

		HBox root = new HBox();
		//컨테이너 크기 지정
		root.setPrefHeight(50);
		root.setPrefWidth(200);
		root.setSpacing(20);
		
		//컨테이너 버튼 생성
		Button btn1 = new Button("버튼1");
		btn1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("버튼1 클릭되었습니다.");
				
			}
		
		});
		
		Button btn2 = new Button("버튼2");
		btn2.setOnAction(event -> System.out.println("버튼2 클릭"));
		
		root.getChildren().add(btn1);
		root.getChildren().add(btn2);
		
		//신에 넣어서 실행해주면 그 안에 담은거 다 보여줌
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene); //만든 scene을 윈도위에 구성
		primaryStage.show(); //보여줌
	}
	
	public static void main(String[] args) {
		Application.launch(args); //Appmian 호출 satrt 메소드 호출 하는 역할
	}
	
}
