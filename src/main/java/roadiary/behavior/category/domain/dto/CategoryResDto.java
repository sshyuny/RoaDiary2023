package roadiary.behavior.category.domain.dto;

public class CategoryResDto {
    
    private long id;
    private String content;

    public CategoryResDto() {}
    
    public CategoryResDto(long id, String content) {
        this.id = id;
        this.content = content;
    }
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    
}
