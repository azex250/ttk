package az.dao;

import az.model.Note;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Connection;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import org.springframework.jdbc.core.RowMapper;  
import org.springframework.stereotype.Component;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.beans.factory.annotation.Autowired; 


import org.springframework.jdbc.core.PreparedStatementCreator;

@Component
public class NoteDAO implements INoteDAO {

	private DataSource dataSource;
	private String queryListTmplate =
		"select notes.id, notes.title, notes.body, foo.tagnames from notes left join ( " +
		"select tag_note_links.note_id, group_concat(tags.name separator ', ') as tagnames " + 
		"from tag_note_links left join tags on tags.id=tag_note_links.tag_id " + 
		"group by tag_note_links.note_id ) as foo on notes.id = foo.note_id where 1=1 ";

	private RowMapper<Note> getRowMapper() {
		return new RowMapper<Note>() {
	        public Note mapRow(ResultSet rs, int rowNum) throws SQLException {
	            Note c = new Note();
	            c.setId(rs.getInt(1));
	            c.setTitle(rs.getString(2));
	            c.setBody(rs.getString(3));
	            c.setTags(rs.getString(4));
	            return c;
	        }
	    };
	}

	@Autowired 
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public int create(final Note note) {
		final String queryNote = "insert into notes (title, body) values (?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(queryNote, new String[] { "id" });
				ps.setString(1, note.getTitle());
				ps.setString(2, note.getBody());
				return ps;
			}
		}, keyHolder);

		return keyHolder.getKey().intValue();
	}

	@Override
	public List<Note> list() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate.query(String.format(queryListTmplate, ""), getRowMapper());
	};

	@Override
	public List<Note> listByTag(String tag_name) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String queryList = queryListTmplate + "and exists (select 1 from tag_note_links as tnl, tags as tgs where tnl.tag_id=tgs.id and tnl.note_id=notes.id and tgs.name=?);";
		return jdbcTemplate.query(queryList, getRowMapper(), tag_name);
	};

	@Override
	public List<Note> listByTitle(String title) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String queryList = queryListTmplate + "and notes.title like ?;";
		String param = "%" + title + "%";
		return jdbcTemplate.query(queryList, getRowMapper(), param);
	};

	@Override
	public void addTag(int note_id, String tag_name) {
		String queryNote = "insert into tag_note_links (note_id, tag_id) select ?, id from tags where name=?;";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(queryNote, new Object[] {note_id, tag_name});
	};
}


