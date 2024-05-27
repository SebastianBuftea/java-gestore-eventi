package org.esercise.exceptions;

public class InvalidTitleException extends IllegalArgumentException{
    public InvalidTitleException(String s) {
        super(s);
    }
}
