package roadiary.behavior.record.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RecordModifyReqDto {
    
    private long behaviorRecordsId;
    private long behaviorCategoryId;

    private Integer startYear;
    private Integer startMonth;
    private Integer startDate;

    private Integer startHour;
    private Integer startMin;
    private Integer endHour;
    private Integer endMin;

    private String detail;
}
