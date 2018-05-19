package ro.robertgabriel.backtrackingsudoku;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ro.robertgabriel.backtrackingsudoku.views.SudokuView;


@SpringBootApplication
public class SudokuApplication extends AbstractJavaFxApplicationSupport {

    public static void main(String[] args) {
        launchApp(SudokuApplication.class, SudokuView.class, args);
    }
}
