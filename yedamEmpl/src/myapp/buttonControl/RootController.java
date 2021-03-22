package myapp.buttonControl;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class RootController implements Initializable {
	@FXML //선언했던 버튼 요론 방식으로 사용하겠다
	Button btn1, btn2, btn3;
	
	@Override //controller 실행되면 젤 먼저 실행
	public void initialize(URL arg0, ResourceBundle arg1) {
	
	}
	
	public void handleBtn1Action(ActionEvent e) {
		System.out.println("버튼1 클릭.");
	}
	
	public void handleBtn2Action(ActionEvent e) {
		System.out.println("버튼2 클릭.");
	}
	
	public void handleBtn3Action(ActionEvent e) {
		Platform.exit();
	}

}
