package mk.finki.ukim.wp.lab.repository.impl;

import mk.finki.ukim.wp.lab.bootstrap.Dataholder;
import mk.finki.ukim.wp.lab.model.BookStore;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InBookStoreRepository {

    public List<BookStore> findAll() {
        return Dataholder.bookStores;
    }

    public Optional<BookStore> findById(Long id){
        return Dataholder.bookStores.stream()
                .filter(bs -> bs.getId().equals(id)).findFirst();
    }

    public void removeBookStore(Long id){
        Dataholder.bookStores.removeIf(i -> i.getId().equals(id));
    }

    public void saveStore(String name, String city, String address) {

        Dataholder.bookStores.add(new BookStore(name, city, address));
    }


}
