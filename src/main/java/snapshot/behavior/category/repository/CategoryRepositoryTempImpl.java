package snapshot.behavior.category.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import snapshot.behavior.category.dto.CategoryDTO;

@Component
//@Primary
public class CategoryRepositoryTempImpl implements CategoryRepository {
    
    @Override
    public List<CategoryDTO> selectCategoryDTOList(Long userId) {
        List<CategoryDTO> categoryDTOList = new ArrayList<>();

        CategoryDTO categoryDTO1 = new CategoryDTO(1L, userId, "study", LocalDateTime.now());
        CategoryDTO categoryDTO2 = new CategoryDTO(2L, userId, "date", LocalDateTime.now());
        CategoryDTO categoryDTO3 = new CategoryDTO(3L, userId, "play", LocalDateTime.now());
        categoryDTOList.add(categoryDTO1);
        categoryDTOList.add(categoryDTO2);
        categoryDTOList.add(categoryDTO3);

        return categoryDTOList;
    }
}
