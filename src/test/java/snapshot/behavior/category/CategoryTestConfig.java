package snapshot.behavior.category;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import snapshot.behavior.category.repository.CategoryRepository;
import snapshot.behavior.category.service.CategoryService;

@Configuration
public class CategoryTestConfig {
    
    @Bean
    CategoryRepository categoryRepository() {
        return new CategoryRepositoryTempImpl();
    }

    @Bean
    CategoryService categoryService() {
        return new CategoryService(categoryRepository());
    }
}
