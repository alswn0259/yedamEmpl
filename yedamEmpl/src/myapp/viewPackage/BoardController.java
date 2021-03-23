package myapp.viewPackage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import common.BoardVO;
import common.InputBoardVO;
import common.InputDAO;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

//Board.fxml
//BoardController.java

	
	public class BoardController implements Initializable {
	@FXML TableView<BoardVO> tableView;
	@FXML TextField boardNo, title;
	@FXML TextArea contents;
	@FXML ComboBox<String> publicity;
	@FXML DatePicker exitDate;
	@FXML Button updateBtn, deleteBtn, addBtn, beforeBtn, afterBtn ;
	
	private Stage primaryStage;

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//삭제는 메소드 호출해서 쓰는걸로 할거기 때문 나머지 두개는 기능정의 했었음.
		//람다식 표현 deleteBtn.setOnAction(e -> deletBtnAction(e));
		deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				deleteBtnAction(e);
			}
		});
		addBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				addBtnAction(e);
				
			}
			
		});
		beforeBtn.setOnAction(e -> beforeBtnAction(e));
		
		
		ObservableList<BoardVO> list = InputDAO.boardList();
		
		tableView.setPrefWidth(440);
		tableView.setPrefHeight(120);
		tableView.setLayoutX(30);
		tableView.setLayoutX(30);
		
		TableColumn<BoardVO, Integer> tcBoardNo = (TableColumn<BoardVO, Integer>) tableView.getColumns().get(0);
		tcBoardNo.setCellValueFactory(new PropertyValueFactory<BoardVO, Integer>("boardNo"));
		tcBoardNo.setPrefWidth(70);
		//이번 열은 callback 인터페이스 구현하는걸로 해보기
		TableColumn<BoardVO, String> tcTitle = (TableColumn<BoardVO, String>) tableView.getColumns().get(1);
		tcTitle.setCellValueFactory(new Callback<CellDataFeatures<BoardVO,String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<BoardVO, String> param) {
				return param.getValue().titleProperty();
			}
			
		});
		TableColumn<BoardVO, String> tcPub = new TableColumn<BoardVO, String>("공개여부");
		tcPub.setCellValueFactory(new PropertyValueFactory<BoardVO, String>("publicity"));
		//위에 두개변수는 추가되어져 있는 상태 였지만 이건 아니니까 추가해준다.
		tableView.getColumns().add(tcPub);
		
		TableColumn<BoardVO, String> tcDate = new TableColumn<BoardVO, String>("종료일자");
		tcDate.setCellValueFactory(new PropertyValueFactory<BoardVO, String>("exitDate"));
		tableView.getColumns().add(tcDate);
		
		TableColumn<BoardVO, String> tcContents = new TableColumn<BoardVO, String>("내용");
		tcContents.setCellValueFactory(new PropertyValueFactory<BoardVO, String>("contents"));
		tableView.getColumns().add(tcContents);
		
		tableView.setItems(list);
		
		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BoardVO>() {

			@Override
			public void changed(ObservableValue<? extends BoardVO> arg0, BoardVO oldVal, BoardVO newVal) {
				if(newVal != null) { //무언가 null로 인식하기 때문에 null 이면 실행안하게 만들어 줄려고
				System.out.println(newVal.getBoardNo()); //잘 나오는지 확인하려고
				boardNo.setText(String.valueOf(newVal.getBoardNo()));
				title.setText(newVal.getTitle());
				publicity.setValue(newVal.getPublicity());
				contents.setText(newVal.getContents());
				exitDate.setValue(LocalDate.parse(newVal.getExitDate()));// 타입변환 1권에 api
				}
			}
		});
	}// end of initialize
	
	//수정 종료만 메소드 만들어서 하고 버튼은 위에서 해줄거임
	public void updateBtnAction(ActionEvent e) {
		BoardVO vo = new BoardVO();
		vo.setBoardNo(Integer.parseInt(boardNo.getText()));
		vo.setContents(contents.getText());
		vo.setExitDate(exitDate.getValue().toString());
		vo.setPublicity(publicity.getValue());
		InputDAO.updateBoard(vo);
		
		tableView.setItems(InputDAO.boardList());
	}
	
	public void deleteBtnAction(ActionEvent e) {
		BoardVO vo = new BoardVO();
		vo.setBoardNo(Integer.parseInt(boardNo.getText()));
		InputDAO.deleteBoard(vo);
		
		tableView.setItems(InputDAO.boardList());
	}
	
	public void addBtnAction(ActionEvent e) {
		Stage stage = new Stage(StageStyle.DECORATED); //window 하나 만듬
		stage.initModality(Modality.WINDOW_MODAL); //window 타입을 모달 타입.(모달: 새로띄운창 그창이 닫기기 전까지 뒷창으로 안바뀜)
		stage.initOwner(primaryStage);//main window에 갖다 붙이겠습니다.
		
		try {
			AnchorPane ap = FXMLLoader.load(getClass().getResource("BoardAdd.fxml"));
			Scene scene = new Scene(ap);
			stage.setScene(scene);
			stage.show();
			
			Button btnReg = (Button) ap.lookup("#btnReg");
			btnReg.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					TextField txtTitle = (TextField) ap.lookup("#txtTitle");
					PasswordField txtPasswd = (PasswordField) ap.lookup("#txtPassword");
					ComboBox comPub = (ComboBox) ap.lookup("#comboPublic");
					DatePicker dpExitDate = (DatePicker) ap.lookup("#dateExit");
					TextArea contents = (TextArea) ap.lookup("#txtContent");
					
					InputBoardVO vo = new InputBoardVO();
					vo.setTitle(txtTitle.getText());
					vo.setPasswd(txtPasswd.getText());
					vo.setPublicity(comPub.getValue().toString()); //안되면 확인
					vo.setExitDate(dpExitDate.getValue().toString());
					vo.setContents(contents.getText());
					
					InputDAO.InsertBoard(vo);
					tableView.setItems(InputDAO.boardList());
				}
				
			});
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void beforeBtnAction(ActionEvent e) {
		BoardVO vo = new BoardVO();
		vo.setBoardNo(Integer.parseInt(boardNo.getText()));
		InputDAO.beforeBoard(vo);
		
		tableView.setItems(InputDAO.boardList());
	}
}
