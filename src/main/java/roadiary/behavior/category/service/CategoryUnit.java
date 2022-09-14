package roadiary.behavior.category.service;

import java.util.ArrayList;
import java.util.List;

import roadiary.behavior.category.dto.CategoryDTO;
import roadiary.behavior.category.dto.CategoryResDTO;

public class CategoryUnit {
    
    static public List<CategoryResDTO> switchCategoryDTOToResDTO(List<CategoryDTO> categoryDTOList) {
        List<CategoryResDTO> newList = new ArrayList<>();

        for (CategoryDTO categoryDTO : categoryDTOList) {
            CategoryResDTO categoryResDTO = new CategoryResDTO(categoryDTO.getId(), categoryDTO.getContent());
            newList.add(categoryResDTO);
        }

        return newList;
    }
}
