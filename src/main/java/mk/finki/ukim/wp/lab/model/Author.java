package mk.finki.ukim.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Convert(converter = AuthorFullnameConverter.class)
    AuthorFullname authorFullname;

//    @Column(name = "author_name")
//    String name;
//
//    String surname;

    String biography;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    public Author(AuthorFullname authorFullname, String biography, LocalDate dateOfBirth) {
//        this.id = id;
        this.authorFullname=authorFullname;
        this.biography = biography;
        this.dateOfBirth=dateOfBirth;
    }


    public Author() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
