package mk.finki.ukim.wp.lab.service.impl;

import jakarta.transaction.Transactional;
import mk.finki.ukim.wp.lab.model.BookStore;
import mk.finki.ukim.wp.lab.model.exceptions.BookStoreNotFoundException;
import mk.finki.ukim.wp.lab.repository.impl.InBookStoreRepository;
import mk.finki.ukim.wp.lab.repository.jpa.BookStoreRepository;
import mk.finki.ukim.wp.lab.service.BookStoreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookStoreServiceImpl implements BookStoreService {

    private final BookStoreRepository bookStoreRepository;

    public BookStoreServiceImpl(BookStoreRepository bookStoreRepository) {
        this.bookStoreRepository = bookStoreRepository;
    }

    @Override
    public List<BookStore> findAll() {
        return bookStoreRepository.findAll();
    }

    @Override
    public void removeBookById(Long id) {
        bookStoreRepository.removeBookStoreById(id);
    }

    public void save(String name,
                     String city,
                     String address){
        BookStore bs = new BookStore(name, city, address);
        bookStoreRepository.save(bs);
    }

    @Override
    public Optional<BookStore> findById(Long id) {
        return Optional.of(bookStoreRepository.findById(id).orElseThrow(()->new BookStoreNotFoundException(id)));
    }

    @Override
    public void removeBookStore(Long id) {
        BookStore bookStore = bookStoreRepository.findById(id).orElseThrow(() -> new BookStoreNotFoundException(id));
        bookStoreRepository.delete(bookStore);
    }

    @Override
    @Transactional
    public void deleteBookStore(Long id) {
        BookStore bookStore = bookStoreRepository.findById(id).orElse(null);
        if(bookStore!=null){
            bookStoreRepository.delete(bookStore);
        }

    }

    @Override
    public void edit(Long id, String name, String city, String address) {
        BookStore bookStore = bookStoreRepository.findById(id).orElse(null);
        if(bookStore!=null){
            bookStore.setAddress(address);
            bookStore.setName(name);
            bookStore.setCity(city);
        }
        bookStoreRepository.save(bookStore);
    }
}
