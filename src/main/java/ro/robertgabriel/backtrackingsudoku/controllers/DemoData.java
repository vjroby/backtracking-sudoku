package ro.robertgabriel.backtrackingsudoku.controllers;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DemoData {

    private Random random = new Random();

    private List<Integer[][]> games = new ArrayList<>();

    @PostConstruct
    public void init() {
        addGames();
    }

    public Integer[][] getValues() {
        return games.get(random.nextInt(games.size()));
    }

    private void addGames() {
        games.add(new Integer[][]{
                {5, 3, null, null, 7, null, null, null, null},
                {6, null, null, 1, 9, 5, null, null, null},
                {null, 9, 8, null, null, null, null, 6, null},
                {8, null, null, null, 6, null, null, null, 3},
                {4, null, null, 8, null, 3, null, null, 1},
                {7, null, null, null, 2, null, null, null, 6},
                {null, 6, null, null, null, null, 2, 8, null},
                {null, null, null, 4, 1, 9, null, null, 5},
                {null, null, null, null, 8, null, null, 7, 9}
        });

        games.add(new Integer[][]{
                {null, null, 8, null, null, null, 9, null, null},
                {null, null, null, 6, 8, 3, null, null, null},
                {null, 4, null, 9, null, 1, null, null, 3},
                {null, 8, 4, null, null, null, 6, 3, null},
                {null, 6, null, null, 3, null, null, 1, null},
                {null, 5, 7, null, null, null, 2, 4, null},
                {8, null, null, 3, null, 4, null, 7, null},
                {null, null, null, 2, 1, 5, null, null, null},
                {null, null, 3, null, null, null, 1, null, null}
        });
        games.add(new Integer[][]{
                {7, 9, null, 8, null, 2, null, 6, 5},
                {null, null, 4, null, 9, null, 8, null, null},
                {null, null, null, null, null, null, null, null, null},
                {5, null, null, null, 2, 6, null, null, 3},
                {null, 3, null, null, null, null, null, 5, null},
                {1, null, null, 9, 3, null, null, null, 7},
                {null, null, null, null, null, null, null, null, null},
                {null, null, 6, null, 1, null, 5, null, null},
                {2, 1, null, 7, null, 3, null, 4, 8}
        });
    }
}
