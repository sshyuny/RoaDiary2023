package roadiary.behavior.category.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class CategoryReqDto {
    
    private long userId;
    private long categoryId;
    private String categoryContent;

    public static CategoryReqDto of(long userId, long categoryId, String categoryContent) {
        CategoryReqDto categoryReqDto = new CategoryReqDto();
        categoryReqDto.setUserId(userId);
        categoryReqDto.setCategoryId(categoryId);
        categoryReqDto.setCategoryContent(categoryContent);
        return categoryReqDto;
    }
    
}
