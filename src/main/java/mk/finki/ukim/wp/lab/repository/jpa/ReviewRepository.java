package mk.finki.ukim.wp.lab.repository.jpa;

import mk.finki.ukim.wp.lab.model.Book;
import mk.finki.ukim.wp.lab.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review>  findByBook(Book book);

    List<Review> findByTimestampBetween(LocalDateTime from, LocalDateTime to);

}
