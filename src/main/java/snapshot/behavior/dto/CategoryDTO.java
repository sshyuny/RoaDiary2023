package snapshot.behavior.dto;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

public class CategoryDTO {
    
    private final long id;
    private final long userId;
    private final String content;
    private final LocalDateTime recentlyUsed;

    @Autowired
    public CategoryDTO(long id, long userId, String content, LocalDateTime recentlyUsed) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.recentlyUsed = recentlyUsed;
    }
}
