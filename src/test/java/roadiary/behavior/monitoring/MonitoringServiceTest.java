package roadiary.behavior.monitoring;

import static org.assertj.core.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.transaction.annotation.Transactional;

import com.zaxxer.hikari.HikariDataSource;

import roadiary.behavior.monitoring.exception.WrongDataSourceException;

public class MonitoringServiceTest {
    
    MonitoringService monitoringService = new MonitoringService();

    @Test
    void 데이터베이스구현체가다를경우() {
        //given
        DriverManagerDataSource dataSource = new DriverManagerDataSource("jdbc:mysql://localhost:3306/bhdb", "bhuser", "bhpw");

        //then
        assertThatThrownBy(() -> monitoringService.getHikariPoolStatus(dataSource))
                .isInstanceOf(WrongDataSourceException.class);
    }

    @Transactional
    @Test
    void 사용중인커넥션개수확인() throws SQLException, InterruptedException, WrongDataSourceException {
        //given
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/bhdb");
        dataSource.setUsername("bhuser");
        dataSource.setPassword("bhpw");
        dataSource.setMaximumPoolSize(10);

        //when
        Connection con1 = null;
        Connection con2 = null;

        try {
            con1 = dataSource.getConnection();
            con2 = dataSource.getConnection();
            
            //then
            DBStatusResDto dbStatusResDto = monitoringService.getHikariPoolStatus(dataSource);
            assertThat(dbStatusResDto.getActive()).isEqualTo(2);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con1);
            close(con2);
        }

    }

    private void close(Connection con) {
        JdbcUtils.closeResultSet(null);
        JdbcUtils.closeStatement(null);
        JdbcUtils.closeConnection(con);
    }
}
