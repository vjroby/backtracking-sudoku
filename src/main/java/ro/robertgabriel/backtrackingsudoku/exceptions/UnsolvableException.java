package ro.robertgabriel.backtrackingsudoku.exceptions;

public class UnsolvableException extends RuntimeException {
    public UnsolvableException(String message) {
        super(message);
    }
}
