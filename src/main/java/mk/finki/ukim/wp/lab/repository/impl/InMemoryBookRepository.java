package mk.finki.ukim.wp.lab.repository.impl;

import mk.finki.ukim.wp.lab.bootstrap.Dataholder;
import mk.finki.ukim.wp.lab.model.Author;
import mk.finki.ukim.wp.lab.model.Book;
import mk.finki.ukim.wp.lab.model.BookStore;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryBookRepository {
    public List<Book> findAll(){return Dataholder.books;}

    public Book findByIsbn(String isbn){
        List<Book> collect = Dataholder.books.stream().filter(b -> b.getIsbn().equals(isbn)).collect(Collectors.toList());
        if(collect.isEmpty() || collect.size()>2){
            return null;
        }
        return collect.get(0);
    }
    public List<Author> getAllAuthors(){
        List<List<Author>> collect = Dataholder.books.stream().map(b -> b.getAuthors()).collect(Collectors.toList());
        List<Author>allAuthors = new ArrayList<>();
        for (List<Author> a : collect) {
            for(Author a1 : a){
                allAuthors.add(a1);
            }
        }
        return allAuthors;
    }
    public Author addAuthorToBook(Author author, Book book){
        Book book1 = Dataholder.books.stream().filter(b -> b == book).findFirst().get();
        book1.getAuthors().add(author);
        return author;
    }
    public List<Book>findByKeyword(String keyword){
        return Dataholder.books.stream().filter(k -> k.getTitle().contains(keyword)).collect(Collectors.toList());
    }

    public void addBook(String title, String isbn, String genre, int year, BookStore bookStore) {
        List<Author> as=new ArrayList<>();
//        as.add(author);

        Optional<Book> first = Dataholder.books.stream().filter(b -> b.getIsbn().equals(isbn)).findFirst();
        if(first.isPresent()) {
            first.get().setTitle(title);
            first.get().setGenre(genre);
            first.get().setYear(year);
//            first.get().getAuthors().add(0, author);
            first.get().setBookStore(bookStore);
            return;
        }

        Book b = new Book(isbn, title, genre, year);
        b.setBookStore(bookStore);
        Dataholder.books.add(b);
    }

}

