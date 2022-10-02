/*package roadiary.behavior.category;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import roadiary.behavior.category.Dto.CategoryDto;
import roadiary.behavior.category.repository.CategoryRepository;

public class CategoryRepositoryTempImpl implements CategoryRepository {
    
    @Override
    public List<CategoryDto> selectCategoryDtos(Long userId) {
        List<CategoryDto> categoryDtoList = new ArrayList<>();

        CategoryDto categoryDto1 = new CategoryDto(1L, userId, "study", LocalDateTime.parse("2022-08-05T05:00:00"));
        CategoryDto categoryDto2 = new CategoryDto(2L, userId, "date", LocalDateTime.parse("2022-08-10T05:00:00"));
        CategoryDto categoryDto3 = new CategoryDto(3L, userId, "play", LocalDateTime.parse("2022-08-01T05:00:00"));
        categoryDtoList.add(categoryDto1);
        categoryDtoList.add(categoryDto2);
        categoryDtoList.add(categoryDto3);

        return categoryDtoList;
    }

    @Override
    public int addCategory(Long userId, String categoryName) {
        return 1;
    }
}
*/