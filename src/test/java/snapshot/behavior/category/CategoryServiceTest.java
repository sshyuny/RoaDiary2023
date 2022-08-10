package snapshot.behavior.category;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import snapshot.behavior.category.dto.CategoryDTO;
import snapshot.behavior.category.dto.CategoryResDTO;
import snapshot.behavior.category.service.CategoryService;

public class CategoryServiceTest {
    
    //AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(CategoryTestConfig.class);

    // 이런 식으로 의존 빈 사용해도 되는건지 추후 확인하기 / 생성자로 주입해야하나?
    //private CategoryService categoryService = ac.getBean(CategoryService.class);

    @Test
    @DisplayName("")
    void getCategoryListTest() {

        
    }

}
