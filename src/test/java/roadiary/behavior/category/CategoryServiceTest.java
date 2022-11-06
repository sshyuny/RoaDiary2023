package roadiary.behavior.category;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import roadiary.behavior.category.dto.CategoryReqDto;
import roadiary.behavior.category.dto.CategoryResDto;
import roadiary.behavior.category.service.CategoryService;

@ActiveProfiles("local")
@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    CategoryService categoryService;

    @Test
    @DisplayName("사용자의 카테고리 우선순위에 없는 값을, 카테고리 우선순위에 새로 추가")
    @Transactional
    void 카테고리우선순위추가테스트() {
        //given
        CategoryReqDto categoryReqDto = CategoryReqDto.of(2, "카테고리 추가");

        //when
        categoryService.addCategory(categoryReqDto);
        int addedPriorityNum = categoryService.addPriority(categoryReqDto);

        //then
        assertThat(addedPriorityNum).isEqualTo(1);
    }

    @Test
    @DisplayName("사용자의 카테고리 우선순위에 이미 들어있는 값을, 카테고리 우선순위에 새로 추가")
    @Transactional
    void 카테고리우선순위중복값추가테스트() {
        //given
        CategoryReqDto categoryReqDto1 = CategoryReqDto.of(2, "중복된 카테고리 추가");
        CategoryReqDto categoryReqDto2 = CategoryReqDto.of(2, "중복된 카테고리 추가");

        //when
        categoryService.addCategory(categoryReqDto1);
        int addedPriorityNum1 = categoryService.addPriority(categoryReqDto1);
        categoryService.addCategory(categoryReqDto2);
        int addedPriorityNum2 = categoryService.addPriority(categoryReqDto2);

        //then
        assertThat(addedPriorityNum1).isEqualTo(1);
        assertThat(addedPriorityNum2).isEqualTo(0);
    }

    @Test
    @DisplayName("사용자의 카테고리 우선순위 변경")
    @Transactional
    void 카테고리순서변경테스트() {
        //given
        List<CategoryResDto> categoryResDtos1 = categoryService.getCategoryList(2L);
        long categoryId1Before = categoryResDtos1.get(1).getId();
        long categoryId1After = categoryResDtos1.get(2).getId();

        //when
        categoryService.updateDirectionOfPriority(2, categoryId1After, "up");

        List<CategoryResDto> categoryResDtos2 = categoryService.getCategoryList(2L);
        long categoryId2Before = categoryResDtos2.get(1).getId();
        long categoryId2After = categoryResDtos2.get(2).getId();

        //then
        assertThat(categoryId2After).isEqualTo(categoryId1Before);
        assertThat(categoryId2Before).isEqualTo(categoryId1After);
    }

    @Test
    @DisplayName("사용자의 카테고리 우선순위 변경 - 맨 앞 카테고리를 위로 올리는 경우")
    @Transactional
    void 카테고리순서변경맨앞위로테스트() {
        //given
        List<CategoryResDto> categoryResDtos = categoryService.getCategoryList(2L);
        long categoryIdBefore = categoryResDtos.get(0).getId();

        //when
        int checkPossibleNum = categoryService.updateDirectionOfPriority(2, categoryIdBefore, "up");

        //then
        assertThat(checkPossibleNum).isEqualTo(0);
    }

}
