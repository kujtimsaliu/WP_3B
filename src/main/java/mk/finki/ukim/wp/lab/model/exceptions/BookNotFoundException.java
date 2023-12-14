package mk.finki.ukim.wp.lab.model.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String message) {
        super(String.format("Book with isbn: %s not found", message));
    }
}
