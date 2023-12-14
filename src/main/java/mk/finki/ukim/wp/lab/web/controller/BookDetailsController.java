package mk.finki.ukim.wp.lab.web.controller;


import mk.finki.ukim.wp.lab.model.Author;
import mk.finki.ukim.wp.lab.model.Book;
import mk.finki.ukim.wp.lab.model.Review;
import mk.finki.ukim.wp.lab.model.exceptions.BookNotFound;
import mk.finki.ukim.wp.lab.service.AuthorService;
import mk.finki.ukim.wp.lab.service.BookService;
import mk.finki.ukim.wp.lab.service.BookStoreService;
import mk.finki.ukim.wp.lab.service.ReviewService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/book-details")
public class BookDetailsController {

    private final BookService bookService;
    private final ReviewService reviewService;
    private final BookStoreService bookStoreService;
    private final AuthorService authorService;

    public BookDetailsController(BookService bookService, ReviewService reviewService, BookStoreService bookStoreService, AuthorService authorService) {
        this.bookService = bookService;
        this.reviewService = reviewService;
        this.bookStoreService = bookStoreService;
        this.authorService = authorService;
    }

    @GetMapping("/book/{id}")
    public String currentBookPage(@PathVariable Long id, Model model){
        Book bookById = bookService.findBookById(id).orElseThrow(() -> new BookNotFound(id));
        List<Review> reviewsByBook = reviewService.findReviewsByBook(bookById);
        List<Author> authors = bookById.getAuthors();

        model.addAttribute("reviewsForBook", reviewsByBook);
        model.addAttribute("selectedBook", bookById);
        model.addAttribute("authors", authors);
        return "bookDetails";
    }
    @PostMapping("/book/{id}")
    public String filterReviewsByTimeInterval(@PathVariable Long id,
                                              @RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime fromDate,
                                              @RequestParam("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime toDate,
                                              Model model) {
        Book bookById = bookService.findBookById(id).orElse(null);
        model.addAttribute("selectedBook", bookById);

        // In the controller
        System.out.println("From Date: " + fromDate);
        System.out.println("To Date: " + toDate);

        if (bookById != null) {
            List<Review> reviewsForBook = reviewService.findReviewsByBook(bookById);
            model.addAttribute("reviewsForBook", reviewsForBook);
            model.addAttribute("filtered", true);
            // Filter reviews based on the time interval
            List<Review> filteredReviews = reviewService.findReviewsInTimeInterval(fromDate, toDate);
            model.addAttribute("filteredReviews", filteredReviews);

            System.out.println("Reviews before filter: " + reviewsForBook.size());
            System.out.println("Filtered Reviews: " + filteredReviews.size());
        }

        return "bookDetails";
    }
}
