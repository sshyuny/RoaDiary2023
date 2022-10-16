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

        int startYear, startMonth, startDate;
        if (recordReqDto.getStartYear() == null) startYear = LocalDate.now().getYear();
        else startYear = recordReqDto.getStartYear();
        if (recordReqDto.getStartMonth() == null) startMonth = LocalDate.now().getMonthValue();
        else startMonth = recordReqDto.getStartMonth();
        if (recordReqDto.getStartDate() == null) startDate = LocalDate.now().getDayOfMonth();
        else startDate = recordReqDto.getStartDate();

        LocalDateTime startDateTime = LocalDateTime.of(LocalDate.of(startYear, startMonth, startDate), 
                LocalTime.of(recordReqDto.getStartHour(), recordReqDto.getStartMin()));
        LocalDateTime enddDateTime = LocalDateTime.of(LocalDate.of(startYear, startMonth, startDate), 
                LocalTime.of(recordReqDto.getEndHour(), recordReqDto.getEndMin()));

        //@@ enddDateTime이 더 앞설 경우 예외처리
        //@@ 12시간 이상 지속될경우 예외처리

        RecordEntity recordEntity = RecordEntity.of(recordReqDto.getCategoryId(), userId, startDateTime, enddDateTime, recordReqDto.getDetail());
        int insertedNum = recordsRepository.insertRecord(recordEntity);

        if (insertedNum == 1) return true;
        else return false;
    }
}   
