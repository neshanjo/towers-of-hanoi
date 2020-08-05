package com.ggr;

import java.io.IOException;

import com.ggr.controller.GameOverController;
import com.ggr.controller.TowerOfHanoiController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage mainStage) throws Exception {
		showTowerOfHanoi(mainStage);
	}

	public static void showTowerOfHanoi(Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/TowerOfHanoi.fxml"));
			loader.setController(new TowerOfHanoiController());
			Parent layout = loader.load();
			stage.setScene(new Scene(layout));
			stage.setTitle("Tower of Hanoi");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void showGameOver(Stage stage, long moveCounter) {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/GameOver.fxml"));
			loader.setController(new GameOverController(moveCounter));
			Parent layout = loader.load();
			stage.setScene(new Scene(layout));
			stage.setTitle("You Win!");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
