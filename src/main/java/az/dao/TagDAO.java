package az.dao;


import java.util.List;
import java.util.ArrayList;
import javax.sql.DataSource;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;  
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired; 
import java.sql.ResultSet;

@Component
public class TagDAO implements ITagDAO {
	private DataSource dataSource;

	@Autowired 
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override 
	public List<String> list() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String query = "Select name from tags";
		return jdbcTemplate.query(query,
		    new RowMapper<String>() {
		        public String mapRow(ResultSet rs, int rowNum) throws SQLException {
		            return rs.getString(1);
		        }
		    }
	    );
	}
}