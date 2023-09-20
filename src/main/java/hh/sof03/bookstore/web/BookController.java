package hh.sof03.bookstore.web;

import java.util.ArrayList;
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
	
	@Autowired
	private BookRepository repository;
	
	private final List<Book> books = new ArrayList<Book>();
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String getBooks(Model model) {
		model.addAttribute("car", new Book());
		model.addAttribute("books", books);
		return "welcome";	
	}
	
	@RequestMapping(value="/booklist", method = RequestMethod.GET)
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	

}
