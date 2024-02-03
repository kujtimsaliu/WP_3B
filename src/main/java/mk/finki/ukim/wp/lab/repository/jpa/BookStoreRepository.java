package mk.finki.ukim.wp.lab.repository.jpa;

import mk.finki.ukim.wp.lab.model.BookStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookStoreRepository extends JpaRepository<BookStore, Long> {
    void removeBookStoreById(Long id);
}
