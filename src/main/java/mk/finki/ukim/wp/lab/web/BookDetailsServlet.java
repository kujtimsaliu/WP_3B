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
//
//@WebServlet(name="book-details-servlet", urlPatterns = "/servlet-bookDetails")
//public class BookDetailsServlet extends HttpServlet {
//
//    private final SpringTemplateEngine springTemplateEngine;
//    private final BookService bookService;
//
//    public BookDetailsServlet(SpringTemplateEngine springTemplateEngine, BookService bookService) {
//        this.springTemplateEngine = springTemplateEngine;
//        this.bookService = bookService;
//    }
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(req.getServletContext())
//                .buildExchange(req,resp);
//        WebContext context = new WebContext(webExchange);
//        String isbn = req.getParameter("isbn");
//        Book b = bookService.findBookByIsbn(isbn);
//        context.setVariable("selectedBook", b);
//
//        this.springTemplateEngine.process("/bookDetails.html", context, resp.getWriter());
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.sendRedirect("/bookDetails");
//    }
//}
