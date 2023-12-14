package mk.finki.ukim.wp.lab.web.controller;

import mk.finki.ukim.wp.lab.model.Book;
import mk.finki.ukim.wp.lab.service.BookService;
import mk.finki.ukim.wp.lab.service.ReviewService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    private final BookService bookService;
    private final ReviewService reviewService;

    public ReviewController(BookService bookService, ReviewService reviewService) {
        this.bookService = bookService;
        this.reviewService = reviewService;
    }


    @GetMapping("/add-review/{id}")
    public String showReviewForm(@PathVariable Long id, Model model) {
        // Assuming you have a service method to get the book by id
        Book book = bookService.findBookById(id).get();

        // Add the book and other necessary data to the model
        model.addAttribute("book", book);
        model.addAttribute("bookId", id);

        return "review";
    }
    @PostMapping("/save")
    public String saveReview(@RequestParam("score") Integer score,
                             @RequestParam("description") String description,
                             @RequestParam("bookId") Long bookId,
                             @RequestParam("timestamp") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime timestamp) {
        // Process the review data and save to the database

        reviewService.save(score, description, bookId, timestamp);
        return "redirect:/books"; // Redirect to the desired page after saving the review
    }
}