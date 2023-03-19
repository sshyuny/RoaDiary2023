package roadiary.behavior.monitoring;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Setter @Getter
public class DBStatusResDto {
    
    private Integer active;
    private Integer idle;
    private Integer max;

    public static DBStatusResDto of(Integer activeNum, Integer idleNum, Integer max) {
        DBStatusResDto dbStatusResDto = new DBStatusResDto();
        dbStatusResDto.setActive(activeNum);
        dbStatusResDto.setIdle(idleNum);
        dbStatusResDto.setMax(max);
        return dbStatusResDto;
    }

}
