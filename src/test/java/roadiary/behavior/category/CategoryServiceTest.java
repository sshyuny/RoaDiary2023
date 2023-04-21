package roadiary.behavior.category;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import roadiary.behavior.category.domain.dto.CategoryResDto;
import roadiary.behavior.category.domain.entity.CategoryEntity;
import roadiary.behavior.category.domain.entity.PriorityCategoryEntity;
import roadiary.behavior.category.repository.CategoryRepository;
import roadiary.behavior.category.service.CategoryService;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
public class CategoryServiceTest {

    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;

    final Long user2Id = 2L;

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

        int maxPriority = categoryService.MAX_PRIORITY;
        int someCategoryIdShouldntBeSaved = 12;
        
        // 최대개수 이전

        //given
        for (int i = 1; i < maxPriority; i++) {

            categoryRepository.insertPriority(PriorityCategoryEntity.of(user2Id, i * 2, (long) i));
        }
        //then
        assertFalse(categoryService.hasMaxCategorySavedAlready(user2Id));

        // 최대개수 이후

        //given
        categoryRepository.insertPriority(PriorityCategoryEntity.of(user2Id, maxPriority * 2 + 1, someCategoryIdShouldntBeSaved));
        //then
        assertTrue(categoryService.hasMaxCategorySavedAlready(user2Id));
    }

    @Test
    void addPriority_테스트() {

        int lastSavedPriority = 20;

        long someCategoryId1 = 1L;
        long someCategoryId2 = 2L;

        //given
        categoryRepository.insertPriority(PriorityCategoryEntity.of(user2Id, lastSavedPriority, someCategoryId1));
        Integer theMaxPriorityBefore = categoryRepository.selectTheMaxPriority(user2Id);
        
        //when
        categoryService.addPriority(user2Id, someCategoryId2);
        Integer theMaxPriorityAfter = categoryRepository.selectTheMaxPriority(user2Id);

        //then
        assertThat(theMaxPriorityBefore).isEqualTo(lastSavedPriority);
        assertThat(theMaxPriorityAfter).isEqualTo(lastSavedPriority + 1);
    }

    @Test
    void hasTheCategoryInAccountPriority_테스트() {

        long categoryIdWillBeSaved = 1L;
        long categoryIdWontBeSaved = 2L;

        //given
        categoryRepository.insertPriority(PriorityCategoryEntity.of(user2Id, 1, categoryIdWillBeSaved));

        //then
        assertTrue(categoryService.hasTheCategoryInAccountPriority(user2Id, categoryIdWillBeSaved));
        assertFalse(categoryService.hasTheCategoryInAccountPriority(user2Id, categoryIdWontBeSaved));
    }



    // @Test
    // @Transactional
    // void updateDirectionOfPriority_카테고리순서변경테스트() {
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
