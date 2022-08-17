package snapshot.behavior.category.service;

import java.util.ArrayList;
import java.util.List;

import snapshot.behavior.category.dto.CategoryDTO;
import snapshot.behavior.category.dto.CategoryResDTO;

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
