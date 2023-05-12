package roadiary.behavior.category;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import roadiary.behavior.category.domain.entity.CategoryEntity;
import roadiary.behavior.category.repository.CategoryRepository;
import roadiary.behavior.category.service.CategoryService;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTestWithMock {

    @InjectMocks
    CategoryService categoryService;
    @Mock
    CategoryRepository categoryRepository;

    final Long someUserId = 1122L;

    final Long fullUserId = 1L;
    final Long emptyUserId = 2L;

    @Test
    void addCategory_이미저장된카테고리를추가할경우_기존id값반환() {
    
        //given
        String savedCategoryContent = "이미저장된카테고리";
        Long savedCategoryId = 123L;
        
        //repository
        when(categoryRepository.selectCategoryIdByContent(savedCategoryContent)).thenReturn(savedCategoryId);

        //when
        Long categoryId = categoryService.addCategory(CategoryEntity.of(savedCategoryContent));

        //then
        assertThat(categoryId).isEqualTo(savedCategoryId);
    }

    @Test
    void addCategory_새로운카테고리를추가하는경우_새id값반환() {

        //given
        String newCategoryContent = "새카테고리";
        Long newCategoryId = 123L;
        CategoryEntity categoryEntity = CategoryEntity.of(newCategoryContent);
        
        //repository
        when(categoryRepository.selectCategoryIdByContent(newCategoryContent)).thenReturn(null);
        when(categoryRepository.insertCategory(categoryEntity)).thenAnswer(
            new Answer<Object>() {
                public Object answer(InvocationOnMock invocation) {
                    CategoryEntity categoryEntity = (CategoryEntity) invocation.getArgument(0);
                    categoryEntity.setBehaviorCategoryId(newCategoryId);
                    return 1;
                }
            }
        );

        //when
        Long categoryId = categoryService.addCategory(categoryEntity);

        //then
        assertThat(categoryId).isEqualTo(newCategoryId);
    }

    @Test
    void hasMaxCategorySavedAlready_카테고리순위저장() {
        
        // 1. 최대개수 이전
        when(categoryRepository.selectCategoryEntities(someUserId)).
                thenReturn(CategoryTestUtil.makeCategoryEntitiesUnderMax());
        assertFalse(categoryService.hasMaxCategorySavedAlready(someUserId));

        // 2. 최대개수 이후
        when(categoryRepository.selectCategoryEntities(someUserId)).
                thenReturn(CategoryTestUtil.makeCategoryEntitiesAboveMax());
        assertTrue(categoryService.hasMaxCategorySavedAlready(someUserId));
    }


}
