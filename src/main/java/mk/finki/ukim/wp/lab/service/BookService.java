package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Author;
import mk.finki.ukim.wp.lab.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listBooks();
    Author addAuthorToBook(Long authorId, String isbn);
    Book findBookByIsbn(String isbn);
    Optional<Book> findBookById(Long id);
    public List<Book>findByKeyword(String keyword);
    void removeBookById(Long id);

    Optional<Book> edit(Long id,String isbn,String title,String  genre,int year,int price,Long bookstoreId);
    void save(String title,
                    String isbn,
                    String genre,
                    int year,
                    int price,
                    Long bookstoreId);

    Optional<Book>save(Book book);

}
