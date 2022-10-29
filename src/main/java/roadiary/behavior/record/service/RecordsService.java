package roadiary.behavior.record.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import roadiary.behavior.record.dto.RecordModifyReqDto;
import roadiary.behavior.record.dto.RecordReqDto;
import roadiary.behavior.record.dto.RecordResDto;
import roadiary.behavior.record.entity.RecordEntity;
import roadiary.behavior.record.repository.RecordsRepository;

@RequiredArgsConstructor
@Service
public class RecordsService {

    private final RecordsRepository recordsRepository;
    
    public boolean addRecord(RecordReqDto recordReqDto, long userId) {

        LocalDateTime startDateTime = LocalDateTime.of(LocalDate.of(recordReqDto.getStartYear(), recordReqDto.getStartMonth(), recordReqDto.getStartDate()), 
                LocalTime.of(recordReqDto.getStartHour(), recordReqDto.getStartMin()));
        LocalDateTime enddDateTime = LocalDateTime.of(LocalDate.of(recordReqDto.getEndYear(), recordReqDto.getEndMonth(), recordReqDto.getEndDate()), 
                LocalTime.of(recordReqDto.getEndHour(), recordReqDto.getEndMin()));

        //@@ enddDateTime이 더 앞설 경우 예외처리
        //@@ 12시간 이상 지속될경우 예외처리
        //@@ 하루 최대 저장 개수 제한

        RecordEntity recordEntity = RecordEntity.of(recordReqDto.getCategoryId(), userId, startDateTime, enddDateTime, recordReqDto.getDetail());
        int insertedNum = recordsRepository.insertRecord(recordEntity);

        if (insertedNum == 1) return true;
        else return false;
    }

    public List<RecordResDto> getRecords(LocalDate reqDate, long userId) {
        return recordsRepository.selectRecords(reqDate, userId);
    }

    public int modifyRecord(long userId, RecordModifyReqDto recordModifyReqDto) {

        LocalDateTime start = LocalDateTime.of(
            recordModifyReqDto.getStartYear(), recordModifyReqDto.getStartMonth(), recordModifyReqDto.getStartDate(), 
            recordModifyReqDto.getStartHour(), recordModifyReqDto.getStartMin());
        LocalDateTime end = LocalDateTime.of(
            recordModifyReqDto.getStartYear(), recordModifyReqDto.getStartMonth(), recordModifyReqDto.getStartDate(), 
            recordModifyReqDto.getEndHour(), recordModifyReqDto.getEndMin());

        RecordEntity recordEntity = RecordEntity.of(
            recordModifyReqDto.getBehaviorRecordsId(), recordModifyReqDto.getBehaviorCategoryId(), 
            userId, start, end, recordModifyReqDto.getDetail());

        return recordsRepository.updateRecord(recordEntity);
    }

    public int deleteRecord(long userId, long behaviorRecordsId) {
        return recordsRepository.deleteRecord(userId, behaviorRecordsId);
    }
}   
