package roadiary.behavior.monitoring;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.metadata.HikariDataSourcePoolMetadata;
import org.springframework.stereotype.Service;

import com.zaxxer.hikari.HikariDataSource;

import roadiary.behavior.monitoring.exception.WrongDataSourceException;

@Service
public class MonitoringService {
    
    public DBStatusResDto getHikariPoolStatus(DataSource dataSource) throws WrongDataSourceException {
        
        if (!(dataSource instanceof HikariDataSource)) {
            throw new WrongDataSourceException();
        }

        HikariDataSourcePoolMetadata poolMetadata = new HikariDataSourcePoolMetadata((HikariDataSource) dataSource);
        return DBStatusResDto.of(poolMetadata.getActive(), poolMetadata.getIdle(), poolMetadata.getMax());
    }
}
