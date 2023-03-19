package roadiary.behavior.monitoring;

import javax.sql.DataSource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zaxxer.hikari.HikariDataSource;

import lombok.AllArgsConstructor;
import roadiary.behavior.monitoring.exception.WrongDataSourceException;

@AllArgsConstructor
@RestController
public class MonitoringRestController {

    private final DataSource dataSource;
    private final MonitoringService monitoringService;
    
    @GetMapping("/api/monitoring")
    public DBStatusResDto dataSourcePoolStatus() throws WrongDataSourceException {
        
        return monitoringService.getHikariPoolStatus(dataSource);
    }
}
