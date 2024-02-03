package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.model.Book;
import mk.finki.ukim.wp.lab.model.Review;
import mk.finki.ukim.wp.lab.repository.jpa.BookRepository;
import mk.finki.ukim.wp.lab.repository.jpa.ReviewRepository;
import mk.finki.ukim.wp.lab.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, BookRepository bookRepository) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void save(Integer score, String description, Long bookId, LocalDateTime timestamp) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

        Review review = new Review(score, description, book, timestamp);
//        review.setScore(score);
//        review.setDescription(description);
//        review.setBook(book);
//        review.setTimestamp(timestamp);

        reviewRepository.save(review);
    }

    @Override
    public List<Review> findReviewsByBook(Book book) {
       return reviewRepository.findByBook(book);
    }


    public List<Review> findReviewsInTimeInterval(LocalDateTime from, LocalDateTime to) {
        return reviewRepository.findByTimestampBetween(from, to);
    }
}
