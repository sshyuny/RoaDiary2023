package roadiary.behavior.record.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RecordModifyReqDto {
    
    private Integer startTime;
    private Integer endTime;
    private String preStartTime;

}
