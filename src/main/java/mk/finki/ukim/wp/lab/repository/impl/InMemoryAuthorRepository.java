package mk.finki.ukim.wp.lab.repository.impl;

import mk.finki.ukim.wp.lab.bootstrap.Dataholder;
import mk.finki.ukim.wp.lab.model.Author;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryAuthorRepository {
    public List<Author> findAll(){
//        return Dataholder.authors.stream().collect(Collectors.toList());
        return Dataholder.authors;
    }
    public Optional<Author> findById(Long id){
        return Dataholder.authors.stream().filter(a->a.getId().equals(id)).findFirst();
    }


}
