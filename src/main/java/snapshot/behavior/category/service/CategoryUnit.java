package snapshot.behavior.category.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import snapshot.behavior.category.dto.CategoryDTO;
import snapshot.behavior.category.dto.CategoryResDTO;

public class CategoryUnit {
    
    static public List<CategoryResDTO> sortCategoryDTOListOrderByTime(List<CategoryDTO> categoryDTOList) {
        Map<LocalDateTime, Long> categoryTimeIdMap = new TreeMap<>();
        Map<Long, String> categoryIdContentMap = new TreeMap<>();
        List<CategoryResDTO> newList = new ArrayList<>();

        for (CategoryDTO categoryDTO : categoryDTOList) {
            categoryTimeIdMap.put(categoryDTO.getRecently_used(), categoryDTO.getId());
            categoryIdContentMap.put(categoryDTO.getId(), categoryDTO.getContent());
        }

        int maxLength;
        if (categoryTimeIdMap.size() < 10) maxLength = categoryTimeIdMap.size();
        else maxLength = 10;

        Set<LocalDateTime> keySet = categoryTimeIdMap.keySet();
        int checkMaxLength = 0;
        for (LocalDateTime time : keySet) {
            if (checkMaxLength > maxLength) break;

            long id = categoryTimeIdMap.get(time);
            newList.add(new CategoryResDTO(id, categoryIdContentMap.get(id)));
            ++checkMaxLength;
        }

        return newList;
    }
}
