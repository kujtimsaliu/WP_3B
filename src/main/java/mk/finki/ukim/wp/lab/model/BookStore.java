package mk.finki.ukim.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class BookStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="bookstore_name")
    private String name;

    private String city;

    private String address;

    @ManyToMany(mappedBy = "bookStores" )
    private List<Book> books;

    public BookStore(String name, String city, String address) {
//        this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.city = city;
        this.address = address;
        books=new ArrayList<>();
    }

    public BookStore() {}

    public void addBook(Book book) {
        this.books.add(book);
        book.getBookStores().add(this); // Update the other side of the relationship
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
