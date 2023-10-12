package hh.sof03.bookstore.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof03.bookstore.domain.Book;
import hh.sof03.bookstore.domain.BookRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {
	@Autowired
	private BookRepository bookRepository;
	
	@Test
	public void createNewBook() {
		Book book = new Book("The Fault in Our Stars", "John Green", 2012, "0-525-47881-7", 15.0, null);
		bookRepository.save(book);
		assertThat(book.getId()).isNotNull();
	}
	
	@Test
	public void deleteBook() {
		bookRepository.deleteById(2L);
		boolean bookExistsAfterDeletion = bookRepository.existsById(2L);
		assertThat(bookExistsAfterDeletion).isFalse();
	}
	
	@Test
	public void findByTitleShouldReturnBook() {
		List<Book> books = bookRepository.findByTitle("Järki ja tunteet");
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("Jane Austen");
	}
	
}
