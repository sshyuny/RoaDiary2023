package roadiary.behavior.record.repository;

import roadiary.behavior.record.entity.RecordEntity;

public interface RecordsRepository {
    
    public int insertRecord(RecordEntity recordEntity);
    
}
