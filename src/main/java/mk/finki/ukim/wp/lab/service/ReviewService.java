package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Book;
import mk.finki.ukim.wp.lab.model.Review;

import java.time.LocalDateTime;
import java.util.List;

public interface ReviewService {
    void save(Integer score, String description, Long bookId, LocalDateTime timestamp);

    List<Review> findReviewsByBook(Book bookById);
    public List<Review> findReviewsInTimeInterval(LocalDateTime from, LocalDateTime to) ;

}
