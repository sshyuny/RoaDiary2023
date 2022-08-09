package snapshot.behavior.category.dto;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

public class CategoryDTO {
    
    private long id;
    private long user_id;
    private String content;
    private LocalDateTime recently_used;

    @Autowired
    public CategoryDTO(long id, long user_id, String content, LocalDateTime recently_used) {
        this.id = id;
        this.user_id = user_id;
        this.content = content;
        this.recently_used = recently_used;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getRecently_used() {
        return recently_used;
    }

    public void setRecently_used(LocalDateTime recently_used) {
        this.recently_used = recently_used;
    }


}
