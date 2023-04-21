package roadiary.behavior.category.domain.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter @Getter
public class CategoryResDto {

    private long id;
    private String content;
    
    public static CategoryResDto of (long id, String content) {
        CategoryResDto categoryResDto = new CategoryResDto();
        categoryResDto.setId(id);
        categoryResDto.setContent(content);
        return categoryResDto;
    }

}
