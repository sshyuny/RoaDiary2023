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

    final Long fullUserId = 1L;
    final Long emptyUserId = 2L;

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

        //given
        int maxPriority = categoryService.MAX_PRIORITY;
        int someCategoryIdShouldntBeSaved = 12;
        
        // 1. 최대개수 이전

        //when
        for (int i = 1; i < maxPriority; i++) {

            categoryRepository.insertPriority(PriorityCategoryEntity.of(emptyUserId, i * 2, (long) i));
        }
        //then
        assertFalse(categoryService.hasMaxCategorySavedAlready(emptyUserId));

        // 2. 최대개수 이후

        //when
        categoryRepository.insertPriority(PriorityCategoryEntity.of(emptyUserId, maxPriority * 2 + 1, someCategoryIdShouldntBeSaved));
        //then
        assertTrue(categoryService.hasMaxCategorySavedAlready(emptyUserId));
    }

    @Test
    void addPriority_테스트() {

        //given
        int lastSavedPriority = 20;

        long someCategoryId1 = 1L;
        long someCategoryId2 = 2L;

        categoryRepository.insertPriority(PriorityCategoryEntity.of(emptyUserId, lastSavedPriority, someCategoryId1));
        Integer theMaxPriorityBefore = categoryRepository.selectTheMaxPriority(emptyUserId);
        
        //when
        categoryService.addPriority(emptyUserId, someCategoryId2);
        Integer theMaxPriorityAfter = categoryRepository.selectTheMaxPriority(emptyUserId);

        //then
        assertThat(theMaxPriorityBefore).isEqualTo(lastSavedPriority);
        assertThat(theMaxPriorityAfter).isEqualTo(lastSavedPriority + 1);
    }

    @Test
    void removePriority_테스트() {

        //given
        int lastSavedPriority = 2;
        long categoryIdWillBeRemoved = 1L;
        long categoryIdWontBeRemoved = 2L;

        categoryRepository.insertPriority(PriorityCategoryEntity.of(emptyUserId, lastSavedPriority, categoryIdWillBeRemoved));
        categoryRepository.insertPriority(PriorityCategoryEntity.of(emptyUserId, lastSavedPriority, categoryIdWontBeRemoved));

        //when
        categoryService.removePriority(emptyUserId, categoryIdWillBeRemoved);

        //then
        PriorityCategoryEntity priorityCategoryEntity1 = categoryRepository.selectPriorityCategoryByUserIdAndCategoryId(emptyUserId, categoryIdWillBeRemoved);
        PriorityCategoryEntity priorityCategoryEntity2 = categoryRepository.selectPriorityCategoryByUserIdAndCategoryId(emptyUserId, categoryIdWontBeRemoved);
        assertThat(priorityCategoryEntity1).isNull();
        assertThat(priorityCategoryEntity2).isNotNull();

    }

    @Test
    void hasTheCategoryInAccountPriority_테스트() {

        //given
        long categoryIdWillBeSaved = 1L;
        long categoryIdWontBeSaved = 2L;

        //when
        categoryRepository.insertPriority(PriorityCategoryEntity.of(emptyUserId, 1, categoryIdWillBeSaved));

        //then
        assertTrue(categoryService.hasTheCategoryInAccountPriority(emptyUserId, categoryIdWillBeSaved));
        assertFalse(categoryService.hasTheCategoryInAccountPriority(emptyUserId, categoryIdWontBeSaved));
    }

    @Test
    void getTwoCategoryToSwitchPriority_정상적인상황() {

        //given
        long secondPriorityCategoryId = 3L;
        
        //when
        List<PriorityCategoryEntity> entitiesDown = 
                categoryService.getTwoCategoryToSwitchPriority(fullUserId, secondPriorityCategoryId, "down");
        List<PriorityCategoryEntity> entitiesUp = 
                categoryService.getTwoCategoryToSwitchPriority(fullUserId, secondPriorityCategoryId, "up");

        //then
        assertThat(entitiesDown.size()).isEqualTo(2);
        assertThat(entitiesUp.size()).isEqualTo(2);
        assertThat(entitiesDown.get(0).getBehaviorCategoryId()).isEqualTo(secondPriorityCategoryId);
        assertThat(entitiesDown.get(0).getPriorityIdx()).isEqualTo(2);
        assertThat(entitiesDown.get(1).getBehaviorCategoryId()).isEqualTo(4L);
        assertThat(entitiesDown.get(1).getPriorityIdx()).isEqualTo(3);
        assertThat(entitiesUp.get(0).getBehaviorCategoryId()).isEqualTo(secondPriorityCategoryId);
        assertThat(entitiesUp.get(0).getPriorityIdx()).isEqualTo(2);
        assertThat(entitiesUp.get(1).getBehaviorCategoryId()).isEqualTo(2L);
        assertThat(entitiesUp.get(1).getPriorityIdx()).isEqualTo(1);
    }

    @Test
    void getTwoCategoryToSwitchPriority_예외상황() {

        //given
        long firstPriorityCategoryId = 2L;
        long lastPriorityCategoryId = 4L;

        //then
        assertThatThrownBy(() -> categoryService.getTwoCategoryToSwitchPriority(fullUserId, firstPriorityCategoryId, "up"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> categoryService.getTwoCategoryToSwitchPriority(fullUserId, lastPriorityCategoryId, "down"))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    void updateDirectionOfPriority_위로올리기() {
        
        //given
        long secondPriorityCategoryId = 3L;
        int secondPriorityIdx = 2;
        long firstPriorityCategoryId = 2L;
        int firstPriorityIdx = 1;

        //before then
        PriorityCategoryEntity entityAfterUping1Before = categoryRepository.selectPriorityCategoryByUserIdAndCategoryId(fullUserId, secondPriorityCategoryId);
        assertThat(entityAfterUping1Before.getPriorityIdx()).isEqualTo(secondPriorityIdx);
        PriorityCategoryEntity entityAfterUping2Before = categoryRepository.selectPriorityCategoryByUserIdAndCategoryId(fullUserId, firstPriorityCategoryId);
        assertThat(entityAfterUping2Before.getPriorityIdx()).isEqualTo(firstPriorityIdx);

        //when
        List<PriorityCategoryEntity> entitiesUp = 
                categoryService.getTwoCategoryToSwitchPriority(fullUserId, secondPriorityCategoryId, "up");
        categoryService.updateDirectionOfPriority(entitiesUp);
        
        //then
        PriorityCategoryEntity entityAfterUping1 = categoryRepository.selectPriorityCategoryByUserIdAndCategoryId(fullUserId, secondPriorityCategoryId);
        assertThat(entityAfterUping1.getPriorityIdx()).isEqualTo(firstPriorityIdx);
        PriorityCategoryEntity entityAfterUping2 = categoryRepository.selectPriorityCategoryByUserIdAndCategoryId(fullUserId, firstPriorityCategoryId);
        assertThat(entityAfterUping2.getPriorityIdx()).isEqualTo(secondPriorityIdx);
        
    }

}
