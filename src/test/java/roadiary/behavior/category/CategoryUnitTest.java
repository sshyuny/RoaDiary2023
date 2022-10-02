/*package roadiary.behavior.category;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import roadiary.behavior.category.Dto.CategoryDto;
import roadiary.behavior.category.Dto.CategoryResDto;
import roadiary.behavior.category.repository.CategoryRepository;
import roadiary.behavior.category.service.CategoryUnit;

public class CategoryUnitTest {

    private CategoryRepository categoryRepository = new CategoryRepositoryTempImpl();
    
    // 비즈니스 로직을 지우고, 같은 기능을 DB 쿼리로 옮김 
    // 필요 없어진 Test
    @Test
    @DisplayName("CategoryDto 최신순정렬 뒤 CategoryResDto로 반환 확인")
    void switchCategoryDtoToResDtoTest() {

        // given
        List<CategoryDto> categoryDtoList = categoryRepository.selectCategoryDtos(1L);

        // when
        List<CategoryResDto> sortedCategoryResDtoList = CategoryUnit.switchCategoryDtoToResDto(categoryDtoList);

        Map<Long, LocalDateTime> categoryIdTimeMap = new HashMap<>();
        for (CategoryDto categoryDto : categoryDtoList) {
            categoryIdTimeMap.put(categoryDto.getId(), categoryDto.getRecently_used());
        }
        
        // then
        int listSize = sortedCategoryResDtoList.size();
        long beforeId = sortedCategoryResDtoList.get(0).getId();
        for (int i = 1; i < listSize; i++) {
            long curId = sortedCategoryResDtoList.get(i).getId();
            LocalDateTime beforeTime = categoryIdTimeMap.get(beforeId);
            LocalDateTime curTime = categoryIdTimeMap.get(curId);
            
            assertTrue(curTime.isAfter(beforeTime));
            
            System.out.println("beforeId: " + beforeId + " & beforeTime: " + beforeTime);
            System.out.println("curId: " + curId + " & curTime: " + curTime);

            beforeId = curId;
        }
    }
    
}
*/