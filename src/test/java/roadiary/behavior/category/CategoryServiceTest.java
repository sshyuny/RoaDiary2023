package roadiary.behavior.category;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import roadiary.behavior.category.dto.CategoryReqDto;
import roadiary.behavior.category.dto.CategoryResDto;
import roadiary.behavior.category.entity.CategoryEntity;
import roadiary.behavior.category.entity.PriorityCategoryEntity;
import roadiary.behavior.category.repository.CategoryRepository;
import roadiary.behavior.category.service.CategoryService;

@ActiveProfiles("local")
@SpringBootTest
@Transactional
public class CategoryServiceTest {

    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;

    final Long userId = 2L;

    @Test
    void addCategory_이미저장된카테고리를추가할경우_기존id값반환() {
        //given
        String newCategoryContent = "카테고리 추가";
        CategoryEntity categoryEntity = CategoryEntity.of(newCategoryContent);
        
        //when
        int addedNum = categoryRepository.insertCategory(categoryEntity);
        Long categoryId = categoryService.addCategory(newCategoryContent);

        //then
        assertThat(addedNum).isEqualTo(1);
        assertThat(categoryId).isEqualTo(categoryEntity.getBehaviorCategoryId());
    }

    @Test
    void hasMaxCategorySavedAlready_최대개수이상으로_카테고리순위저장() {
        //given-1
        for (int i = 1; i < 12; i++) {
            categoryRepository.insertPriority(new PriorityCategoryEntity(userId, i, (long) i));
        }

        //then-1
        assertFalse(categoryService.hasMaxCategorySavedAlready(userId));

        //given-2
        categoryRepository.insertPriority(new PriorityCategoryEntity(userId, 12, 12));

        //then-2
        assertTrue(categoryService.hasMaxCategorySavedAlready(userId));
    }

    @Test
    void addPriority_테스트() {
        //given
        categoryRepository.insertPriority(new PriorityCategoryEntity(userId, 1, 1L));
        Integer theMaxPriorityBefore = categoryRepository.selectTheMaxPriority(userId);
        
        //when
        categoryService.addPriority(userId, 2L);
        Integer theMaxPriorityAfter = categoryRepository.selectTheMaxPriority(userId);

        //then
        assertThat(theMaxPriorityBefore).isEqualTo(1);
        assertThat(theMaxPriorityAfter).isEqualTo(2);
    }

    @Test
    void hasTheCategoryInAccountPriority_테스트() {
        //given
        categoryRepository.insertPriority(new PriorityCategoryEntity(userId, 1, 1L));

        //then
        assertTrue(categoryService.hasTheCategoryInAccountPriority(userId, 1L));
        assertFalse(categoryService.hasTheCategoryInAccountPriority(userId, 2L));
    }



    // @Test
    // @DisplayName("사용자의 카테고리 우선순위 변경")
    // @Transactional
    // void 카테고리순서변경테스트() {
    //     //given
    //     List<CategoryResDto> categoryResDtos1 = categoryService.getCategoryList(2L);
    //     long categoryId1Before = categoryResDtos1.get(1).getId();
    //     long categoryId1After = categoryResDtos1.get(2).getId();

    //     //when
    //     categoryService.updateDirectionOfPriority(2, categoryId1After, "up");

    //     List<CategoryResDto> categoryResDtos2 = categoryService.getCategoryList(2L);
    //     long categoryId2Before = categoryResDtos2.get(1).getId();
    //     long categoryId2After = categoryResDtos2.get(2).getId();

    //     //then
    //     assertThat(categoryId2After).isEqualTo(categoryId1Before);
    //     assertThat(categoryId2Before).isEqualTo(categoryId1After);
    // }

    // @Test
    // @DisplayName("사용자의 카테고리 우선순위 변경 - 맨 앞 카테고리를 위로 올리는 경우")
    // @Transactional
    // void 카테고리순서변경맨앞위로테스트() {
    //     //given
    //     List<CategoryResDto> categoryResDtos = categoryService.getCategoryList(2L);
    //     long categoryIdBefore = categoryResDtos.get(0).getId();

    //     //when
    //     int checkPossibleNum = categoryService.updateDirectionOfPriority(2, categoryIdBefore, "up");

    //     //then
    //     assertThat(checkPossibleNum).isEqualTo(0);
    // }

}
