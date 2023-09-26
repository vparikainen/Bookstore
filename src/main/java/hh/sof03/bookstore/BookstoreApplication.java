package hh.sof03.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.sof03.bookstore.domain.Book;
import hh.sof03.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {
	
	// uusi logger attribuutti
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	// testidatan luonti H2-testitietokantaan aina sovelluksen käynnistyessä
	@Bean
	public CommandLineRunner demo(BookRepository bookRepository) {
		return (args) -> {
			Book b1 = new Book("Järki ja tunteet", "Jane Austen", 1811, "978-951-0-45636-1", 10.0);
			Book b2 = new Book("Dorian Grayn Muotokuva", "Oscar Wilde", 1891, "978-951-1-25774-5", 15.0);
			Book b3 = new Book("milk and honey", "Rupi Kaur", 2015, "978-1-4494-7425-6", 13.5);
			
			bookRepository.save(b1);
			bookRepository.save(b2);
			bookRepository.save(b3);
			
			// testidatan näyttö consolessa
			log.info("fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
		};
	}
}
