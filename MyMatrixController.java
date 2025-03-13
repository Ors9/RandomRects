package Maman11Q2;

import java.util.ArrayList;
import java.util.Collections;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;

public class MyMatrixController {

	// The size of each cell in the grid.
	private static final int CELL_SIZE = 10;

	@FXML
	private Canvas myCanvas;

	private GraphicsContext gc;

	// Initialize graphics context and draw the grid.
	public void initialize() {
		gc = myCanvas.getGraphicsContext2D();

		// Draw the grid on the canvas.
		gridTheCanvas();
	}

	// Draw grid lines on the canvas.
	private void gridTheCanvas() {

		// Get the current width and height of the canvas.
		double width = myCanvas.getWidth();
		double height = myCanvas.getHeight();

		// Set the stroke color and line width for drawing the grid.
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);

		// Draw vertical lines across the canvas.
		for (double x = 0; x <= width; x += CELL_SIZE) {
			gc.strokeLine(x, 0, x, height);
		}

		// Draw horizontal lines across the canvas.
		for (double y = 0; y <= height; y += CELL_SIZE) {
			gc.strokeLine(0, y, width, y);
		}

	}

	// fill 10% of cells each mouse press.
	@FXML
	void onFillButtonClicked(MouseEvent event) {
		// Clear the entire canvas to remove any previous drawings.
		gc.clearRect(0, 0, myCanvas.getWidth(), myCanvas.getHeight());

		// Redraw the grid lines after clearing.
		gridTheCanvas();

		// Fill exactly 10% of the grid cells with the current fill color.
		fillTenPercentOfGrid();
	}

	// Fill exactly 10% of the grid cells with color randomly.
	private void fillTenPercentOfGrid() {

		// Retrieve the current dimensions of the canvas.
		double width = myCanvas.getWidth();
		double height = myCanvas.getHeight();

		// Calculate the number of rows and columns based on the cell size.
		int rows = (int) (height / CELL_SIZE);
		int cols = (int) (width / CELL_SIZE);

		// Compute the total number of cells in the grid.
		int totalCells = rows * cols;

		// Determine the number of cells to fill (10% of total).
		int cellsToFill = (int) (totalCells * 0.1);

		// Create a list of indices representing each cell.
		ArrayList<Integer> indices = new ArrayList<>();

		for (int i = 0; i < totalCells; i++) {
			indices.add(i);
		}

		// Shuffle the list to ensure random selection of cells.
		Collections.shuffle(indices);

		// Fill the selected cells.
		for (int i = 0; i < cellsToFill; i++) {
			int index = indices.get(i);

			// Convert the index to row and column coordinates.
			int row = index / cols;
			int col = index % cols;

			// Calculate the x and y coordinates of the top-left corner of the cell.
			double x = col * CELL_SIZE;
			double y = row * CELL_SIZE;

			// Fill the cell.
			gc.fillRect(x, y, CELL_SIZE, CELL_SIZE);
		}

	}

}
