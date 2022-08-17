package snapshot.behavior.category;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import snapshot.behavior.category.dto.CategoryDTO;
import snapshot.behavior.category.dto.CategoryResDTO;
import snapshot.behavior.category.repository.CategoryRepository;
import snapshot.behavior.category.service.CategoryUnit;

public class CategoryUnitTest {

    private CategoryRepository categoryRepository = new CategoryRepositoryTempImpl();
    
    // 비즈니스 로직을 지우고, 같은 기능을 DB 쿼리로 옮김 
    // 필요 없어진 Test
    @Test
    @DisplayName("CategoryDTO 최신순정렬 뒤 CategoryResDTO로 반환 확인")
    void switchCategoryDTOToResDTOTest() {

        // given
        List<CategoryDTO> categoryDTOList = categoryRepository.selectCategoryDTOList(1L);

        // when
        List<CategoryResDTO> sortedCategoryResDTOList = CategoryUnit.switchCategoryDTOToResDTO(categoryDTOList);

        Map<Long, LocalDateTime> categoryIdTimeMap = new HashMap<>();
        for (CategoryDTO categoryDTO : categoryDTOList) {
            categoryIdTimeMap.put(categoryDTO.getId(), categoryDTO.getRecently_used());
        }
        
        // then
        int listSize = sortedCategoryResDTOList.size();
        long beforeId = sortedCategoryResDTOList.get(0).getId();
        for (int i = 1; i < listSize; i++) {
            long curId = sortedCategoryResDTOList.get(i).getId();
            LocalDateTime beforeTime = categoryIdTimeMap.get(beforeId);
            LocalDateTime curTime = categoryIdTimeMap.get(curId);
            
            assertTrue(curTime.isAfter(beforeTime));
            
            System.out.println("beforeId: " + beforeId + " & beforeTime: " + beforeTime);
            System.out.println("curId: " + curId + " & curTime: " + curTime);

            beforeId = curId;
        }
    }
    
}
