package roadiary.behavior.record.repository;

import org.apache.ibatis.annotations.Mapper;

import roadiary.behavior.record.dto.RecordDTO;

@Mapper
public interface RecordsMapper {
    
    public int insertRecord(RecordDTO recordDTO);

}
