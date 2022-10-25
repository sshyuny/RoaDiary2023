package roadiary.behavior.record.repository;

import java.time.LocalDate;
import java.util.List;

import roadiary.behavior.record.dto.RecordResDto;
import roadiary.behavior.record.entity.RecordEntity;

public interface RecordsRepository {
    
    public int insertRecord(RecordEntity recordEntity);

    public List<RecordResDto> selectRecords(LocalDate reqDate, long userId);

    public int updateRecord(RecordEntity recordEntity);
    
}
