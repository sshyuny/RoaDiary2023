package roadiary.behavior.record.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import roadiary.behavior.record.dto.RecordReqDto;
import roadiary.behavior.record.entity.RecordEntity;
import roadiary.behavior.record.repository.RecordsRepository;

@RequiredArgsConstructor
@Service
public class RecordsService {

    private final RecordsRepository recordsRepository;
    
    public boolean addRecord(RecordReqDto recordReqDto, long userId) {

        LocalDateTime startDateTime = LocalDateTime.of(LocalDate.now(), 
                LocalTime.of(recordReqDto.getStartHour(), recordReqDto.getStartMin()));
        LocalDateTime enddDateTime = LocalDateTime.of(LocalDate.now(), 
                LocalTime.of(recordReqDto.getEndHour(), recordReqDto.getEndMin()));

        RecordEntity recordEntity = RecordEntity.create(recordReqDto.getCategoryId(), userId, startDateTime, enddDateTime, recordReqDto.getDetail());
        int insertedNum = recordsRepository.insertRecord(recordEntity);

        if (insertedNum == 1) return true;
        else return false;
    }
}   
