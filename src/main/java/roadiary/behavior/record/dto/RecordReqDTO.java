package roadiary.behavior.record.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RecordReqDto {
    
    private long categoryId;
    
    private int startYear;
    private int startMonth;
    private int startDate;
    private int startHour;
    private int startMin;

    private int endYear;
    private int endMonth;
    private int endDate;
    private int endHour;
    private int endMin;

    private String detail;

}
