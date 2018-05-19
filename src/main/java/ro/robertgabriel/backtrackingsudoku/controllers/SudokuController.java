package ro.robertgabriel.backtrackingsudoku.controllers;

import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ro.robertgabriel.backtrackingsudoku.algo.SolveBacktracking;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Slf4j
@FXMLController
public class SudokuController {

    private static final int ROWS = 9;

    @FXML
    private GridPane grid;

    private final SolveBacktracking solveBacktracking;

    private final DemoData demoData;

    private List<TextField> textCells = new ArrayList<>();

    @FXML
    private Button solve;

    @FXML
    private Button demo;

    @Autowired
    public SudokuController(SolveBacktracking solveBacktracking, DemoData demoData) {
        this.solveBacktracking = solveBacktracking;
        this.demoData = demoData;
    }

    @FXML
    void initialize() {
        addInput();
    }

    public void onPressSolve() {
        log.debug("Grid: values:");
        StringBuilder sb = new StringBuilder();
        Integer[][] grid = new Integer[ROWS][ROWS];
        int i = 0, j = 0;
        for (TextField tf :
                textCells) {
            sb.append("{").append(tf.getText()).append("}");
            grid[j][i] = tf.getText().length() == 0 ? null: Integer.valueOf(tf.getText());
            if (j == 8) {
                i++;
                j = 0;
            } else {
                j++;
            }
        }
        solveBacktracking.setPrintFlag(true);
        Integer[][] solved = solveBacktracking.solve(grid);

        fillTextCell(solved);
        log.debug(sb.toString());
    }

    private void fillTextCell(Integer[][] solved) {
        Iterator<TextField> iteratorCells = textCells.iterator();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < ROWS; j++) {
                if(iteratorCells.hasNext()){
                    String value = solved[j][i] == null? "" : String.valueOf(solved[j][i]);
                    iteratorCells.next().setText(value);
                }
            }
        }
    }

    public void onDemoPress() {
        fillTextCell(demoData.getValues());
    }

    private void addInput() {

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < ROWS; j++) {
                TextField tf = new TextField();
                grid.add(tf, i, j);
                textCells.add(tf);
            }
        }
    }
}
