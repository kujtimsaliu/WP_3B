package mk.finki.ukim.wp.lab.service.impl;

import jakarta.transaction.Transactional;
import mk.finki.ukim.wp.lab.model.Author;
import mk.finki.ukim.wp.lab.model.Book;
import mk.finki.ukim.wp.lab.model.BookStore;
import mk.finki.ukim.wp.lab.model.exceptions.BookNotFound;
import mk.finki.ukim.wp.lab.model.exceptions.BookNotFoundException;
import mk.finki.ukim.wp.lab.model.exceptions.BookStoreNotFoundException;
import mk.finki.ukim.wp.lab.repository.jpa.AuthorRepository;
import mk.finki.ukim.wp.lab.repository.jpa.BookRepository;
import mk.finki.ukim.wp.lab.repository.jpa.BookStoreRepository;
import mk.finki.ukim.wp.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookStoreRepository bookStoreRepository;
    private final AuthorRepository authorRepository;



    public BookServiceImpl(BookRepository bookRepository, BookStoreRepository bookStoreRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.bookStoreRepository = bookStoreRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Transactional
    public Author addAuthorToBook(Long authorId, String isbn) {
        // Find the author by ID
        Author author = authorRepository.findById(authorId).orElse(null);

        // Find the book by ISBN
        Book book = bookRepository.findByIsbn(isbn).orElse(null);

        // Check if both author and book exist
        if (author != null && book != null) {
            // Add the author to the book
            book.addAuthor(author);

            // Save the book to update the relationship
            bookRepository.save(book);

            return author;
        }
        System.out.println(book + "------" + author);

        return null; // Return null if author or book is not found
    }
    @Override
    public Book findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn).orElseThrow(()->new BookNotFoundException(isbn));
    }

    @Override
    public Optional<Book> findBookById(Long id) {
        Optional<Book> first = bookRepository.findAll().stream()
                .filter(b -> b.getId().equals(id))
                .findFirst();
        return first;
    }

    public List<Book>findByKeyword(String keyword){
        return bookRepository.findAll().stream().filter(k -> k.getTitle().contains(keyword)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void removeBookById(Long id) {
//        bookRepository.findAll().removeIf(b -> b.getId().equals(id));
        Book book = bookRepository.findById(id)
                .orElseThrow(null);
        if(book!=null){
            bookRepository.delete(book);
        }
    }

    @Override
    public Optional<Book> edit(Long id, String isbn, String title, String genre, int year,int price, Long bookstoreId) {

        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFound(id));

        book.setIsbn(isbn);
        book.setTitle(title);
        book.setGenre(genre);
        book.setYear(year);
        book.setPrice(price);


        BookStore bookStore = bookStoreRepository.findById(bookstoreId).orElseThrow(() -> new BookStoreNotFoundException(bookstoreId));

        book.setBookStore(bookStore);

        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public void save(String title, String isbn, String genre, int year,int price, Long bookstoreId) {
        BookStore bookStore = bookStoreRepository.findById(bookstoreId)
                .orElseThrow(() -> new BookNotFound(bookstoreId));
//        Author author = authorRepository.findById(authorId).orElseThrow(() -> new InvalidAuthorIdException());

        Book book = new Book(title,isbn,genre,year,price);
//        book.setBookStore(bookStore);
        this.bookRepository.save(book);
        bookStore.addBook(book);
    }

    @Override
    public Optional<Book> save(Book book) {
        bookStoreRepository.findById(book.getBookStores().get(0).getId())
                .orElseThrow(()->new BookStoreNotFoundException(book.getBookStores().get(0).getId()));
        this.bookRepository.deleteById(book.getId());
        Book savedBook = this.bookRepository.save(new Book(book.getIsbn(), book.getTitle(), book.getGenre(), book.getYear(),book.getPrice()));
//        savedBook.setBookStore(book.getBookStores().get(0));
        return Optional.of(savedBook);
    }

    @Override
    public Book removeAuthors(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFound(id));
        book.getAuthors().clear();
        bookRepository.save(book);
        return book;
    }

}
