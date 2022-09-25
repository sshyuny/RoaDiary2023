package roadiary.behavior.record.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import roadiary.behavior.record.dto.RecordDTO;

@Repository
public class RecordsRepositoryImpl implements RecordsRepository {

    @Autowired
    private RecordsMapper recordsMapper;
    
    @Override
    public int insertRecord(RecordDTO recordDTO) {
        return recordsMapper.insertRecord(recordDTO);
    }

}
