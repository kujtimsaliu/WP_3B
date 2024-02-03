package mk.finki.ukim.wp.lab.web.controller;

import mk.finki.ukim.wp.lab.model.Author;
import mk.finki.ukim.wp.lab.model.Book;
import mk.finki.ukim.wp.lab.model.exceptions.BookNotFound;
import mk.finki.ukim.wp.lab.service.AuthorService;
import mk.finki.ukim.wp.lab.service.BookService;
import mk.finki.ukim.wp.lab.service.BookStoreService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Controller
@RequestMapping("/author")
public class AuthorController {
    private final BookService bookService;
    private final BookStoreService bookStoreService;
    private final AuthorService authorService;

    public AuthorController(BookService bookService, BookStoreService bookStoreService, AuthorService authorService) {
        this.bookService = bookService;
        this.bookStoreService = bookStoreService;
        this.authorService = authorService;
    }

    @GetMapping("/add-author/{id}")
    public String addAuthorPage(@RequestParam(required = false)String error,
                                @PathVariable Long id,
                                Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Author> authors = authorService.listAuthors();
        model.addAttribute("authors", authors);
        model.addAttribute("sb", id);
        return "authorList";
    }

    @PostMapping("/add")
    public String addAuthor(@RequestParam Long bookId,
                            @RequestParam Long existingAuthor) {
        Book book = bookService.findBookById(bookId).orElse(null);

        if (book == null) {
            System.out.println("Book==================================null");
//            return "redirect:/books";
        }
        Author selectedAuthor = authorService.findById(existingAuthor);
        Optional<Author> first = book.getAuthors().stream().filter(a -> a.getId().equals(selectedAuthor.getId())).findFirst();
        System.out.println(first + "+=+=+="+ selectedAuthor + "----" + book.getAuthors().toString());
        if (first.isPresent()) {
            return "redirect:/author/add-author/" + bookId + "?error=AuthorExistsAlready";
        } else {
            this.bookService.addAuthorToBook(existingAuthor, book.getIsbn());
            book.getAuthors().add(selectedAuthor);
            return "redirect:/book-details/book/" + bookId;
        }
    }

    @GetMapping("/remove-authors/{id}")
    public String removeAuthors(@PathVariable Long id) {
//        Optional<Book> optionalBook = bookService.findBookById(id);
//        Book book1 = bookService.findBookById(id).orElseThrow(() -> new BookNotFound(id));

//        if (optionalBook.isPresent()) {
//            Book book = optionalBook.get();
//            book.getAuthors().clear();
//            bookService.save(book);  // Assuming you have a method to save the book changes
//        }
        bookService.removeAuthors(id);

        return "redirect:/book-details/book/" + id;
    }

    @GetMapping("/create-author/{id}")
    public String getCreateAuthorPage(@PathVariable(required = false) Long id, Model model) {
        System.out.println("+=+=" + id);
        model.addAttribute("sb", id);
        return "add-author";
    }
    @PostMapping("/add-author")
    public String saveAuthor(@RequestParam(required = false) Long bookId,
                             @RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam String biography,
                             @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dateOfBirth) {

        System.out.println("Book ID received: " + bookId);
        this.authorService.save(name, surname, biography, dateOfBirth);

        return "redirect:/author/add-author/" + bookId;
    }


}
