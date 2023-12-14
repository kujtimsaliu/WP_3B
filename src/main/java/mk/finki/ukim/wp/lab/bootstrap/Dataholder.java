package mk.finki.ukim.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import mk.finki.ukim.wp.lab.model.Author;
import mk.finki.ukim.wp.lab.model.Book;
import mk.finki.ukim.wp.lab.model.BookStore;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class Dataholder {

    public static List<Author> authors = new ArrayList<>();
    public static List<Book> books = new ArrayList<>();
    public static List<BookStore> bookStores=new ArrayList<>();

    @PostConstruct
    public void init(){

//        BookStore bs1= new BookStore("Literatura1", "Skopje", "Lib address");
//        BookStore bs2= new BookStore("Literatura2", "Tetovo", "Lib address");
//        BookStore bs3= new BookStore("Literatura3", "Struga", "Lib address");
//        BookStore bs4= new BookStore("Literatura4", "Ohrid", "Lib address");
//        BookStore bs5= new BookStore("Literatura5", "Veles", "Lib address");
//        bookStores.add(bs1);
//        bookStores.add(bs2);
//        bookStores.add(bs3);
//        bookStores.add(bs4);
//        bookStores.add(bs5);
//
//
//        Author a1 = new Author("John", "Doe", "John Doe is a prolific writer known for his mystery novels.", LocalDate.now());
//        Author a2 = new Author("Jane", "Smith", "Jane Smith is a renowned poet and has received numerous awards for her work.", LocalDate.now());
//        Author a3 = new Author("Mark", "Johnson", "Mark Johnson is a science fiction author, famous for his space exploration series.", LocalDate.now());
//        Author a4 = new Author("Emily", "Brown", "Emily Brown is a children's book author, loved by kids and parents alike.", LocalDate.now());
//        Author a5 = new Author("David", "Wilson", "David Wilson is a historian and has written extensively on ancient civilizations.", LocalDate.now());
//        authors.add(a1);
//        authors.add(a2);
//        authors.add(a3);
//        authors.add(a4);
//        authors.add(a5);
//
//        List<Author> book1Authors = new ArrayList<>();
//        book1Authors.add(a1);
//        Book book1 = new Book("ISBN123456", "The Mystery of the Hidden Clues", "Mystery", 2020);
//        book1.getAuthors().add(a1);
//        book1.setBookStore(bookStores.get(0));
//
//        List<Author> book2Authors = new ArrayList<>();
//        book2Authors.add(a2);
//        book2Authors.add(a3);
//        Book book2 = new Book("ISBN789012", "Poems of Serenity", "Poetry", 2019);
//        book2.getAuthors().add(a2);
//
//        book2.setBookStore(bookStores.get(1));
//
//        List<Author> book3Authors = new ArrayList<>();
//        book3Authors.add(a3);
//        Book book3 = new Book("ISBN345678", "Explorers of the Cosmos", "Science Fiction", 2022);
//        book3.getAuthors().add(a3);
//        book3.setBookStore(bookStores.get(3));
//
//        List<Author> book4Authors = new ArrayList<>();
//        book4Authors.add(a4);
//        book4Authors.add(a5);
//        Book book4 = new Book("ISBN901234", "The Adventures of Teddy Bear", "Children's", 2018);
//        book4.getAuthors().add(a4);
//        book4.setBookStore(bookStores.get(2));
//
//        List<Author> book5Authors = new ArrayList<>();
//        book5Authors.add(a5);
//        Book book5 = new Book("ISBN567890", "Ancient Civilizations: Unearthed Mysteries", "History", 2021);
//        book5.getAuthors().add(a5);
//        book5.setBookStore(bookStores.get(4));
//
//        books.add(book1);
//        books.add(book2);
//        books.add(book3);
//        books.add(book4);
//        books.add(book5);

    }
}
