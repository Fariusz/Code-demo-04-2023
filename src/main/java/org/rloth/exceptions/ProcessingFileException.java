package org.rloth.exceptions;

public class ProcessingFileException extends Exception {
    public ProcessingFileException(String message) {
        super("Error: " + message);
    }
}