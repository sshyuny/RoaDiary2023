package roadiary.behavior.category;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import roadiary.behavior.category.entity.CategoryEntity;
import roadiary.behavior.category.repository.CategoryRepository;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void selectCategoryEntities테스트() {

        List<CategoryEntity> categoryEntities = categoryRepository.selectCategoryEntities(1L);
        CategoryEntity categoryEntity1 = categoryEntities.get(0);
        CategoryEntity categoryEntity2 = categoryEntities.get(1);
        CategoryEntity categoryEntity3 = categoryEntities.get(2);

        assertThat(categoryEntities.size()).isEqualTo(3);

        assertThat(categoryEntity1.getBehaviorCategoryId()).isEqualTo(2L);
        assertThat(categoryEntity1.getContent()).isEqualTo("테스트2");
        assertThat(categoryEntity2.getBehaviorCategoryId()).isEqualTo(3L);
        assertThat(categoryEntity2.getContent()).isEqualTo("test3");
        assertThat(categoryEntity3.getBehaviorCategoryId()).isEqualTo(4L);
        assertThat(categoryEntity3.getContent()).isEqualTo("테스트4");
    }

    
}
