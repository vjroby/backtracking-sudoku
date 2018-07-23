package ro.robertgabriel.backtrackingsudoku.controllers;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class GridObserver {

    private final TriConsumer<Optional<Integer>, Integer, Integer> consumer;

    public GridObserver(TriConsumer<Optional<Integer>, Integer, Integer> consumer) {
        this.consumer = consumer;
    }

    public void onUpdate(Integer value, int i, int j) {
         consumer.accept(Optional.ofNullable(value), i, j);
    }
}
