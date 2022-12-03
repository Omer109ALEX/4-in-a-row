
import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Main13q2Controller {
	
	private Logic4InRow game4InRow = new Logic4InRow();
	// help to know what color to draw
	private boolean redTurn = true;
	private double circleRadius = 25.0;
	
	private void pressNumberButton(int i) {
		int row = game4InRow.addCircle(i, redTurn);
		if (redTurn) {    		
    		if (row >= 0)
    			gameGrid.add(new Circle(circleRadius, Color.RED), i, game4InRow.NUM_OF_ROWS - (1 + row));
    	}
    	else {
    		if (row >= 0)
    			gameGrid.add(new Circle(circleRadius, Color.BLUE), i, game4InRow.NUM_OF_ROWS - (1 + row));
    	}
		
		redTurn = !redTurn;	
		if (game4InRow.getWinner() != "")
			this.winnerAnnounce(game4InRow.getWinner());
			
	}
	
	private void winnerAnnounce(String winner) {	
		if (winner == "RED")
			JOptionPane.showMessageDialog(null, "RED WIN!");
		if (winner == "BLUE")
			JOptionPane.showMessageDialog(null, "BLUE WIN!");
	}

    @FXML
    private GridPane gameGrid;

    @FXML
    void clearPressed(ActionEvent event) {
    	int numberOfButtons = game4InRow.NUM_OF_COLUMNS + 1;
		game4InRow.newGame();
		redTurn = true;
		//not remove the buttons (num of button = num of columns + 1 for clear button)
		gameGrid.getChildren().remove(numberOfButtons, gameGrid.getChildren().size());
    }
    
    @FXML
    void pressed1(ActionEvent event) {
    	this.pressNumberButton(0); 	
    }

    @FXML
    void pressed2(ActionEvent event) {
    	this.pressNumberButton(1);
    }

    @FXML
    void pressed3(ActionEvent event) {
    	this.pressNumberButton(2);
    }

    @FXML
    void pressed4(ActionEvent event) {
    	this.pressNumberButton(3);
    }

    @FXML
    void pressed5(ActionEvent event) {
    	this.pressNumberButton(4);
    }

    @FXML
    void pressed6(ActionEvent event) {
    	this.pressNumberButton(5);
    }

    @FXML
    void pressed7(ActionEvent event) {
    	this.pressNumberButton(6);
    }
}



