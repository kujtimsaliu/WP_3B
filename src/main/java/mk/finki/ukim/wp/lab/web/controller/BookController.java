package mk.finki.ukim.wp.lab.web.controller;

import mk.finki.ukim.wp.lab.model.Author;
import mk.finki.ukim.wp.lab.model.Book;
import mk.finki.ukim.wp.lab.model.BookStore;
import mk.finki.ukim.wp.lab.model.exceptions.BookNotFound;
import mk.finki.ukim.wp.lab.model.exceptions.BookStoreNotFoundException;
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

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final BookStoreService bookStoreService;
    private final AuthorService authorService;


    public BookController(BookService bookService, BookStoreService bookStoreService, AuthorService authorService) {
        this.bookService = bookService;
        this.bookStoreService = bookStoreService;
        this.authorService = authorService;
    }


    @GetMapping()
    public String getBooksPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);

        }
        List<Book> books = bookService.listBooks();
        List<BookStore> bookStores = bookStoreService.findAll();
        model.addAttribute("books", books);
        model.addAttribute("book-store", bookStores);
        return "listBooks";
    }

    @GetMapping("books/buy/{id}")
    public String buyBook(@PathVariable Long id, Model model) {
        List<Book> books = bookService.listBooks();
        List<BookStore> bookStores = bookStoreService.findAll();

        Book book = bookService.findBookById(id).orElseThrow(() -> new BookNotFound(id));
        model.addAttribute("books", books);
        model.addAttribute("book-store", bookStores);
        return "listBooks";
    }



    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        this.bookService.removeBookById(id);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String getEditBookForm(@PathVariable Long id, Model model) {

        if (this.bookService.findBookById(id).isPresent()) {
            List<Author>authors = authorService.listAuthors();
            Book bookById = bookService.findBookById(id).get();
            List<BookStore> bookStores = bookStoreService.findAll();
            model.addAttribute("bookStores", bookStores);
            model.addAttribute("book", bookById);
            model.addAttribute("authors", authors);
            return "add-book";
        }
        return "redirect:/books?error=BookNotFound";
    }

    @GetMapping("/add-form")
    public String addBookPage(Model model) {
        List<BookStore> bookStores = bookStoreService.findAll();
        List<Author>authors = authorService.listAuthors();
        model.addAttribute("bookStores",bookStores);
        model.addAttribute("authors",authors);
        return "add-book";
    }

    @PostMapping("/add")
    public String saveBook(@RequestParam(required = false) Long id,
                           @RequestParam String title,
                           @RequestParam String isbn,
                           @RequestParam String genre,
                           @RequestParam int year,
                            @RequestParam int price,
//                           @RequestParam Long authorId,
                           @RequestParam Long bookstoreId){
        if (id != null)
            this.bookService.edit(id, isbn,title, genre, year,price, bookstoreId);
        else
            this.bookService.save(isbn,title, genre, year,price, bookstoreId);
        return "redirect:/books";
    }
}

