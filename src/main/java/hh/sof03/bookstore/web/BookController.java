package hh.sof03.bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.sof03.bookstore.domain.Book;
import hh.sof03.bookstore.domain.BookRepository;

@Controller
public class BookController {
	
	// Spring-alusta luo sovelluksen käynnistyessä BookRepository-rajapintaa toteuttavan luokan olion 
	// ja kytkee olion BookController-luokasta luodun olion attribuuttiolioksi
	@Autowired
	BookRepository bookRepository;
	
	// Listaa kirjat
	@RequestMapping(value="/booklist", method = RequestMethod.GET)
	public String getBooks(Model model) {
		List<Book> books = (List<Book>) bookRepository.findAll();
		model.addAttribute("books", books);
		return "booklist";	
	}
	
	// Luo tyhjän kirjalomakkeen
	@RequestMapping(value="/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book()); // "tyhjä" kirja-olio
		return "addBook";
	}
	
	// Kirjalomakkeella syötettyjen tietojen vastaanotto ja tallennus
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveBook(Book book) {
		bookRepository.save(book); // Tallennetaan yhden kirjan tiedot tietokantaan
		return "redirect:booklist";
	}
	
	// Poistaa kirjan
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		bookRepository.deleteById(bookId);
		return "redirect:/booklist";
	}
	
	// Muokkaa kirjaa
	@RequestMapping(value="edit/{id}")
	public String editBook(@PathVariable("id") Long bookId, Model model) { // PathVariable löytää kirja-olion id:n perusteella
		model.addAttribute("book", bookRepository.findById(bookId)); // korvataan olemassaoleva olio uudella ja laitetaan se samaan id:hen kuin edellinen
		return "editbook";
	}
}
