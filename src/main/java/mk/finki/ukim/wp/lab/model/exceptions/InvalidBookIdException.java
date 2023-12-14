package mk.finki.ukim.wp.lab.model.exceptions;

public class InvalidBookIdException extends RuntimeException {
    public InvalidBookIdException(){
        super("Invalid Book Id Exception");
    }
}
