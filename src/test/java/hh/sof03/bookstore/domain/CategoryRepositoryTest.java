package hh.sof03.bookstore.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CategoryRepositoryTest {
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Test
	public void createNewCategory() {
		Category category = new Category("Horror");
		categoryRepository.save(category);
		assertThat(category.getCategoryid()).isNotNull();
	}
	
	@Test
	public void deleteCategory() {	
		categoryRepository.deleteById(1L);
		boolean categoryExistsAfterDeletion = categoryRepository.existsById(1L);
		assertThat(categoryExistsAfterDeletion).isFalse();
	}
	
	@Test
	public void findByNameShouldReturnCategory() {
		List<Category> categories = categoryRepository.findByName("Romance");
		assertThat(categories).hasSize(1);
	}
}
