package hh.sof03.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.sof03.bookstore.domain.Book;
import hh.sof03.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository repository) {
		return (args) -> {
			Book b1 = new Book("Järki ja tunteet", "Jane Austen", 1811, "978-951-0-45636-1", 10.0);
			Book b2 = new Book("Dorian Grayn Muotokuva", "Oscar Wilde", 1891, "978-951-1-25774-5", 15.0);
			Book b3 = new Book("milk and honey", "Rupi Kaur", 2015, "978-1-4494-7425-6", 13.5);
			
			repository.save(b1);
			repository.save(b2);
			repository.save(b3);
		};
	}
}
