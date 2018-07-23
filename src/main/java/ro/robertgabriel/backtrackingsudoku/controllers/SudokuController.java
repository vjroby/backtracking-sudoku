package ro.robertgabriel.backtrackingsudoku.controllers;

import de.felixroske.jfxsupport.FXMLController;
import javafx.application.Platform;
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
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;


@Slf4j
@FXMLController
public class SudokuController {

    private static final int ROWS = 9;

    @FXML
    private GridPane grid;

    private final SolveBacktracking solveBacktracking;

    private final DemoData demoData;

    private List<TextField> textCells = new ArrayList<>();
    private ExecutorService executors = Executors.newFixedThreadPool(1);
    private AtomicLong iterations = new AtomicLong();

    @FXML
    private Button solve;

    @FXML
    private Button solveDetailed;

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
        solveBacktracking.setGridObserver(new GridObserver(this::updateVoid));
        log.debug("Grid: values:");
        Integer[][] grid = extractGridFromFields();
        solveBacktracking.setPrintFlag(true);
        executors.submit(() -> {
            Integer[][] solved = solveBacktracking.solve(grid);
            fillTextCell(solved);
            log.info("Iterations: " + iterations.get());
            iterations.set(0);
        });

    }

    public void onPressSolveDetailed() {
        solveBacktracking.setGridObserver(new GridObserver(this::updateTextGrid));
        log.debug("Grid: values:");
        Integer[][] grid = extractGridFromFields();
        solveBacktracking.setPrintFlag(false);
        executors.submit(() -> {
            solveBacktracking.solve(grid);
            log.info("Iterations: " + iterations.get());
            iterations.set(0);
        });

    }

    private Integer[][] extractGridFromFields() {
        StringBuilder sb = new StringBuilder();
        Integer[][] grid = new Integer[ROWS][ROWS];
        int i = 0, j = 0;
        for (TextField tf :
                textCells) {
            sb.append("{").append(tf.getText()).append("}");

            grid[j][i] = tf.getText().length() == 0 ? null : Integer.valueOf(tf.getText());
            if (j == 8) {
                i++;
                j = 0;
            } else {
                j++;
            }
        }
        log.debug(sb.toString());
        return grid;
    }

    private void fillTextCell(Integer[][] solved) {
        Iterator<TextField> iteratorCells = textCells.iterator();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < ROWS; j++) {
                if (iteratorCells.hasNext()) {
                    String value = solved[j][i] == null ? "" : String.valueOf(solved[j][i]);
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
                String borders = createTextFieldBorders(i, j);
                tf.setStyle("-fx-border-color: black;-fx-border-width:" + borders);
                grid.add(tf, i, j);
                textCells.add(tf);
            }
        }
    }

    private String createTextFieldBorders(int i, int j) {
        String[] borders = {"0", "0", "0", "0"};
        if (i % 3 == 0) {
            borders[3] = "1";
        }
        if (i == ROWS - 1) {
            borders[1] = "1";
        }
        if (j % 3 == 0) {
            borders[0] = "1";
        }
        if (j == ROWS - 1) {
            borders[2] = "1";
        }
        return String.join(" ", borders);
    }

    private void updateTextGrid(Optional<Integer> value, Integer i, Integer j) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Platform.runLater(() -> {
            String valueString = "";
            if (value.isPresent()) {
                valueString = String.valueOf(value.get());
            }
            String number = String.valueOf(j) + String.valueOf(i);
            Integer index = Integer.valueOf(number) - j;
            textCells.get(index).setText(String.valueOf(valueString));
            iterations.getAndIncrement();
        });
    }

    private void updateVoid(Optional<Integer> value, Integer i, Integer j) {
        iterations.getAndIncrement();
    }
}
