package hh.sof03.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.sof03.bookstore.domain.Book;
import hh.sof03.bookstore.domain.BookRepository;
import hh.sof03.bookstore.domain.Category;
import hh.sof03.bookstore.domain.CategoryRepository;
import hh.sof03.bookstore.domain.User;
import hh.sof03.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	
	// uusi logger attribuutti
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	// testidatan luonti H2-testitietokantaan aina sovelluksen käynnistyessä
	@Bean
	public CommandLineRunner bookDemo(BookRepository bRepository, CategoryRepository cRepository, UserRepository uRepository) {
		return (args) -> {
			Category c1 = new Category("Romance");
			cRepository.save(c1);
			Category c2 = new Category("Gothic");
			cRepository.save(c2);
			Category c3 = new Category("Poetry");
			cRepository.save(c3);
			
			bRepository.save(new Book("Järki ja tunteet", "Jane Austen", 1811, "978-951-0-45636-1", 10.0, c1));
			bRepository.save(new Book("Dorian Grayn Muotokuva", "Oscar Wilde", 1891, "978-951-1-25774-5", 15.0, c2));
			bRepository.save(new Book("milk and honey", "Rupi Kaur", 2015, "978-1-4494-7425-6", 13.5, c3));
			
			User user1 = new User("user", "$2a$10$jsHlAcln72kVBlvypn0Ce.mK/Ccf5btU7waW276wm5OFUdjNLwJZK", "user@email.com", "USER");
			User user2 = new User("admin", "$2a$10$f.Fcwtyvvgf0IfaGJOLkjOfOFGd7SVCWI25ZuS4AXPrB65d6SE0fW", "admin@email.com", "ADMIN");
			
			uRepository.save(user1);
			uRepository.save(user2);
			
			// testidatan näyttö consolessa
			log.info("fetch all books");
			for (Book book : bRepository.findAll()) {
				log.info(book.toString());
			}
		};
	}
}
