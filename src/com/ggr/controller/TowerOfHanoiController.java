package com.ggr.controller;

import java.util.ArrayList;

import com.ggr.MainApp;
import com.ggr.model.Ring;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TowerOfHanoiController {
	@FXML
	private TextField sizeInput;

	@FXML
	private Label movesLabel;

	@FXML
	private Canvas canvasLeft;

	@FXML
	private Canvas canvasMid;

	@FXML
	private Canvas canvasRight;

	private long moveCounter = 0;
	private int amountOfRings = 0;
	private GraphicsContext gcLeft;
	private GraphicsContext gcMid;
	private GraphicsContext gcRight;
	private ArrayList<Ring> startSituation = new ArrayList<Ring>();
	private ArrayList<Ring> leftTower = new ArrayList<Ring>();
	private ArrayList<Ring> midTower = new ArrayList<Ring>();
	private ArrayList<Ring> rightTower = new ArrayList<Ring>();
	private boolean inHand = false;
	private Ring ringInHand = null;

	@FXML
	private void initialize() {
		gcLeft = canvasLeft.getGraphicsContext2D();
		gcMid = canvasMid.getGraphicsContext2D();
		gcRight = canvasRight.getGraphicsContext2D();
		drawBase();
	}

	@FXML
	private void createHandler(ActionEvent event) {
		// Remove everything and reset count
		resetMoveCounter();
		clearAllTowers();
		removeAllTowers();
		// Create and draw rings
		amountOfRings = Integer.valueOf(sizeInput.getText());
		double ringHeight = 500 / amountOfRings;
		double widthOffset = 300 / amountOfRings;
		double leftOffset = 0;
		double width = 300;
		for (int i = 0; i != amountOfRings; i++) {
			Ring ring = new Ring(ringHeight, width, leftOffset, amountOfRings - i);
			leftTower.add(ring);
			width -= widthOffset;
			leftOffset += widthOffset / 2;
			drawRing(ring.getWidth(), ring.getHeight(), ring.getLeftOffset(), ring.getOrder(), "left");
		}
		copyArrayList(startSituation, leftTower);
	}

	@FXML
	private void resetHandler(ActionEvent event) {
		resetMoveCounter();
		resetTowers();
		removeAllTowers();
		drawAllTowers();
	}

	@FXML
	private void leftHandler(MouseEvent event) {
		pickUpOrPlaceRing(leftTower);
		removeAllTowers();
		drawAllTowers();
	}

	@FXML
	private void midHandler(MouseEvent event) {
		pickUpOrPlaceRing(midTower);
		removeAllTowers();
		drawAllTowers();
	}

	@FXML
	private void rightHandler(MouseEvent event) {
		pickUpOrPlaceRing(rightTower);
		removeAllTowers();
		drawAllTowers();
	}

	private void pickUpOrPlaceRing(ArrayList<Ring> tower) {
		if (inHand && (tower.isEmpty() || ringInHand.getWidth() <= tower.get(tower.size() - 1).getWidth())) {
			ringInHand.setOrder(amountOfRings - tower.size());
			tower.add(ringInHand);
			inHand = false;
			increaseMoveCounter();
		} else if (!inHand) {
			ringInHand = tower.get(tower.size() - 1);
			tower.remove(ringInHand);
			inHand = true;
		}
		// Game over
		if (isGameOver()) {
			MainApp.showGameOver(new Stage(), moveCounter);
		}
	}

	@FXML
	private void solveHandler(ActionEvent event) {
		// Even
		if (amountOfRings % 2 == 0) {
			solveEvenTower();
		}
		// Uneven
		else {
			solveUnevenTower();
		}
	}

	// Algorithms
	
	private void solveEvenTower() {
		boolean solved = false;
		while (!solved) {
			if (isLegalMove(leftTower, midTower)) {
				// move a to b
				moveRing(leftTower, midTower);
				increaseMoveCounter();
			} else if (isLegalMove(midTower, leftTower)) {
				// move b to a
				moveRing(midTower, leftTower);
				increaseMoveCounter();
			}

			if (isLegalMove(leftTower, rightTower)) {
				// move a to c
				moveRing(leftTower, rightTower);
				increaseMoveCounter();
			} else if (isLegalMove(rightTower, leftTower)) {
				// move c to a
				moveRing(rightTower, leftTower);
				increaseMoveCounter();
			}

			if (isLegalMove(midTower, rightTower)) {
				// move b to c
				moveRing(midTower, rightTower);
				increaseMoveCounter();
			} else if (isLegalMove(rightTower, midTower)) {
				// move c to b
				moveRing(rightTower, midTower);
				increaseMoveCounter();
			}
			solved = isGameOver();
		}

		removeAllTowers();
		drawAllTowers();
	}

	private void solveUnevenTower() {
		boolean solved = false;
		while (!solved) {
			if (isLegalMove(leftTower, rightTower)) {
				// move a to c
				moveRing(leftTower, rightTower);
				increaseMoveCounter();
			} else if (isLegalMove(rightTower, leftTower)) {
				// move c to a
				moveRing(rightTower, leftTower);
				increaseMoveCounter();
			}

			if (isLegalMove(leftTower, midTower)) {
				// move a to b
				moveRing(leftTower, midTower);
				increaseMoveCounter();
			} else if (isLegalMove(midTower, leftTower)) {
				// move b to a
				moveRing(midTower, leftTower);
				increaseMoveCounter();
			}

			if (isLegalMove(midTower, rightTower)) {
				// move b to c
				moveRing(midTower, rightTower);
				increaseMoveCounter();
			} else if (isLegalMove(rightTower, midTower)) {
				// move c to b
				moveRing(rightTower, midTower);
				increaseMoveCounter();
			}
			solved = isGameOver();
		}
		removeAllTowers();
		drawAllTowers();
	}
	
	// Util Functions

	private boolean isLegalMove(ArrayList<Ring> source, ArrayList<Ring> dest) {
		if (isGameOver()) {
			return false;
		}
		if (source.isEmpty()) {
			return false;
		}
		if (dest.isEmpty() || source.get(source.size() - 1).getWidth() <= dest.get(dest.size() - 1).getWidth()) {
			return true;
		}
		return false;
	}

	private void moveRing(ArrayList<Ring> source, ArrayList<Ring> dest) {
		Ring temp = source.get(source.size() - 1);
		temp.setOrder(amountOfRings - dest.size());
		source.remove(temp);
		dest.add(temp);
	}

	private boolean isGameOver() {
		if (leftTower.isEmpty() && midTower.isEmpty() && rightTower.size() == amountOfRings) {
			return true;
		}
		return false;
	}
	

	private void increaseMoveCounter() {
		movesLabel.setText(String.valueOf(++moveCounter));
	}

	private void resetMoveCounter() {
		moveCounter = 0;
		movesLabel.setText(String.valueOf(moveCounter));
	}

	private void resetTowers() {
		copyArrayList(leftTower, startSituation);
		midTower.clear();
		rightTower.clear();
	}

	private void clearAllTowers() {
		leftTower.clear();
		midTower.clear();
		rightTower.clear();
	}
	
	private void copyArrayList(ArrayList<Ring> dest, ArrayList<Ring> source) {
		dest.clear();
		for (int i = 0; i < source.size(); i++) {
			dest.add(new Ring(source.get(i)));
		}
	}
	
	// Draw and clear Functions
	
	private void drawBase() {
		gcLeft.setFill(Color.BLACK);
		gcLeft.fillRect(0, 505, 300, 10);
		gcMid.setFill(Color.BLACK);
		gcMid.fillRect(0, 505, 300, 10);
		gcRight.setFill(Color.BLACK);
		gcRight.fillRect(0, 505, 300, 10);
	}

	private void drawAllTowers() {
		drawTower(leftTower, "left");
		drawTower(midTower, "mid");
		drawTower(rightTower, "right");
	}

	private void drawTower(ArrayList<Ring> rings, String tower) {
		for (Ring ring : rings) {
			drawRing(ring.getWidth(), ring.getHeight(), ring.getLeftOffset(), ring.getOrder(), tower);
		}
	}

	private void drawRing(double width, double height, double offset, int order, String tower) {
		switch (tower) {
		case "left":
			gcLeft.setStroke(Color.RED);
			gcLeft.strokeRect(offset, (order * height - height), width, height);
			break;
		case "mid":
			gcMid.setStroke(Color.ORANGE);
			gcMid.strokeRect(offset, (order * height - height), width, height);
			break;
		case "right":
			gcRight.setStroke(Color.GREEN);
			gcRight.strokeRect(offset, (order * height - height), width, height);
			break;
		default:
			System.out.println("incorrect tower was passed");
			break;
		}
	}

	private void removeAllTowers() {
		removeTower("left");
		removeTower("mid");
		removeTower("right");
	}

	private void removeTower(String tower) {
		switch (tower) {
		case "left":
			gcLeft.clearRect(0, 0, canvasLeft.getWidth(), canvasLeft.getHeight() - 15);
			break;
		case "mid":
			gcMid.clearRect(0, 0, canvasMid.getWidth(), canvasMid.getHeight() - 15);
			break;
		case "right":
			gcRight.clearRect(0, 0, canvasRight.getWidth(), canvasRight.getHeight() - 15);
			break;
		default:
			System.out.println("incorrect tower was passed");
			break;
		}
	}
}
