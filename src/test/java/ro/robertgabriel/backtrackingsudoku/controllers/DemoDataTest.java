package ro.robertgabriel.backtrackingsudoku.controllers;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.robertgabriel.backtrackingsudoku.algo.SolveBacktracking;
import ro.robertgabriel.backtrackingsudoku.exceptions.UnsolvableException;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class DemoDataTest {

    private DemoData demoData = new DemoData();

    private SolveBacktracking solveBacktracking = new SolveBacktracking();

    @Test
    public void shouldAddGames() {
        assertEquals(0, demoData.getGames().count());

        demoData.init();

        assertTrue( demoData.getGames().count() != 0);
    }

    @Test
    public void shouldSolveAll() {
        demoData.init();
        List<Boolean> results = demoData.getGames()
                .parallel()
                .map(this::isSolved)
                .filter(g -> !g)
                .collect(Collectors.toList());

        assertEquals(0, results.size());
    }

    private boolean isSolved(Integer[][] game) {
        try {
            solveBacktracking.solve(game);
            return true;
        }catch (UnsolvableException e){
            return false;
        }
    }
}