package hh.sof03.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.sof03.bookstore.domain.Book;
import hh.sof03.bookstore.domain.BookRepository;
import hh.sof03.bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	
	// Spring-alusta luo sovelluksen käynnistyessä BookRepository-rajapintaa toteuttavan luokan olion 
	// ja kytkee olion BookController-luokasta luodun olion attribuuttiolioksi
	@Autowired
	private BookRepository bRepository;
	// sama CategoryRepositorylle
	@Autowired
	private CategoryRepository cRepository;
	
	// Listaa kirjat
	@GetMapping(value="/books")
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) bRepository.findAll();
	}
	
	// haetaan kirja id:n perusteella
		@GetMapping(value="/books/{id}")
		public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
			return bRepository.findById(bookId);
		}
	
	// Luo tyhjän kirjalomakkeen
	@RequestMapping(value="/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book()); // "tyhjä" kirja-olio
		model.addAttribute("categories", cRepository.findAll());
		return "addBook";
	}
	
	// Kirjalomakkeella syötettyjen tietojen vastaanotto ja tallennus
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveBook(Book book) {
		bRepository.save(book); // Tallennetaan yhden kirjan tiedot tietokantaan
		return "redirect:booklist";
	}
	
	// Poistaa kirjan
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		bRepository.deleteById(bookId);
		return "redirect:/booklist";
	}
	
	// Muokkaa kirjaa
	@RequestMapping(value="edit/{id}")
	public String editBook(@PathVariable("id") Long bookId, Model model) { // PathVariable löytää kirja-olion id:n perusteella
		model.addAttribute("book", bRepository.findById(bookId)); // korvataan olemassaoleva olio uudella ja laitetaan se samaan id:hen kuin edellinen
		model.addAttribute("categories", cRepository.findAll());
		return "editbook";
	}
}
