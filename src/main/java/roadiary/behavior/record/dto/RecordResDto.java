package roadiary.behavior.record.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class RecordResDto {
    
    private long behaviorCategoryId;
    private LocalTime startTime;
    private LocalTime endTime;
    private String detail;
    
    private String content;
    
}
