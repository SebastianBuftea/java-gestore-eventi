package org.esercise.exceptions;

public class InvalidReservedSeatsException extends IllegalArgumentException{
    public InvalidReservedSeatsException(String s) {
        super(s);
    }
}
