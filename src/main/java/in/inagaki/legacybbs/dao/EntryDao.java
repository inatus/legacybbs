package in.inagaki.legacybbs.dao;

import in.inagaki.legacybbs.bean.Entry;

import java.util.List;

import org.springframework.dao.DataAccessException;


public interface EntryDao {
	List<Entry> getEntryList(int num) throws DataAccessException;
	void addEntry(Entry entry) throws DataAccessException;
	int getPages();
}
