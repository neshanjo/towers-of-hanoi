package com.ggr.controller;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameOverController {
	@FXML
	private ImageView gameOverImage;

	@FXML
	private Label movesLabel;

	private long moveCounter;

	public GameOverController(long moveCounter) {
		this.moveCounter = moveCounter;
	}

	@FXML
	private void initialize() {
		// Load image
		File imageFile = new File("res/youWin.jpg");
		Image image = new Image(imageFile.toURI().toString());
		gameOverImage.setImage(image);
		// Load count
		movesLabel.setText(String.valueOf(moveCounter));
	}

}