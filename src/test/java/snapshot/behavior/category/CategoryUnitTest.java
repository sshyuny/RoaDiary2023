package snapshot.behavior.category;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import snapshot.behavior.category.dto.CategoryDTO;
import snapshot.behavior.category.dto.CategoryResDTO;
import snapshot.behavior.category.repository.CategoryRepository;
import snapshot.behavior.category.service.CategoryUnit;

// @SpringBootTest
public class CategoryUnitTest {

    // AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(CategoryTestConfig.class);
    
    //@Autowired
    private CategoryRepository categoryRepository = new CategoryRepositoryTempImpl();
    // private final CategoryRepository categoryRepository;
    // Autowired 되지 않은 것으로 보임. 테스트에서 의존 주입 방법 찾아서 적용해보기

    // @Autowired
    // public CategoryUnitTest(CategoryRepository categoryRepository) {
    //     this.categoryRepository = categoryRepository;
    // }
    
    @Test
    @DisplayName("CategoryDTO 최신순정렬 뒤 CategoryResDTO로 반환 확인")
    void sortCategoryDTOListOrderByTimeTest() {

        // given
        List<CategoryDTO> categoryDTOList = categoryRepository.selectCategoryDTOList(1L);

        // when
        List<CategoryResDTO> sortedCategoryResDTOList = CategoryUnit.sortCategoryDTOListOrderByTime(categoryDTOList);

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
