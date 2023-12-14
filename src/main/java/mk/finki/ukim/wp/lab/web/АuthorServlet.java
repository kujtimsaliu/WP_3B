//package mk.finki.ukim.wp.lab.web;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.finki.ukim.wp.lab.model.Author;
//import mk.finki.ukim.wp.lab.model.Book;
//import mk.finki.ukim.wp.lab.repository.impl.InMemoryAuthorRepository;
//import mk.finki.ukim.wp.lab.service.AuthorService;
//import mk.finki.ukim.wp.lab.service.BookService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet(name="author-servlet", urlPatterns = "/servlet-author")
//public class АuthorServlet extends HttpServlet {
//
//    private final AuthorService authorService;
//    private final BookService bookService;
//    private final SpringTemplateEngine springTemplateEngine;
//
//
//
//    public АuthorServlet(InMemoryAuthorRepository inMemoryAuthorRepository, AuthorService authorService, SpringTemplateEngine springTemplateEngine, BookService bookService) {
//        this.authorService = authorService;
//        this.springTemplateEngine = springTemplateEngine;
//        this.bookService = bookService;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(req.getServletContext())
//                .buildExchange(req,resp);
//        WebContext context = new WebContext(webExchange);
//
//        String isbnFromParam = req.getParameter("isbn");
//        List<Author> authorList = authorService.listAuthors();
//        Book b = bookService.findBookByIsbn(isbnFromParam);
//
//        context.setVariable("authors", authorList);
//        context.setVariable("isbn", isbnFromParam);
//        context.setVariable("selectedBook", b);
//
//        springTemplateEngine.process("authorList.html", context, resp.getWriter());
//    }
//
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(req.getServletContext())
//                .buildExchange(req,resp);
//        WebContext context = new WebContext(webExchange);
//
//        Long authorId = Long.valueOf(req.getParameter("authorId"));
//
//
//
////        Author byId = new Author(4L, "Name", "surname", "b...");
//
//
//        String isbnFromParam = req.getParameter("isbn");
//
//        bookService.addAuthorToBook(authorId, isbnFromParam);
//
////        String isbnFromParam = req.getParameter("isbn");
////
////        if (isbnFromParam != null) {
////            // Do something with the ISBN value
////            resp.getWriter().write("ISBN from the URL: " + isbnFromParam);
////        } else {
////            resp.getWriter().write("ISBN parameter is missing in the URL.");
////        }
//
//        resp.sendRedirect("/bookDetails?isbn="+isbnFromParam);
//    }
//}
