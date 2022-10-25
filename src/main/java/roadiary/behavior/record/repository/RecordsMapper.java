package roadiary.behavior.record.repository;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import roadiary.behavior.record.dto.RecordResDto;
import roadiary.behavior.record.entity.RecordEntity;

@Mapper
public interface RecordsMapper {
    
    public int insertRecord(RecordEntity recordEntity);

    public List<RecordResDto> selectRecords(@Param("reqDate") LocalDate reqDate, @Param("userId") long userId);

    public int updateRecord(RecordEntity recordEntity);

    public int deleteRecord(@Param("userId") long userId, @Param("behaviorRecordsId") long behaviorRecordsId);

}
