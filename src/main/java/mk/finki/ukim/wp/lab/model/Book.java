package mk.finki.ukim.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isbn;

    private String title;

    private String genre;

    private int year;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Author> authors;


    //TODO changed to list from singe bookstore
    @ManyToMany
    @JoinTable(name = "book-bookStore",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "bookstore_id"))
    private List<BookStore> bookStores;


    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private List<Review> reviews;

    public Book(String isbn, String title, String genre, int year) {
//        this.id = (long) (Math.random() * 1000);
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.authors = new ArrayList<>();
        bookStores=new ArrayList<>();
        reviews=new ArrayList<>();
    }


    public Book() {}
    public void addAuthor(Author author){
        authors.add(author);
    }

    public void addReview(Review review){
        reviews.add(review);
    }
    public void setBookStore(BookStore bookStore) {
        this.bookStores.set(0, bookStore);
        bookStore.getBooks().add(this); // Make sure to update the other side of the relationship
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Author> getAuthorsfromBookClass() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<BookStore> getBookStores() {
        return bookStores;
    }

    public void setBookStores(List<BookStore> bookStores) {
        this.bookStores = bookStores;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
