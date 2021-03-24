package myapp.chartControl;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

public class RootController implements Initializable {
	@FXML PieChart pieChart;
	@FXML BarChart<String, Integer> barChart; //<카테고리타입, 밸류타입>
	@FXML AreaChart<String, Integer> areaChart;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<Data> pieList = FXCollections.observableArrayList();
		pieList.add(new PieChart.Data("Java", 30));
		pieList.add(new PieChart.Data("HTML", 25));
		pieList.add(new PieChart.Data("JavaScript", 45));
		
		pieChart.setData(pieList); //pieChart.setData의 매개값이 ObservableList타입이라서 이 타입으로 위에 선언함.
		
		// XYCjar << Series1, Series2, Series3...
		// Series1 << Data1, Data2, Data3 
		// Series2 << Data1, Data2, Data3
		XYChart.Series<String, Integer> series1 = new XYChart.Series<String, Integer>();
		ObservableList<XYChart.Data<String, Integer>> data1 = FXCollections.observableArrayList();
		data1.add(new XYChart.Data<String, Integer>("천서진", 30));
		data1.add(new XYChart.Data<String, Integer>("심수련", 20));
		data1.add(new XYChart.Data<String, Integer>("오윤희", 35));
		series1.setData(data1);
		series1.setName("국어");
		
		XYChart.Series<String, Integer> series2 = new XYChart.Series<String, Integer>();
		ObservableList<XYChart.Data<String, Integer>> data2 = FXCollections.observableArrayList();
		data2.add(new XYChart.Data<String, Integer>("천서진", 35));
		data2.add(new XYChart.Data<String, Integer>("심수련", 22));
		data2.add(new XYChart.Data<String, Integer>("오윤희", 45));
		series2.setData(data2);
		series2.setName("영어");
		
		XYChart.Series<String, Integer> series3 = new XYChart.Series<String, Integer>();
		ObservableList<XYChart.Data<String, Integer>> data3 = FXCollections.observableArrayList();
		data3.add(new XYChart.Data<String, Integer>("천서진", 45));
		data3.add(new XYChart.Data<String, Integer>("심수련", 30));
		data3.add(new XYChart.Data<String, Integer>("오윤희", 50));
		series3.setData(data3);
		series3.setName("수학");
		
		barChart.getData().add(series1);
		barChart.getData().add(series2);
		barChart.getData().add(series3);
		
		
//		barChart.setData(barList);
	}

}
