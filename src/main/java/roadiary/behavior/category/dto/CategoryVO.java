package roadiary.behavior.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class CategoryVO {
    
    // 이후에 user가 최근에 사용한 category 반환하는 걸로 수정하기
    private long userId;
    private String categoryContent;
    
}
