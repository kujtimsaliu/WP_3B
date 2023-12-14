package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.model.Author;
import mk.finki.ukim.wp.lab.model.AuthorFullname;
import mk.finki.ukim.wp.lab.model.exceptions.InvalidBookIdException;
import mk.finki.ukim.wp.lab.repository.impl.InMemoryAuthorRepository;
import mk.finki.ukim.wp.lab.repository.jpa.AuthorRepository;
import mk.finki.ukim.wp.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> listAuthors() {
        return this.authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return this.authorRepository.findById(id).orElseThrow(InvalidBookIdException::new);
    }

    @Override
    public void save(String name, String surname, String biography, LocalDate dateOfBirth) {
        AuthorFullname authorFullname = new AuthorFullname(name, surname);
        authorRepository.save(new Author(authorFullname, biography, dateOfBirth));
    }



}
