package roadiary.behavior.record.repository;

import org.apache.ibatis.annotations.Mapper;

import roadiary.behavior.record.entity.RecordEntity;

@Mapper
public interface RecordsMapper {
    
    public int insertRecord(RecordEntity recordEntity);

}
