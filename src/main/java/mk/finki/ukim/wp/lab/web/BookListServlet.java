//package mk.finki.ukim.wp.lab.web;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.finki.ukim.wp.lab.model.Book;
//import mk.finki.ukim.wp.lab.service.BookService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet(name="books-servlet", urlPatterns = "/servlet-listBooks")
//public class BookListServlet extends HttpServlet {
//    private final SpringTemplateEngine springTemplateEngine;
//    private final BookService bookService;
//
//    public BookListServlet(SpringTemplateEngine springTemplateEngine, BookService bookService) {
//        this.springTemplateEngine = springTemplateEngine;
//        this.bookService = bookService;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(req.getServletContext())
//                .buildExchange(req,resp);
//        WebContext context = new WebContext(webExchange);
//        List<Book> books = bookService.listBooks();
//        context.setVariable("books", books);
//        this.springTemplateEngine.process("listBooks.html", context, resp.getWriter());
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String bIsbn = req.getParameter("bookIsbn");
//        Book bookByIsbn = bookService.findBookByIsbn(bIsbn);
//        resp.sendRedirect("/author?isbn=" + bookByIsbn.getIsbn());
//
//
//
//    }
//
//
//}
