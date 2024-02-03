package mk.finki.ukim.wp.lab.web.controller;


import mk.finki.ukim.wp.lab.model.Book;
import mk.finki.ukim.wp.lab.model.BookStore;
import mk.finki.ukim.wp.lab.repository.impl.InBookStoreRepository;
import mk.finki.ukim.wp.lab.repository.impl.InMemoryAuthorRepository;
import mk.finki.ukim.wp.lab.repository.impl.InMemoryBookRepository;
import mk.finki.ukim.wp.lab.service.AuthorService;
import mk.finki.ukim.wp.lab.service.BookService;
import mk.finki.ukim.wp.lab.service.BookStoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/bookstore")
public class BookStoreController {

    private final BookStoreService bookStoreService;
    private final BookService bookService;
    private final AuthorService authorService;


    public BookStoreController(BookStoreService bookStoreService, BookService bookService, AuthorService authorService) {
        this.bookStoreService = bookStoreService;
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping()
    public String getBookStorePage(Model model) {
        List<BookStore> all1 = bookStoreService.findAll();
        model.addAttribute("bookstore", all1);

        return "listBookStore";
    }

    @GetMapping("/details/{id}")
    public String bookStoreDetails(@PathVariable Long id, Model model){
        BookStore bookStore = bookStoreService.findById(id).get();
//        BookStore bookStore = bookStoreRepository.findById(id).get();
        model.addAttribute("selectedBookStore", bookStore);
        return "bookStoreDetails";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        this.bookStoreService.deleteBookStore(id);


        return "redirect:/bookstore";
    }



    @GetMapping("/edit/{id}")
    public String getEditBookForm(@PathVariable Long id, Model model) {
        if (this.bookStoreService.findById(id).isPresent()) {
           BookStore bookStore = bookStoreService.findById(id).get();
            model.addAttribute("bookStore", bookStore);
            return "add-bookstore";
        }
        return "redirect:/books?error=BookNotFound";
    }

    @GetMapping("/add-bookstore-form")
    public String addBookPage(Model model) {

        return "add-bookstore";
    }

    @PostMapping("/add")
    public String saveBook(@RequestParam(required = false) Long id,
                           @RequestParam String name,
                           @RequestParam String city,
                           @RequestParam String address){
//        this.bookService.save(title, isbn, genre, year, bookstoreId);
//        Optional<BookStore> first = bookStoreService.findAll()
//                .stream().filter(bs -> bs.getId().equals(id)).findFirst();
//        if(first.isPresent()){
//            first.get().setName(name);
//            first.get().setCity(city);
//            first.get().setAddress(address);
//            return "redirect:/bookstore";
//        }
        if(id!=null)
            bookStoreService.edit(id,name,city,address);
        else
            this.bookStoreService.save(name,city,address);
        return "redirect:/bookstore";
    }
//



}
