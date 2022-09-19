package roadiary.behavior.category.service;

import java.util.ArrayList;
import java.util.List;

import roadiary.behavior.category.dto.CategoryDTO;
import roadiary.behavior.category.dto.CategoryResDTO;

public class CategoryUnit {
    
    static public List<CategoryResDTO> switchCategoryDTOToResDTO(List<CategoryDTO> categoryDTOList) {
        // 이후에 user가 최근에 사용한 category 반환하는 걸로 수정하기
        List<CategoryResDTO> newList = new ArrayList<>();

        for (CategoryDTO categoryDTO : categoryDTOList) {
            CategoryResDTO categoryResDTO = new CategoryResDTO(categoryDTO.getBehavior_category_id(), categoryDTO.getContent());
            newList.add(categoryResDTO);
        }

        return newList;
    }
}
