package mk.finki.ukim.wp.lab.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthorFullname implements Serializable {
    private String name;
    private String surname;

    public AuthorFullname(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
