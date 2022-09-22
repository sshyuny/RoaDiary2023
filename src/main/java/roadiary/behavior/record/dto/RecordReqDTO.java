package roadiary.behavior.record.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RecordReqDTO {
    
    private long categoryId;
    private int startTime;
    private int endTime;
    private String detail;

}
