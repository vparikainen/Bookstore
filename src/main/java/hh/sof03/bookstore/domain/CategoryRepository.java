package hh.sof03.bookstore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

// tietokantakäsittelyn rajapinta
public interface CategoryRepository extends CrudRepository<Category, Long> {

	List<Category> findByName(String name);

}
