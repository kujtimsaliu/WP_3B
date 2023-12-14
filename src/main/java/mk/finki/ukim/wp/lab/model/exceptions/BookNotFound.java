package mk.finki.ukim.wp.lab.model.exceptions;

public class BookNotFound extends RuntimeException {
    public BookNotFound(Long id){
        super(String.format("Book with id: %d not found", id));
    }
}
