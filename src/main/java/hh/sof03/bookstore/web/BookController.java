package hh.sof03.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController {
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String getBooks(Model model) {
		
	return "";	
	}

}
