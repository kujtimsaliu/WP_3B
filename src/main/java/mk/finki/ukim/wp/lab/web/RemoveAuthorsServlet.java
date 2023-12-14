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
//@WebServlet(name="remove-author-servlet", urlPatterns = "/servlet-remove-authors")
//public class RemoveAuthorsServlet extends HttpServlet {
//    private final BookService bookService;
//    private final SpringTemplateEngine springTemplateEngine;
//
//
//    public RemoveAuthorsServlet(BookService bookService, SpringTemplateEngine springTemplateEngine) {
//        this.bookService = bookService;
//        this.springTemplateEngine = springTemplateEngine;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(req.getServletContext())
//                .buildExchange(req,resp);
//        WebContext context = new WebContext(webExchange);
//
//        String isbnFromParam = req.getParameter("isbn");
//        Book b = bookService.findBookByIsbn(isbnFromParam);
//        b.getAuthors().clear();
//
//        context.setVariable("selectedBook", b);
//
//        springTemplateEngine.process("/bookDetails.html", context, resp.getWriter());
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//    }
//}
