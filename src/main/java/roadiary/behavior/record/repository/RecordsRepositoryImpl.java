package roadiary.behavior.record.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import roadiary.behavior.record.entity.RecordEntity;

@Repository
public class RecordsRepositoryImpl implements RecordsRepository {

    @Autowired
    private RecordsMapper recordsMapper;
    
    @Override
    public int insertRecord(RecordEntity recordEntity) {
        return recordsMapper.insertRecord(recordEntity);
    }

}
