package hh.sof03.bookstore.domain;

import org.springframework.data.repository.CrudRepository;

// tietokantakäsittelyn rajapinta
public interface CategoryRepository extends CrudRepository<Category, Long> {

}
