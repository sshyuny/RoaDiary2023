package roadiary.behavior.record.service;

import java.time.LocalDateTime;

public class RecordsUtil {
    
    public static boolean isStartDateEarlier(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if (startDateTime.isAfter(endDateTime)) return false;
        else return true;
    }
}
