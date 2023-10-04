package hh.sof03.bookstore.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {
	// attribuutit
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long categoryid;
	private String name;
	
	// category OneToMany Student
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	@JsonIgnoreProperties("category") // vältetään ikuinen loop
	private List<Book> books;
	
	// konstruktorit
	public Category() {}

	public Category(String name) {
		super();
		this.name = name;
	}
	
	// getterit ja setterit
	public Long getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(Long categoryid) {
		this.categoryid = categoryid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	// toString
	@Override
	public String toString() {
		return "Category [categoryid=" + categoryid + ", name=" + name + "]";
	}
	
	
	
}
