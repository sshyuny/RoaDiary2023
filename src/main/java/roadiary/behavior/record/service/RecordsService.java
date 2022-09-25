package roadiary.behavior.record.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import roadiary.behavior.record.dto.RecordDTO;
import roadiary.behavior.record.dto.RecordReqDTO;
import roadiary.behavior.record.repository.RecordsRepository;

@RequiredArgsConstructor
@Service
public class RecordsService {

    private final RecordsRepository recordsRepository;
    
    public boolean addRecord(RecordReqDTO recordReqDTO, long userId) {

        LocalDateTime startDateTime = LocalDateTime.of(LocalDate.now(), 
                LocalTime.of(recordReqDTO.getStartHour(), recordReqDTO.getStartMin()));
        LocalDateTime enddDateTime = LocalDateTime.of(LocalDate.now(), 
                LocalTime.of(recordReqDTO.getEndHour(), recordReqDTO.getEndMin()));

        RecordDTO recordDTO = RecordDTO.create(recordReqDTO.getCategoryId(), userId, startDateTime, enddDateTime, recordReqDTO.getDetail());
        int insertedNum = recordsRepository.insertRecord(recordDTO);

        if (insertedNum == 1) return true;
        else return false;
    }
}   
