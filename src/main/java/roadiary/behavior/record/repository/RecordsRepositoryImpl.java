package roadiary.behavior.record.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import roadiary.behavior.record.dto.RecordResDto;
import roadiary.behavior.record.entity.RecordEntity;

@Repository
public class RecordsRepositoryImpl implements RecordsRepository {

    @Autowired
    private RecordsMapper recordsMapper;
    
    @Override
    public int insertRecord(RecordEntity recordEntity) {
        return recordsMapper.insertRecord(recordEntity);
    }

    @Override
    public List<RecordResDto> selectRecords(LocalDate reqDate, long userId) {
        return recordsMapper.selectRecords(reqDate, userId);
    }

    @Override
    public int updateRecord(RecordEntity recordEntity) {
        return recordsMapper.updateRecord(recordEntity);
    }

    @Override
    public int deleteRecord(long userId, long behaviorRecordsId) {
        return recordsMapper.deleteRecord(userId, behaviorRecordsId);
    }

}
