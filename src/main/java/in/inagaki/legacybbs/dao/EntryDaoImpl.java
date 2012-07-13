package in.inagaki.legacybbs.dao;

import in.inagaki.legacybbs.bean.Entry;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;


public class EntryDaoImpl extends NamedParameterJdbcDaoSupport implements EntryDao {
	private final String[][] REPLACEMENT_CHAR = { { "&", "<", ">", "'", "\"" },
			{ "&amp;", "&lt;", "&gt;", "&#39;", "&quot;" } };

	public List<Entry> getEntryList(int num) throws DataAccessException {
		RowMapper<Entry> rowMapper = new BeanPropertyRowMapper<Entry>(
				Entry.class);
		return getJdbcTemplate().query(
				"SELECT * FROM entry ORDER BY id DESC LIMIT " + (num - 1) * 10
						+ ", 10", rowMapper);
	}

	public void addEntry(Entry entry) throws DataAccessException {
		if (entry.getTitle() == "") {
			entry.setTitle("\u7121\u984c");
		}
		for (int i = 0; i < REPLACEMENT_CHAR[0].length; i++) {
			entry.setTitle(entry.getTitle().replaceAll(REPLACEMENT_CHAR[0][i],
					REPLACEMENT_CHAR[1][i]));
			entry.setName(entry.getName().replaceAll(REPLACEMENT_CHAR[0][i],
					REPLACEMENT_CHAR[1][i]));
			entry.setEmail(entry.getEmail().replaceAll(REPLACEMENT_CHAR[0][i],
					REPLACEMENT_CHAR[1][i]));
			entry.setMessage(entry.getMessage().replaceAll(
					REPLACEMENT_CHAR[0][i], REPLACEMENT_CHAR[1][i]));
		}
		if (entry.getTitle().length() > 64) {
			entry.setTitle(entry.getTitle().substring(0, 64));
		}
		if (entry.getName().length() > 32) {
			entry.setName(entry.getName().substring(0, 32));
		}
		if (entry.getEmail().length() > 64) {
			entry.setEmail(entry.getEmail().substring(0, 64));
		}
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(
				entry);
		getNamedParameterJdbcTemplate()
				.update("INSERT INTO entry (title, name, email, message, ip) VALUES (:title, :name, :email, :message, :ip)",
						parameterSource);
	}

	public int getPages() {
		int entries = getJdbcTemplate().queryForInt(
				"SELECT COUNT(*) FROM entry");
		return (int) (Math.ceil((double) entries / 10));
	}

}
