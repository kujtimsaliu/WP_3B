package mk.finki.ukim.wp.lab.model.exceptions;

public class BookStoreNotFoundException extends RuntimeException {
    public BookStoreNotFoundException(Long id) {
        super(String.format("Book store with id %d not found", id));

    }
}
