package roadiary.behavior.record.repository;

import roadiary.behavior.record.dto.RecordDTO;

public interface RecordsRepository {
    
    public int insertRecord(RecordDTO recordDTO);
    
}
