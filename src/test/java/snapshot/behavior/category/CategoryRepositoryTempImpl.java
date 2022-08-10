package snapshot.behavior.category;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import snapshot.behavior.category.dto.CategoryDTO;
import snapshot.behavior.category.repository.CategoryRepository;

public class CategoryRepositoryTempImpl implements CategoryRepository {
    
    @Override
    public List<CategoryDTO> selectCategoryDTOList(Long userId) {
        List<CategoryDTO> categoryDTOList = new ArrayList<>();

        CategoryDTO categoryDTO1 = new CategoryDTO(1L, userId, "study", LocalDateTime.parse("2022-08-05T05:00:00"));
        CategoryDTO categoryDTO2 = new CategoryDTO(2L, userId, "date", LocalDateTime.parse("2022-08-10T05:00:00"));
        CategoryDTO categoryDTO3 = new CategoryDTO(3L, userId, "play", LocalDateTime.parse("2022-08-01T05:00:00"));
        categoryDTOList.add(categoryDTO1);
        categoryDTOList.add(categoryDTO2);
        categoryDTOList.add(categoryDTO3);

        return categoryDTOList;
    }
}
