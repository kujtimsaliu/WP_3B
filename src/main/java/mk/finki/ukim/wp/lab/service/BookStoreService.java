package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.BookStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface BookStoreService {
    public List<BookStore> findAll();

    public void removeBookById(Long in);

    public void save(String name,
                     String city,
                     String address);

    Optional<BookStore> findById(Long id);

    void removeBookStore(Long id);

    void deleteBookStore(Long id);

    void edit(Long id, String name, String city, String address);
}
