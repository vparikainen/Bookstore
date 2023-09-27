package hh.sof03.bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.sof03.bookstore.domain.Category;
import hh.sof03.bookstore.domain.CategoryRepository;

@Controller
public class CategoryController {

	//Spring-alusta luo sovelluksen käynnistyessä CategoryRepository-rajapintaa toteuttavan luokan olion 
	// ja kytkee olion CategoryController-luokasta luodun olion attribuuttiolioksi
	@Autowired
	CategoryRepository catRepository;
	
	// Listaa kategoriat
	@RequestMapping(value="/categorylist", method = RequestMethod.GET)
	public String getCategories(Model model) {
		List<Category> categories = (List<Category>) catRepository.findAll();
		model.addAttribute("categories", categories);
		return "categorylist";
	}
	
	// Luo tyhjän kategorialomakkeen
	@RequestMapping(value="/addCategory")
	public String addCategory(Model model) {
		model.addAttribute("category", new Category()); // "tyhjä" kategoria-olio
		return "addcategory";
	}
	
	// kategorialomakkeella syötettyjen tietojen vastaanotto ja  tallennus
	@RequestMapping(value="/saveCategory", method = RequestMethod.POST)
	public String saveCategory(@ModelAttribute Category category) {
		// tallennetaan yhden kategorian tiedot tietokantaan
		catRepository.save(category);
		return "redirect:/categorylist";
	}
}
